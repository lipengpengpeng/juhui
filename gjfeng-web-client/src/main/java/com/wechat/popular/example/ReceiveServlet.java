package com.wechat.popular.example;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.popular.api.MessageAPI;
import com.wechat.popular.api.QrcodeAPI;
import com.wechat.popular.api.TokenAPI;
import com.wechat.popular.api.UserAPI;
import com.wechat.popular.bean.BaseResult;
import com.wechat.popular.bean.EventMessage;
import com.wechat.popular.bean.QrcodeTicket;
import com.wechat.popular.bean.Token;
import com.wechat.popular.bean.User;
import com.wechat.popular.bean.message.TextMessage;
import com.wechat.popular.bean.xmlmessage.XMLTextMessage;
import com.wechat.popular.util.ExpireSet;
import com.wechat.popular.util.MessageUtil;
import com.wechat.popular.util.SignatureUtil;
import com.wechat.popular.util.XMLConverUtil;

import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.EmojiFilter;
import cc.messcat.gjfeng.common.util.GenerateQrCodeUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.util.javaHelp;
import cc.messcat.gjfeng.common.vo.app.MemberInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfStoreInfoDubbo;
import cc.messcat.gjfeng.entity.GjfAccessToken;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.common.constant.CommonProperties;

/**
 * 服务端事件消息接收
 * 
 * @author Yi
 */
@Controller
@RequestMapping("wechat/")
public class ReceiveServlet {

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("response")
	private HttpServletResponse response;

	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;

	@Autowired
	@Qualifier("storeInfoDubbo")
	private GjfStoreInfoDubbo storeInfoDubbo;

	@Autowired
	@Qualifier("loginDubbo")
	private GjfLoginDubbo loginDubbo;

	@Value("${dubbo.application.web.client}")
	private String projectNames;

	@Value("${gjfeng.client.project.url}")
	private String projectName;

	@Value("${upload.member.code.path}")
	private String imageFolderNames;

	// 从官方获取
	//private String token = "gjfeng";
	private String token = "jh200";

	// 重复通知过滤 时效60秒
	private static ExpireSet<String> expireSet = new ExpireSet<String>(60);

	@RequestMapping(value = "handShake",method=RequestMethod.POST)
	@ResponseBody
	public String handShake() throws Exception {

		System.out.println("进入ReceiveServlet");
		HttpSession session = request.getSession();
		/*
		 * HttpServletRequest request = ServletActionContext.getRequest();
		 * HttpServletResponse response = ServletActionContext.getResponse();
		 */
		ServletInputStream inputStream = request.getInputStream();
		ServletOutputStream outputStream = response.getOutputStream();

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		// 首次请求申请验证,返回echostr
		if (echostr != null) {
			outputStreamWrite(outputStream, echostr);
			return null;
		}
		// 验证请求签名
		System.out.println("--token---->"+token+"---timestamp--->"+timestamp+"---nonce--->"+nonce);
		try {
			if (!signature.equals(SignatureUtil.generateEventMessageSignature(token, timestamp, nonce))) {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "数据异常...");
		}

		if (inputStream != null) {
			// 转换XML
			EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
			String expireKey = eventMessage.getFromUserName() + "__" + eventMessage.getToUserName() + "__"
					+ eventMessage.getMsgId() + "__" + eventMessage.getCreateTime();
			if (expireSet.contains(expireKey)) {
				// 重复通知不作处理
				return null;
			} else {
				expireSet.add(expireKey);
			}

			String openid = eventMessage.getFromUserName();
			System.out.println(openid);
			// 创建回复

			try {
				if (!eventMessage.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 接收普通消息
					// 发送文本信息
					XMLTextMessage xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(),
							eventMessage.getToUserName(), "亲，您好！如有问题请留言，我们会尽快给您答复，感谢您的支持！");
					xmlTextMessage.outputStreamWrite(outputStream);
				} else if (eventMessage.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { // 接收事件推送
					if (!"".equals(eventMessage.getFromUserName()) && !"".equals(eventMessage.getCreateTime())) {
						String eventType = eventMessage.getEvent();
						if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅

							
							XMLTextMessage xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(),
							 eventMessage.getToUserName(), "巨惠云商是建立在以互联网大数据为支撑，结合共享经济与资源整合的理念，整合工厂、批发商、服务商资源，建立线下服务点，以创新的电商联营商业服务模式，助力实体企业提升盈利能力，达到共赢的体系。");
					
							xmlTextMessage.outputStreamWrite(outputStream);

							if (StringUtil.isNotBlank(openid)) {

								// 扫码关注获取父级id
								String eventKeys = eventMessage.getEventKey();
								// 获取openId
								String openId = eventMessage.getFromUserName();
								System.out.println(eventKeys);
								if (BeanUtil.isValid(eventKeys) && BeanUtil.isValid(openId)) {// 如果父级id存在
									// 截取父级id
									String[] eventKey = eventKeys.split("_");
									// 添加用户信息
									addMemberInfo(Long.parseLong(eventKey[1]), session, openId, eventMessage);

								} else {
									// 如果是普通关注
									addMemberInfo(null, session, openId, eventMessage);
								}

							}
							return null;
						} else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {

						
							XMLTextMessage xmlTextMessage = new XMLTextMessage(eventMessage.getFromUserName(),
							  eventMessage.getToUserName(), "巨惠云商是建立在以互联网大数据为支撑，结合共享经济与资源整合的理念，整合工厂、批发商、服务商资源，建立线下服务点，以创新的电商联营商业服务模式，助力实体企业提升盈利能力，达到共赢的体系。");
					
							xmlTextMessage.outputStreamWrite(outputStream);

							if (StringUtil.isNotBlank(openid)) {

								// 扫码关注获取父级id
								String eventKeys = eventMessage.getEventKey();
								// 获取openId
								String openId = eventMessage.getFromUserName();
								if (BeanUtil.isValid(eventKeys) && BeanUtil.isValid(openId)) {// 如果父级id存在
									// 截取父级id
									// String[] eventKey = eventKeys.split("_");
									// 添加用户信息
									addMemberInfo(Long.parseLong(eventKeys), session, openId, eventMessage);

								} else {
									// 如果是普通关注
									addMemberInfo(null, session, openId, eventMessage);
								}

							}
							return null;
						}

					}
					outputStreamWrite(outputStream, "");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		outputStreamWrite(outputStream, "");
		return null;
	}

	/**
	 * 数据流输出
	 * 
	 * @param outputStream
	 * @param text
	 * @return
	 */
	private boolean outputStreamWrite(OutputStream outputStream, String text) {
		try {
			outputStream.write(text.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * 添加用户信息
	 */
	private final String TYPE = "1";

	public void addMemberInfo(Long superId, HttpSession session, String openid, EventMessage eventMessage) {
		String unionId = "";
		try {
			if (StringUtil.isNotBlank(openid)) {// 判断openid是否为空
				// 获取数据库token信息
				GjfAccessToken accessToken = (GjfAccessToken) memberInfoDubbo.getAccessToken(TYPE).getResult();
				User user = null;
				Token tokens = new Token();
				if (BeanUtil.isValid(accessToken)) {// 如果tokentoken信息不为空
					Date time = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					// 判断token是否失效
					if (sdf.parse(sdf.format(accessToken.getExpirationTime()))
							.compareTo(sdf.parse(sdf.format(time))) == 1) {
						user = UserAPI.userInfo(accessToken.getToken(), openid); // 获取用户信息
						tokens.setAccess_token(accessToken.getToken());
					} else {// 如果token失效
						tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, // 吐过token信息
								CommonProperties.GJFENG_APPSECRET);
						int i = 0;
						while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { // 如果请求失败重新获取token
							tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
							if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
								Thread.sleep(500); // 睡眠0.5 再取
							}
							i++;
						}
						// 获取用户信息
						user = UserAPI.userInfo(tokens.getAccess_token(), openid);
						// 保存token信息
						GjfAccessToken token = new GjfAccessToken(CommonProperties.GJFENG_APP_ID,
								CommonProperties.GJFENG_APPSECRET, TYPE);

						token.setToken(tokens.getAccess_token());
						memberInfoDubbo.addAccessToken(token);
					}
				} else {
					tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
					// 如果请求失败重新获取token
					int i = 0;
					while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { //
						tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
						if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
							Thread.sleep(500); // 睡眠0.5 再取
						}
						i++;
					}
					// 获取用户基本信息
					user = UserAPI.userInfo(tokens.getAccess_token(), openid);
					// 保存用户信息
					GjfAccessToken token = new GjfAccessToken(CommonProperties.GJFENG_APP_ID,
							CommonProperties.GJFENG_APPSECRET, TYPE);
					token.setToken(tokens.getAccess_token());
					memberInfoDubbo.addAccessToken(token);
				}

				if (ObjValid.isValid(user)) {
					System.out.println("errmsg--->"+user.getErrmsg());
					unionId = user.getUnionid();// 获取用户的unionid
					GjfMemberInfo member = memberInfoDubbo.findMemberByUnionId(user.getUnionid());// 根据用户unionid获取用户信息
					System.out.println("unionid---->"+unionId+"--用户---->"+member+"---openId--->"+openid);
					if (!ObjValid.isValid(member) && ObjValid.isValid(openid)) { // 没有该会员信息时保存
						if (!BeanUtil.isValid(superId)) {
							superId = 0L;
						}
						unionId = user.getUnionid();
						ResultVo resultVo = loginDubbo.register(user.getUnionid(), null, EmojiFilter.filterEmoji(user.getNickname()),
								String.valueOf(user.getSex()), javaHelp.toString(user.getHeadimgurl(), ""), "1",
								superId, openid);
						MemberInfoVo memberInfoVo = (MemberInfoVo) resultVo.getResult();

						if (superId != 0) {// 如果父级id不为空
							// 添加完成后根据父级id获取推荐人信息
							GjfMemberInfo superInfo = (GjfMemberInfo) memberInfoDubbo.findMemberById(superId)
									.getResult();
							// 推送消息内容
							// String str = "恭喜！！！[" + superInfo.getNickName() +
							// "]已经成为你的推荐会员";

							String str = "恭喜你已经成为[" + superInfo.getNickName() + "]的会员";
							// 创建推送的text对象
							TextMessage mag = new TextMessage(eventMessage.getFromUserName(), str);
							BaseResult baseResult = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag);
							// 推送给推荐会员
							String str0 = "恭喜[" + memberInfoVo.getNickName() + "]已经成功扫码注册为您的用户";
							// 创建推送的text对象
							System.out.println(superInfo.getOpenId());
							TextMessage mag0 = new TextMessage(superInfo.getOpenId(), str0);
							BaseResult baseResult0 = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag0);

						} else {
							// 推送消息内容
							//String str = "恭喜您注册成为巨惠云商平台用户，现您推荐人为平台，还可重新扫码绑定一次";
							String str = "恭喜您注册成为巨惠云商平台用户，现您推荐人为平台，还可重新扫码绑定一次";
							// 创建推送的text对象
							TextMessage mag = new TextMessage(eventMessage.getFromUserName(), str);
							BaseResult baseResult = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag);
						}

						// 生成二维码

						String access_token = tokens.getAccess_token();
						// int rsult=new java.util.Random().nextInt(100001);
						QrcodeTicket qrcodeTicket = QrcodeAPI.qrcodeCreateFinalStr(access_token,
								memberInfoVo.getId().toString());
						if ("40001".equals(qrcodeTicket.getErrcode())) {
							tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);

							GjfAccessToken token = new GjfAccessToken();
							token.setAppId(CommonProperties.GJFENG_APP_ID);
							token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
							token.setToken(tokens.getAccess_token());
							token.setType(TYPE);
							memberInfoDubbo.addAccessToken(token);
							qrcodeTicket = QrcodeAPI.qrcodeCreateFinalStr(access_token,
									memberInfoVo.getId().toString());
						}
						String url = "";
						String path = "";
						try {
							url = GenerateQrCodeUtil.generateQrcode(request, qrcodeTicket.getUrl(), projectNames,
									imageFolderNames);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (BeanUtil.isValid(url)) {
							path = projectName + imageFolderNames + "/" + url;
						}
						GjfMemberInfo info = memberInfoDubbo.findMemberByUnionId(unionId);
						info.setImgQrUrl(path);
						memberInfoDubbo.updateMemberCode(info);
					} else {
						if (BeanUtil.isValid(superId)) {
							if (superId.longValue() == member.getId().longValue()) {// 如果推荐人是自己

								// 推送消息内容
								String str = "非法操作，不能与自己绑定";
								// 创建推送的text对象
								TextMessage mag = new TextMessage(eventMessage.getFromUserName(), str);
								BaseResult baseResult = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag);
							} else if (member.getSuperId() == 0) {// 如果用户不存在推荐人
								GjfMemberInfo superInfo = (GjfMemberInfo) memberInfoDubbo.findMemberById(superId)
										.getResult();

								if (superInfo.getSuperId().longValue() == member.getId().longValue()) {
									
									//更新openid
									member.setOpenId(openid);
									memberInfoDubbo.updateMemberCode(member);
									// 推送消息内容
									String str = "非法操作，[推荐人" + superInfo.getNickName() + "]是你的下级";
									// 创建推送的text对象
									TextMessage mag = new TextMessage(eventMessage.getFromUserName(), str);
									BaseResult baseResult = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag);
								} else {
									
									member.setOpenId(openid);
									member.setSuperId(superId);
									member.setBindTime(new Date());
									memberInfoDubbo.updateMemberCode(member);
									GjfMemberInfo gfMem=(GjfMemberInfo) memberInfoDubbo.findMemberById(member.getId()).getResult();
									if(gfMem.getSuperId()==0){
										//String str = "恭喜您注册成为巨惠云商平台用户，现您推荐人为平台，还可重新扫码绑定一次";
										String str = "恭喜您注册成为巨惠云商平台用户，现您推荐人为平台，还可重新扫码绑定一次";
										// 创建推送的text对象
										TextMessage mag = new TextMessage(eventMessage.getFromUserName(), str);
										BaseResult baseResult = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag);
									}else{
										String str = "恭喜你已经成为[" + superInfo.getNickName() + "]的会员";
										// 创建推送的text对象
										TextMessage mag = new TextMessage(eventMessage.getFromUserName(), str);
										BaseResult baseResult = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag);
										
										// 推送给推荐会员
										String str0 = "恭喜[" + gfMem.getNickName() + "]已经成功扫码注册为您的用户";
										// 创建推送的text对象
										TextMessage mag0 = new TextMessage(superInfo.getOpenId(), str0);
										BaseResult baseResult0 = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag0);
									}
								}

							} else {
								//更新openid
								member.setOpenId(openid);
								memberInfoDubbo.updateMemberCode(member);
								// 查询推荐人信息
								GjfMemberInfo superInfo = (GjfMemberInfo) memberInfoDubbo
										.findMemberById(member.getSuperId()).getResult();
								// 推送消息内容
								String str = "您已经是［" + superInfo.getNickName() + "］的会员，不能重新绑定。";
								// 创建推送的text对象
								TextMessage mag = new TextMessage(eventMessage.getFromUserName(), str);
								BaseResult baseResult = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag);

							}
						} else {
							//更新openid
							member.setOpenId(openid);
							memberInfoDubbo.updateMemberCode(member);
							// 推送消息内容
							String str = "您已经是平台的会员";
							// 创建推送的text对象
							TextMessage mag = new TextMessage(eventMessage.getFromUserName(), str);
							BaseResult baseResult = MessageAPI.messageCustomSend(tokens.getAccess_token(), mag);
						}
					}

				}

			}
			if (StringUtil.isNotBlank(unionId)) {
				GjfMemberInfo member = memberInfoDubbo.findMemberByUnionId(unionId);
				if (ObjValid.isValid(member)) {
					System.out.println("用户是否启用-----"+member.getIsDel());
					if("1".equals(member.getIsDel())){
						System.out.println("进入session");
						session.setAttribute("gjfMemberInfo", member);
						if (StringUtil.isNotBlank(member.getMobile())) {
							session.setAttribute("account", member.getMobile());
							if (member.getType().equals(TYPE)) {
								Object o=storeInfoDubbo.findStoreByAccount(member.getMobile()).getResult();
								if (ObjValid.isValid(o)) {
									session.setAttribute("gjfStoreInfo",o);
								}
							}
						}
						session.setAttribute("unionId", member.getUnionId());
					}else{					
						session.removeAttribute("account");
						session.removeAttribute("unionId");						
					}					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
