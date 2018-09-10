/**
 * 
 * @Copyright (c) 2015 Messcat. All rights reserved.
 */
package cc.messcat.gjfeng.web.wechat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cc.messcat.gjfeng.common.constant.CommonConstant;
import cc.messcat.gjfeng.common.util.FileHandler;
import cc.messcat.gjfeng.common.util.FilesUploadModel;
import cc.messcat.gjfeng.common.util.ResponseBean;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.upload.UploadFileUtil;



/**
 * 公共接口 文件上传，文件下载，文件删除
 * 
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/base")
public class ActionController {

	@Value("${dubbo.application.web.client}")
	private String projectName;

	@Value("${upload.member.idcard.path}")
	private String imageFolderName;

	/*@Value("${upload.file.path}")
	private String fileFolderName;*/
	
	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Value("${upload.file.maxUploadSize}")
	private Long fileMaxSize;

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	@ResponseBody
	public Object pullImageFile(MultipartFile file) throws Exception {
		FilesUploadModel fileModel = null;
		if (!file.isEmpty()) {
			fileModel = new FilesUploadModel(file.getOriginalFilename(), file.getInputStream(), projectName, imageFolderName);
			fileModel.writeFileByBinary();
			if (!fileModel.isFileExists())
				throw new Exception("文件写入异常！！");
		}
		return new ResponseBean(CommonConstant.SUCCESS_CODE_200, CommonConstant.MSG_SUCCESS, fileModel.getFileUri());
	}

	@RequestMapping(value = "/imageUploadByBase64", method = RequestMethod.POST)
	@ResponseBody
	public Object pullImageFileByBase64(@RequestParam String fileName, @RequestParam String fileContent) throws Exception {
		String url="";
		if (!fileContent.isEmpty()) {
			String[] str = fileContent.split(",");
			String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, imageFolderName);
			url = projectName + imageFolderName + "/" + fils;
		}
		return new ResponseBean(CommonConstant.SUCCESS_CODE_200, CommonConstant.MSG_SUCCESS, url);
	}

	/*@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public Object pullFile(MultipartFile file) throws Exception {
		FilesUploadModel fileModel = null;
		if (!file.isEmpty()) {
			fileModel = new FilesUploadModel(file.getOriginalFilename(), file.getInputStream(), projectName, fileFolderName);
			fileModel.writeFileByBinary();
		}
		return new ResponseBean(CommonConstant.SUCCESS_CODE_200, CommonConstant.MSG_SUCCESS, fileModel.getFileUri());
	}

	@RequestMapping(value = "/fileRemove", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteFile(String uri) throws Exception {
		FileHandler.deleteFile(uri, null, projectName);
		return new ResponseBean(CommonConstant.SUCCESS_CODE_200, CommonConstant.MSG_DELETE_SUCCESS);
	}*/

}
