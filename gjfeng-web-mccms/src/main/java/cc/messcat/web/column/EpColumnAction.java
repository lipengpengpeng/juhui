package cc.messcat.web.column;

import java.io.File;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cc.messcat.entity.Authorities;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.front.PageTypeOut;
import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.modules.commons.PageAction;
import cc.modules.constants.ActionConstants;
import cc.modules.util.CommonUpload;

/**
 * @author Messcat
 * 
 */
public class EpColumnAction extends PageAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private EnterpriseColumn enterpriseColumn;
	private List<EnterpriseColumn> enterpriseColumnList;
	private String names;
	private String shortName;
	private String num;
	private String intro;
	private Long orderColumn;
	private String linkUrl;
	private String frontNum;
	private String numValidInRight;
	private String numValidInLeft;
	private Long id;
	
	//生成树列表
	private List<EnterpriseColumn> treeList;
	private String fatherName;

	private String nametemp;

	private Long father;

	private String columnName;
	//页面类型输出bean
	private PageTypeOut pageTypeOut;
	
	/**
	 * 以下为上传的文件名
	 */
	private CommonUpload comUpload = new CommonUpload(); //上传图片、视频通用类
	private String uploadFileName;
	private String upload1FileName;
	private File upload;
	private File upload1;
	
	private String savePath = "/upload/enterprice/";

	public EpColumnAction() {
	}

	/**
	 * 异步验证栏目名不能为重复
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkName() throws Exception {

		String event = "";

		HttpServletRequest request = ServletActionContext.getRequest();
		String names = request.getParameter("names");
		String orgName = request.getParameter("orgName");
		names = URLDecoder.decode(names, "UTF-8");
		orgName = URLDecoder.decode(orgName, "UTF-8");

		if (epColumnManagerDao.isNameUnique(names, orgName, Long.valueOf(columnName)))
			event = "true";
		else
			event = "false";

		return renderText(event);
	}

	public String execute() throws Exception {
		return ActionConstants.SUCCESS_KEY;
	}

	/**
	 * 查看栏目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		setEnterpriseColumn(epColumnManagerDao.getEnterpriseColumn(id));
		return ActionConstants.VIEW_KEY;
	}

	/**
	 * 添加栏目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		try {
			enterpriseColumn.setId(null);
			initEpColumn(enterpriseColumn);
			
			//新建产品图片
			setPic();
			
			if(uploadFileName != null)
			{
				comUpload.setUpload(upload);//上传的File文件
				comUpload.setUploadFileName(uploadFileName);//上传文件的文件名
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					enterpriseColumn.setPic1(CommonProperties.GJFENG_MCCMS_PROJECT_URL+savePath+comUpload.getUploadFileName());
				}
			}
			
			if(upload1FileName != null)
			{
				comUpload.setUpload(upload1);//上传的File文件
				comUpload.setUploadFileName(upload1FileName);//上传文件的文件名	
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					enterpriseColumn.setPic2(CommonProperties.GJFENG_MCCMS_PROJECT_URL+savePath+comUpload.getUploadFileName());
				}
			}
			
			epColumnManagerDao.saveEnterpriseColumn(enterpriseColumn);

			this.enterpriseColumn.setTypeColumn(pageTypeManagerDao.retrievePageType(enterpriseColumn.getTypeColumn().getId()));
			this.father = enterpriseColumn.getFather();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.query();
	}

	/**
	 * 修改栏目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		try {
			initEpColumn(enterpriseColumn);
			EnterpriseColumn temp = this.epColumnManagerDao.getEnterpriseColumn(enterpriseColumn.getId());
			
			//更新产品图片
			comUpload.setHandleType("update");
			setPic();
			
			if(uploadFileName != null)
			{
				comUpload.setUpload(upload);//上传的File文件
				comUpload.setUploadFileName(uploadFileName);//上传文件的文件名
				comUpload.setOldUploadFileName(temp.getPic1());//旧文件的文件名，填上将删除旧文件
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					enterpriseColumn.setPic1(CommonProperties.GJFENG_MCCMS_PROJECT_URL+savePath+comUpload.getUploadFileName());
				}
			}else {
				enterpriseColumn.setPic1(temp.getPic1());
			}
			
			if(upload1FileName != null)
			{
				comUpload.setUpload(upload1);//上传的File文件
				comUpload.setUploadFileName(upload1FileName);//上传文件的文件名
				comUpload.setOldUploadFileName(temp.getPic2());//旧文件的文件名，填上将删除旧文件	
				if(!comUpload.uploadFile()){
					return ActionConstants.INPUT_KEY;
				}else{
					enterpriseColumn.setPic2(CommonProperties.GJFENG_MCCMS_PROJECT_URL+savePath+comUpload.getUploadFileName());
				}
			}else {
				enterpriseColumn.setPic2(temp.getPic2());
			}
			
			/**
			 * fix by Andy
			 * 获取父级权限ID
			 * 2013/06/25
			 */
			Long fatherId = enterpriseColumn.getFather();
			this.father = enterpriseColumn.getFather();
			Long fatherAuthId;
			
			if(fatherId == 0L){
				fatherAuthId = 0L;
			}else{
				EnterpriseColumn ec = this.epColumnManagerDao.getEnterpriseColumn(fatherId);
				String fatherName = ec.getNames();
				Authorities fatherAuth = this.authoritiesManagerDao.getByNameAndType(fatherName, "1");
				fatherAuthId = fatherAuth.getId();
			}
			
			Authorities authorities = this.authoritiesManagerDao.getByName(temp.getNames());
			if (authorities != null) {
				authorities.setDisplayName(enterpriseColumn.getNames());
				authorities.setName(enterpriseColumn.getNames());
				authorities.setAuthoritiesId(fatherAuthId);
				epColumnManagerDao.updateEnterpriseColumn(enterpriseColumn, authorities);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError(e.getMessage());
		}
		return this.query();
	}

	/**
	 * 跳转到添加栏目页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add_page() throws Exception {
		this.setOrderColumn(epColumnManagerDao.getOrderColumnByFather(father));
		this.setPageTypeOut(pageTypeManagerDao.pageTypesToPageTypeOut(pageTypeManagerDao.retrieveAllPageTypes()));
		if(father==0){
			fatherName="总站";
		}else{
			fatherName = epColumnManagerDao.getEnterpriseColumn(father).getNames();
		}
		return ActionConstants.ADD_PAGE_KEY;
	}

	/**
	 * 跳转到编辑栏目页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		EnterpriseColumn enterpriseColumn = epColumnManagerDao.getEnterpriseColumn(id);
		this.setEnterpriseColumn(enterpriseColumn);
		this.setPageTypeOut(pageTypeManagerDao.pageTypesToPageTypeOut(pageTypeManagerDao.retrieveAllPageTypes()));
		
		if(enterpriseColumn.getFather()==null || enterpriseColumn.getFather()==0){
			fatherName="总站";
		}else{
			fatherName = epColumnManagerDao.getEnterpriseColumn(enterpriseColumn.getFather()).getNames();
		}
		father = enterpriseColumn.getFather();
		return ActionConstants.EDIT_KEY;
	}

	/**
	 * 删除栏目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		try {
			EnterpriseColumn temp = this.epColumnManagerDao.getEnterpriseColumn(id);
			epColumnManagerDao.deleteEnterpriseColumn(id);
			this.father = temp.getFather();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.query();
	}

	/**
	 * 查询栏目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception {
		String event = ActionConstants.SUCCESS_KEY;
		try {
			this.setEnterpriseColumnList(this.epColumnManagerDao.findTreeByFather(father));
			/**
			 * 如果栏目没有子栏目，则跳转到本栏目编辑页面，但如果是总站没有子栏目，则跳转到新增栏目页面
			 */
			if (null == this.getEnterpriseColumnList() || this.getEnterpriseColumnList().size() < 1) {
				if(father != 0){
					//如果没有子栏目的是其他栏目，就跳转到编辑页面
					this.id = father;
					event = this.edit();
				}else{
					//如果没有子栏目的是总站，则跳转到新增栏目页面
					event = this.add_page();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}

	/**
	 * 初始化对象信息
	 * 
	 * @param enterpriseColumn
	 */
	private void initEpColumn(EnterpriseColumn enterpriseColumn) {
		enterpriseColumn.setNames(names);
		enterpriseColumn.setShortName(shortName);
		enterpriseColumn.setNum(num);
		enterpriseColumn.setIntro(intro);
		enterpriseColumn.setOrderColumn(orderColumn);
		enterpriseColumn.setLinkUrl(linkUrl);
		enterpriseColumn.setFrontNum(frontNum);
	}

	/**
	 * 返回栏目树列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String generateTree() throws Exception {
		treeList = epColumnManagerDao.findAllEnterpriseColumn();
		if(id != null){
			Iterator<EnterpriseColumn> iterator = treeList.iterator();
			while(iterator.hasNext()){
				EnterpriseColumn epColumn = iterator.next();
				if(epColumn.getId().longValue() == this.id.longValue()){
					iterator.remove();
				}
			}
		}
		return "tree";
	}
	
	/**
	 * 设置上传图片通用信息
	 * @throws Exception
	 */
	private void setPic() throws Exception {
		comUpload.setMaxSize(comUpload.getPhotoMaxSize());//上传文件最大尺寸
		comUpload.setSavePath("upload/enterprice");//上传文件保存路径
		comUpload.setAllowTypes(comUpload.getAllowTypePhoto());//上传文件所允许的格式
	}

	public EnterpriseColumn getEnterpriseColumn() {
		return enterpriseColumn;
	}

	public void setEnterpriseColumn(EnterpriseColumn enterpriseColumn) {
		this.enterpriseColumn = enterpriseColumn;
	}

	public List getEnterpriseColumnList() {
		return enterpriseColumnList;
	}

	public void setEnterpriseColumnList(List enterpriseColumnList) {
		this.enterpriseColumnList = enterpriseColumnList;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(Long orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getFrontNum() {
		return frontNum;
	}

	public void setFrontNum(String frontNum) {
		this.frontNum = frontNum;
	}

	public String getNumValidInRight() {
		return numValidInRight;
	}

	public void setNumValidInRight(String numValidInRight) {
		this.numValidInRight = numValidInRight;
	}

	public String getNumValidInLeft() {
		return numValidInLeft;
	}

	public void setNumValidInLeft(String numValidInLeft) {
		this.numValidInLeft = numValidInLeft;
	}

	public Long getFather() {
		return father;
	}

	public void setFather(Long father) {
		this.father = father;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getNametemp() {
		return nametemp;
	}

	public void setNametemp(String nametemp) {
		this.nametemp = nametemp;
	}

	public PageTypeOut getPageTypeOut() {
		return pageTypeOut;
	}

	public void setPageTypeOut(PageTypeOut pageTypeOut) {
		this.pageTypeOut = pageTypeOut;
	}

	public List<EnterpriseColumn> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<EnterpriseColumn> treeList) {
		this.treeList = treeList;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUpload1FileName() {
		return upload1FileName;
	}

	public void setUpload1FileName(String upload1FileName) {
		this.upload1FileName = upload1FileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public File getUpload1() {
		return upload1;
	}

	public void setUpload1(File upload1) {
		this.upload1 = upload1;
	}
}