package cc.messcat.gjfeng.web.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cc.messcat.gjfeng.common.constant.CommonConstant;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.ResponseBean;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductCommentDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo;
import cc.messcat.gjfeng.entity.GjfProductComment;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.upload.UploadFileUtil;
import cc.messcat.gjfeng.util.SessionUtil;

@Controller
@RequestMapping(value = "/wx/comment")
public class ProductCommentController extends BaseController {

	@Autowired
	private GjfProductCommentDubbo productCommetDubbo;

	@Autowired
	@Qualifier("orderInfoDubbo")
	private GjfOrderInfoDubbo orderInfoDubbo;
	
	@Autowired
	@Qualifier("productInfoDubbo")
	private GjfProductInfoDubbo productInfoDubbo;
	
	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;
	
	@Value("${gjfeng.client.project.url}")
	private String projectName;

	@Value("${upload.assessment.path}")
	private String imageFolderName;

	/**
	 * 添加評論
	 * 
	 * @param comment
	 * @param fileContent
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value = "newProCommet", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView newProCommet(Long memberId,String proId,String comScore,String content, String fileContent, String fileName, String orderSn) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account=SessionUtil.getAccountSession(request);
			//处理评论信息
			String[] pros=proId.split(",");
			String[] scores=comScore.split(",");
			String[] contents=content.split("&");
			String[] fileContents=null;
			if(!ObjValid.isNotValid(fileContent)){
				fileContents=fileContent.substring(0, fileContent.length()-1).split(",");
			}
			
			GjfMemberInfo info=new GjfMemberInfo();
			info.setId(memberId);
			for(int i=0;i<pros.length;i++){
				GjfProductComment comment=new GjfProductComment();
				GjfProductInfo productInfo=new GjfProductInfo();
				productInfo.setId(Long.parseLong(pros[i]));
		    	comment.setMember(info);
		    	comment.setContent(contents[i]);
		    	comment.setComScore(Integer.parseInt(scores[i]));
		    	comment.setProduct(productInfo);
		    	String img="";
		    	if(!ObjValid.isNotValid(fileContents)){
		    		if(fileContents.length>i){
		    			img=fileContents[i];
		    		}		    		
				}
		    	resultVo = productCommetDubbo.addProComment(comment,img, fileName, orderSn);
			}		   
			model.put("resultVo", orderInfoDubbo.findMyOrder(1, 10, account, "3"));
			model.put("status", 3);
		} catch (Exception e) {
			LoggerPrint.getResult(e, ProductController.class);
		}	
		return new ModelAndView("wx/o2o-shop/order-all", model);	
	}

	/**
	 * 分頁獲取商品評論
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "getProCommetByPage", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getProCommetByPage(Integer pageNo, Integer pageSize, Long proId,String state,String type) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			resultVo = productCommetDubbo.getProComByPager(pageNo, pageSize, proId,state);
			model.put("resultVo", resultVo);
			model.put("proId", proId);
		} catch (Exception e) {
			LoggerPrint.getResult(e, ProductController.class);
		}
		if(Integer.parseInt(type)==0){
			return new ModelAndView("wx/online-shop/goods-comments", model);
		}else{
			return new ModelAndView("wx/o2o-shop/assessment-all", model);
		}
		
	}

	/**
	 * 分页回调
	 * @param pageNo
	 * @param pageSize
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "getAllProCommetByPage", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllProCommetByPage(Integer pageNo, Integer pageSize, Long proId,String state) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			resultVo = productCommetDubbo.getProComByPager(pageNo, pageSize, proId,state);
			model.put("resultVo", resultVo);
			model.put("proId", proId);
		} catch (Exception e) {
			LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}

	/**
	 * 刪除評論
	 * 
	 * @param comId
	 * @return
	 */
	@RequestMapping(value = "delProCommet", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView delProCommet(Long comId) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			resultVo = productCommetDubbo.removeCom(comId);
			model.put("resultVo", resultVo);
		} catch (Exception e) {
			LoggerPrint.getResult(e, ProductController.class);
		}
		return new ModelAndView("wx/online-shop/pay-affirm", model);
	}

	/**
	 * 跳转到评论页面
	 * 
	 * @param orderSn
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "goCommentPage", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView goCommentPage(String orderSn, String token, String proId) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			resultVo = orderInfoDubbo.findOrderBySn(orderSn, token);			
			model.put("resultVo", resultVo);
			String[] pros=proId.split(",");
			List<GjfProductInfo> list=new ArrayList<>();
			for(int i=0;i<pros.length;i++){
				GjfProductInfo product=(GjfProductInfo) productInfoDubbo.findProductById(Long.parseLong(pros[i])).getResult();
				list.add(product);
			}
			model.put("pros", list);
			model.put("proId", proId);
		} catch (Exception e) {
			LoggerPrint.getResult(e, ProductController.class);
		}
		return new ModelAndView("wx/o2o-shop/assessment", model);
	}

	/**
	 * 统计商品评论数
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "countProComment", method = RequestMethod.GET)
	@ResponseBody
	public Object countProComment(Long proId,String state) {
		try {
			resultVo = productCommetDubbo.countProCom(proId,state);

		} catch (Exception e) {
			LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}
	
	/**
	 * 统计商品评论数
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "getProductById", method = RequestMethod.GET)
	@ResponseBody
	public Object getProductById(Long proId) {
		try {
			resultVo = productInfoDubbo.findProductById(proId);

		} catch (Exception e) {
			LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}
	
	/**
	 *上传图片
	 * @param fileName
	 * @param fileContent
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadComImg", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadComImg(@RequestParam String fileName, @RequestParam String fileContent) throws Exception {
		String url="";
		if (!fileContent.isEmpty()) {
			String[] str = fileContent.split(",");
			String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, imageFolderName);
			url = projectName + imageFolderName + "/" + fils;
		}
		return new ResponseBean(CommonConstant.SUCCESS_CODE_200, CommonConstant.MSG_SUCCESS, url);
	}

	
	
	
	
}
