package cc.messcat.web.dynamic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cc.messcat.entity.EnterpriseAd;
import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.modules.commons.PageAction;
import cc.modules.constants.ActionConstants;
import cc.modules.constants.Constants;
import cc.modules.util.DateHelper;

/**
 * @author Messcat
 * @version 1.1
 */
public class EpAdAction extends PageAction {
	
	
	private static final long serialVersionUID = 1L;

	private List<EnterpriseAd> enterpriseAdList;
	private EnterpriseAd enterpriseAd;
	private Long id;
	private String names;
	private String frontNum;
	private Long orderColumn;
	private String address;
	private String intro;
	private Double acost;
	private String clientName;
	private Date initTime;
	private Date endTime;
	private File upload;
	private String uploadFileName;
	private String allowTypes;
	private String uploadContentType;
	private String oldUploadFileName;

	/**
	 * 默认构造方法
	 */
	public EpAdAction() {
		enterpriseAd = new EnterpriseAd();
	}

	/**
	 * 分页查询广告信息
	 */
	public String execute() throws Exception {
		super.pager = epAdManagerDao.findEnterpriseAd(pageSize, pageNo, "-1");
		enterpriseAdList = super.pager.getResultList();
		return ActionConstants.SUCCESS_KEY;
	}

	/**
	 * 查看一条广告信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		enterpriseAd = epAdManagerDao.getEnterpriseAd(id);
		return ActionConstants.VIEW_KEY;
	}

	/**
	 * 查看一条广告信息，并跳转到编辑页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		setEnterpriseAd(epAdManagerDao.getEnterpriseAd(id));
		return ActionConstants.EDIT_KEY;
	}

	/**
	 * 更改一条广告信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String event = "";
		try {
			inintenterpriseAd();
			if (!uploadPhoto("update")) {
				event = ActionConstants.INPUT_KEY;
			} else {
				if (null == upload) {
					EnterpriseAd old  = epAdManagerDao.getEnterpriseAd(id);
					enterpriseAd.setPhoto(old.getPhoto());
				}
				enterpriseAd.setId(id);
				enterpriseAd.setEditTime(new Date());
				epAdManagerDao.updateEnterpriseAd(enterpriseAd);
				event = ActionConstants.DO_SUCCESS_KEY;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add_page() throws Exception {
		if (this.enterpriseAd != null)
			this.setEnterpriseAd(null);
		return ActionConstants.ADD_PAGE_KEY;
	}

	/**
	 * 添加广告信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		String event = "";
		inintenterpriseAd();
		if (!uploadPhoto("add")) {
			event = ActionConstants.INPUT_KEY;
		} else {

			enterpriseAd.setEditTime(new Date());
			epAdManagerDao.addEnterpriseAd(enterpriseAd);
			event = ActionConstants.DO_SUCCESS_KEY;
		}
		return event;
	}

	/**
	 * 删除一条广告信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		epAdManagerDao.deleteEnterpriseAd(id);
		return ActionConstants.DO_SUCCESS_KEY;
	}

	/**
	 * 初始化广告信息
	 */
	public void inintenterpriseAd() {
		enterpriseAd.setNames(names);
		enterpriseAd.setFrontNum(frontNum);
		enterpriseAd.setOrderColumn(orderColumn);
		enterpriseAd.setAddress(address);
		enterpriseAd.setIntro(intro);
		enterpriseAd.setAcost(acost);
		enterpriseAd.setClientName(clientName);
		enterpriseAd.setInitTime(initTime);
		enterpriseAd.setEndTime(endTime);
	}

	public Long getId() {
		return id;
	}

	public List<EnterpriseAd> getEnterpriseAdList() {
		return enterpriseAdList;
	}

	public void setEnterpriseAdList(List<EnterpriseAd> enterpriseAdList) {
		this.enterpriseAdList = enterpriseAdList;
	}

	public EnterpriseAd getEnterpriseAd() {
		return enterpriseAd;
	}

	public void setEnterpriseAd(EnterpriseAd enterpriseAd) {
		this.enterpriseAd = enterpriseAd;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Double getAcost() {
		return acost;
	}

	public void setAcost(Double acost) {
		this.acost = acost;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getInitTime() {
		return initTime;
	}

	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	private boolean checkUploadPhotoSize() throws Exception {
		long maxSize = 0x100000L;
		if (getUpload() != null && getUpload().length() > maxSize) {
			addActionMessage("\u4E0A\u4F20\u7684\u6587\u4EF6\u7684\u5BB9\u91CF\u4E0D\u80FD\u5927\u4E8E1MB\uFF01");
			return false;
		} else {
			return true;
		}
	}

	private String filterType(String types[]) {
		if (getUploadContentType() == null)
			return null;
		String fileType = getUploadContentType();
		String as[];
		int j = (as = types).length;
		for (int i = 0; i < j; i++) {
			String type = as[i];
			if (type.equals(fileType))
				return null;
		}

		return "FilterError";
	}

	private String getAllowTypes() {
		allowTypes = "image/bmp,image/x-png,image/png,image/gif,image/pjpeg,image/jpeg";
		return allowTypes;
	}

	private boolean checkUploadPhoto() throws Exception {
		String filterResult = filterType(getAllowTypes().split(","));
		if (filterResult != null) {
			addActionMessage((new StringBuilder(
				"\u60A8\u8981\u4E0A\u4F20\u7684\u6587\u4EF6\u7C7B\u578B\u4E0D\u6B63\u786E\uFF01<br>\u5141\u8BB8\u4E0A\u4F20\u7684\u6587\u4EF6\u7C7B\u578B\u4E3A\uFF1A"))
				.append(getAllowTypes()).toString());
			return false;
		} else {
			return checkUploadPhotoSize();
		}
	}

	@SuppressWarnings("unused")
	private boolean uploadPhoto(String how_do) throws Exception {
		if (!checkUploadPhoto())
			return false;
		String oldPhoto = null;

		FileOutputStream fos = null;
		FileInputStream fis = null;
		if (enterpriseAd.getId() != null && id == null)
			id = enterpriseAd.getId();
		if ("update".equals(how_do))
			oldPhoto = epAdManagerDao.getEnterpriseAd(id).getPhoto();
		if (uploadFileName != null && !uploadFileName.equals("")) {
			try {
				String inputFileExt = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
				String newFileName = (new StringBuffer(String.valueOf((new DateHelper()).getRandomNum()))).append(".").append(
					inputFileExt).toString();
				String outputFilePath = (new StringBuffer(String.valueOf(getSavePath()))).append(Constants.FILE_SEPARATOR).append(newFileName)
					.toString();
				//enterpriseAd.setPhoto(newFileName);
				//2017/1/20
				String fileName = CommonProperties.GJFENG_MCCMS_PROJECT_URL+"/upload/enterprice/"+newFileName;
				enterpriseAd.setPhoto(fileName);
				
				
			  fos = new FileOutputStream(outputFilePath);
				fis = new FileInputStream(getUpload());
				byte buffer[] = new byte[1024];
				for (int len = 0; (len = fis.read(buffer)) > 0;)
					fos.write(buffer, 0, len);

				File delFile = new File((new StringBuffer(String.valueOf(getSavePath()))).append(Constants.FILE_SEPARATOR).append(oldPhoto).toString());
				if (delFile.exists()) {
					try {
						delFile.delete();
					} catch (Exception e) {
						return false;
					}
				}
			} catch (Exception e) {
				addActionMessage("\u4E0A\u4F20\u5931\u8D25!");
				e.printStackTrace();
				return false;
			} finally {
				fos.close();
				fis.close();
			}
		} else if (oldUploadFileName != null && !"".equals(oldUploadFileName.trim())) {
			enterpriseAd.setPhoto(oldUploadFileName);
		}
		// 清除缓存
		this.uploadFileName = null;
		return true;
	}

	@SuppressWarnings("deprecation")
	private String getSavePath() throws Exception {
		String realPath = ServletActionContext.getRequest().getRealPath("/upload/enterprice/");
		//String realPath = "/upload/enterprice/";
		return realPath;
	}

	public File getUpload() {
		return upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrontNum() {
		return frontNum;
	}

	public void setFrontNum(String frontNum) {
		this.frontNum = frontNum;
	}

	public Long getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(Long orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOldUploadFileName() {
		return oldUploadFileName;
	}

	public void setOldUploadFileName(String oldUploadFileName) {
		this.oldUploadFileName = oldUploadFileName;
	}
}