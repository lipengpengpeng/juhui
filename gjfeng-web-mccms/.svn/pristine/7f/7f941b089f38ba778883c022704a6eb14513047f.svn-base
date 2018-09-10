package cc.messcat.web.system;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cc.messcat.entity.Standby;
import cc.modules.commons.PageAction;
import cc.modules.constants.ActionConstants;
import cc.modules.constants.Constants;
import cc.modules.util.CommonUpload;

import com.opensymphony.xwork2.ActionContext;

public class StandbyAction extends PageAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Standby standby;
	
	private List<Standby> standbys;
	
	private CommonUpload comUpload = new CommonUpload();;
	private String uploadFileName;
	private String upload2FileName;
	private String upload3FileName;
	private String upload4FileName;
	private String upload5FileName;
	private String upload6FileName;
	private String upload7FileName;
//	private String upload8FileName;
//	private String upload9FileName;
//	private String upload10FileName;
	private File upload;
	private File upload2;
	private File upload3;
	private File upload4;
	private File upload5;
	private File upload6;
	private File upload7;
//	private File upload8;
//	private File upload9;
//	private File upload10;

	public String execute()throws Exception {
		super.pager = standbyManagerDao.retrieveStandbysPager(pageSize, pageNo);
		standbys = super.pager.getResultList();
		return "success";
		
	}
	
	public String editStandby() throws Exception {		
		ActionContext context=ActionContext.getContext();   
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);  
		Long id = Long.valueOf(request.getParameter("id"));
		standbys = standbyManagerDao.retrieveAllStandbys();
		return "editStandby";
	}
	
	
	public String update() throws Exception {
		try {
			Standby standby0 = this.standbyManagerDao.retrieveStandby(this.id);
			if(this.standby != null){
				standby0.setStandby1(this.standby.getStandby1());
				standby0.setStandby2(this.standby.getStandby2());
				standby0.setStandby3(this.standby.getStandby3());
				standby0.setStandby4(this.standby.getStandby4());
//				standby0.setStandby5(this.standby.getStandby5());
//				standby0.setStandby6(this.standby.getStandby6());
//				standby0.setStandby7(this.standby.getStandby7());
//				standby0.setStandby8(this.standby.getStandby8());
//				standby0.setStandby9(this.standby.getStandby9());
//				standby0.setStandby10(this.standby.getStandby10());
//				standby0.setStandby11(this.standby.getStandby11());
//				standby0.setStandby12(this.standby.getStandby12());
			}
			
			//更新产品图片
			comUpload.setHandleType("update");
			setPic();
			
			if(uploadFileName != null)
			{
				comUpload.setUpload(upload);//上传的File文件
				comUpload.setUploadFileName(uploadFileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto1());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto1(comUpload.getUploadFileName());
				}
			}
			
			if(upload2FileName != null)
			{
				comUpload.setUpload(upload2);//上传的File文件
				comUpload.setUploadFileName(upload2FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto2());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto2(comUpload.getUploadFileName());
				}
			}
			
			if(upload3FileName != null)
			{
				comUpload.setUpload(upload3);//上传的File文件
				comUpload.setUploadFileName(upload3FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto3());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto3(comUpload.getUploadFileName());
				}
			}
			
			
			
			if(upload4FileName != null)
			{
				comUpload.setUpload(upload4);//上传的File文件
				comUpload.setUploadFileName(upload4FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto4());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto4(comUpload.getUploadFileName());
				}
			}
			
			
		
			if(upload5FileName != null)
			{
				comUpload.setUpload(upload5);//上传的File文件
				comUpload.setUploadFileName(upload5FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto5());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto5(comUpload.getUploadFileName());
				}
			}
			
			if(upload6FileName != null)
			{
				comUpload.setUpload(upload6);//上传的File文件
				comUpload.setUploadFileName(upload6FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto6());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto6(comUpload.getUploadFileName());
				}
			}
			
			
							
			if(upload7FileName != null)
			{
				comUpload.setUpload(upload7);//上传的File文件
				comUpload.setUploadFileName(upload7FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto7());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto7(comUpload.getUploadFileName());
				}
			}
			
			/*
			if(upload8FileName != null)
			{
				comUpload.setUpload(upload8);//上传的File文件
				comUpload.setUploadFileName(upload8FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto8());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto8(comUpload.getUploadFileName());
				}
			}
			
			if(upload9FileName != null)
			{
				comUpload.setUpload(upload9);//上传的File文件
				comUpload.setUploadFileName(upload9FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto9());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto9(comUpload.getUploadFileName());
				}
			}
			
			if(upload10FileName != null)
			{
				comUpload.setUpload(upload10);//上传的File文件
				comUpload.setUploadFileName(upload10FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(standby0.getBgphoto10());
//				comUpload.setZipSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//压缩图片存放地方(前台不需要的话可以选用)
//				comUpload.setZipWidth(300);//压缩图片宽度
//				comUpload.setZipHeight(200);//压缩图片高度
					
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					standby0.setBgphoto10(comUpload.getUploadFileName());
				}
			}
			
			 */

			this.standbyManagerDao.modifyStandby(standby0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		
		try {
			super.pager = this.standbyManagerDao.retrieveStandbysPager(pageSize, pageNo);
			this.standbys = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		
		
		return this.execute();
	}
	
	/**
	 * 设置上传图片通用信息
	 * @throws Exception
	 */
	private void setPic() throws Exception {
		comUpload.setMaxSize(comUpload.getPhotoMaxSize());//上传文件最大尺寸
		comUpload.setSavePath(Constants.FILE_SEPARATOR+"upload"+Constants.FILE_SEPARATOR+"enterprice");//上传文件保存路径
		comUpload.setAllowTypes(comUpload.getAllowTypePhoto());//上传文件所允许的格式
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Standby getStandby() {
		return standby;
	}

	public void setStandby(Standby standby) {
		this.standby = standby;
	}

	public List<Standby> getStandbys() {
		return standbys;
	}

	public void setStandbys(
			List<Standby> standbys) {
		this.standbys = standbys;
	}

	public CommonUpload getComUpload() {
		return comUpload;
	}

	public void setComUpload(CommonUpload comUpload) {
		this.comUpload = comUpload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUpload2FileName() {
		return upload2FileName;
	}

	public void setUpload2FileName(String upload2FileName) {
		this.upload2FileName = upload2FileName;
	}

	public File getUpload2() {
		return upload2;
	}

	public void setUpload2(File upload2) {
		this.upload2 = upload2;
	}

	public String getUpload3FileName() {
		return upload3FileName;
	}

	public void setUpload3FileName(String upload3FileName) {
		this.upload3FileName = upload3FileName;
	}

	public File getUpload3() {
		return upload3;
	}

	public void setUpload3(File upload3) {
		this.upload3 = upload3;
	}

	public String getUpload4FileName() {
		return upload4FileName;
	}

	public void setUpload4FileName(String upload4FileName) {
		this.upload4FileName = upload4FileName;
	}

	public File getUpload4() {
		return upload4;
	}

	public void setUpload4(File upload4) {
		this.upload4 = upload4;
	}

	public String getUpload5FileName() {
		return upload5FileName;
	}

	public void setUpload5FileName(String upload5FileName) {
		this.upload5FileName = upload5FileName;
	}

	public String getUpload6FileName() {
		return upload6FileName;
	}

	public void setUpload6FileName(String upload6FileName) {
		this.upload6FileName = upload6FileName;
	}

	public File getUpload5() {
		return upload5;
	}

	public void setUpload5(File upload5) {
		this.upload5 = upload5;
	}

	public File getUpload6() {
		return upload6;
	}

	public void setUpload6(File upload6) {
		this.upload6 = upload6;
	}

	public void setUpload7FileName(String upload7FileName) {
		this.upload7FileName = upload7FileName;
	}

	public String getUpload7FileName() {
		return upload7FileName;
	}

	public void setUpload7(File upload7) {
		this.upload7 = upload7;
	}

	public File getUpload7() {
		return upload7;
	}
	

}