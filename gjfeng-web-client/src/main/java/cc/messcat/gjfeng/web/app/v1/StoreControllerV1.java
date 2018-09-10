package cc.messcat.gjfeng.web.app.v1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.common.vo.app.StoreJoinVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfStoreInfoDubbo;
import cc.messcat.gjfeng.upload.UploadFileUtil;
import cc.messcat.gjfeng.util.SessionUtil;
import cc.messcat.gjfeng.web.wechat.StoreController;

@Controller
@RequestMapping(value = "app/store/", headers = "app-version=v1.0")
public class StoreControllerV1 extends BaseController {

	@Value("${gjfeng.client.project.url}")
	private String projectName;

	@Value("${upload.store.electronic.path}")
	private String uploadFilePath;

	@Value("${upload.store.banner.path}")
	private String uploadImagePath;

	@Value("${dubbo.application.web.client}")
	private String projectNames;

	@Autowired
	@Qualifier("storeInfoDubbo")
	private GjfStoreInfoDubbo storeInfoDubbo;

	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("response")
	private HttpServletResponse response;

	/**
	 * @描述 商家入驻申请
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/addStore", method = RequestMethod.POST)
	@ResponseBody
	public Object addStore(StoreInfoVo storeInfoVo, StoreJoinVo storeJoinVo, String fileContent, String fileName,
			String account) {
		try {
			if (ObjValid.isNotValid(storeInfoVo)) {
				resultVo = new ResultVo(400, "提交失败，参数不全", null);
				return resultVo;
			}
			if (!fileContent.isEmpty()) {
				String url = "";
				if (!fileContent.isEmpty()) {
					String[] str = fileContent.split(",");
					String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, uploadFilePath);
					url = projectName + uploadFilePath + "/" + fils;
				}
				storeJoinVo.setBusinessLicenceNumberElectronic(url);
			} else {
				resultVo = new ResultVo(400, "文件上传失败", null);
				return resultVo;
			}
			resultVo = storeInfoDubbo.addStore(storeInfoVo, storeJoinVo, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 我是商家
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/myStore", method = RequestMethod.POST)
	@ResponseBody
	public Object myStore(String account) {
		try {
			resultVo = storeInfoDubbo.findStoreByAccount(account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 修改店铺banner图
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/updateBanner", method = RequestMethod.POST)
	@ResponseBody
	public Object updateBanner(String fileContent, String fileName, Long storeId) {
		try {
			String bannerImgUrl = "";
			if (!fileContent.isEmpty()) {
				String[] str = fileContent.split(",");
				String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, uploadFilePath);
				bannerImgUrl = projectName + uploadFilePath + "/" + fils;
			}
			resultVo = storeInfoDubbo.updateStoreBanner(storeId, bannerImgUrl);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 修改店铺简介
	 * @author Karhs
	 * @date 2017年1月12日
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "v1_0/updateIntro", method = RequestMethod.POST)
	@ResponseBody
	public Object updateIntro(@RequestParam("description") String description,Long storeId) {
		try {	
			resultVo = storeInfoDubbo.updateStoreDescription(storeId, description);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 修改店铺简介
	 * @author Karhs
	 * @date 2017年1月12日
	 * @param description
	 * @return
	 */
	@RequestMapping(value = "v1_0/updateAddressInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object updateAddressInfo(Long storeId,String sellerMobile, String cityValue, String addressDetail) {
		try {
			resultVo = storeInfoDubbo.modifyStoreAddress(storeId, sellerMobile, cityValue, addressDetail);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreController.class);
		}
		return resultVo;
	}

}
