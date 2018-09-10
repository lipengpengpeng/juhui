package cc.messcat.bases;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.benefit.GjfBenefitInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.CountInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfAppUpdateInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfEnterpriseColumnDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductAttrDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductAttrStockDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductCommentDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfStoreInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;
import cc.messcat.service.collection.EpNewsManagerDao;
import cc.messcat.service.collection.McProductInfoManagerDao;
import cc.messcat.service.collection.ProductColumnManagerDao;
import cc.messcat.service.column.EpColumnManagerDao;
import cc.messcat.service.data.DataHandlerManagerDao;
import cc.messcat.service.dynamic.EpAdManagerDao;
import cc.messcat.service.dynamic.EpLinksManagerDao;
import cc.messcat.service.store.GjfStoreInfoManagerDao;
import cc.messcat.service.style.WebSkinColorManagerDao;
import cc.messcat.service.style.WebSkinManagerDao;
import cc.messcat.service.system.AlterUrlManagerDao;
import cc.messcat.service.system.AuthoritiesManagerDao;
import cc.messcat.service.system.McParameterManagerDao;
import cc.messcat.service.system.PageTypeManagerDao;
import cc.messcat.service.system.RolesAuthoritiesManagerDao;
import cc.messcat.service.system.RolesManagerDao;
import cc.messcat.service.system.StandbyManagerDao;
import cc.messcat.service.system.UsersManagerDao;
import cc.messcat.service.system.UsersRolesManagerDao;
import cc.messcat.service.system.WebSiteManagerDao;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	protected UsersManagerDao usersManagerDao;
	protected RolesManagerDao rolesManagerDao;
	protected UsersRolesManagerDao usersRolesManagerDao;
	protected AuthoritiesManagerDao authoritiesManagerDao;
	protected RolesAuthoritiesManagerDao rolesAuthoritiesManagerDao;
	protected EpColumnManagerDao epColumnManagerDao;
	protected EpNewsManagerDao epNewsManagerDao;
	protected EpAdManagerDao epAdManagerDao;
	protected EpLinksManagerDao epLinksManagerDao;
	protected WebSiteManagerDao webSiteManagerDao;
	protected WebSkinManagerDao webSkinManagerDao;
	protected WebSkinColorManagerDao webSkinColorManagerDao;
	protected DataHandlerManagerDao dataHandlerManagerDao;
	protected GjfStoreInfoManagerDao gjfStoreInfoManagerDao;
	//备用字段模块
	protected StandbyManagerDao standbyManagerDao;
	//产品模块
	protected McParameterManagerDao mcParameterManagerDao;
	protected McProductInfoManagerDao mcProductInfoManagerDao;
	//页面类型模块
	protected PageTypeManagerDao pageTypeManagerDao;
	//批量修改URL模块
	protected AlterUrlManagerDao alterUrlManagerDao;
	//产品-栏目对应表
	protected ProductColumnManagerDao productColumnManagerDao;
	
	//子项目二次开发
	protected GjfAddressDubbo addressDubbo;
	protected GjfEnterpriseColumnDubbo enterpriseColumnDubbo;
	protected GjfMemberInfoDubbo memberInfoDubbo;
	protected GjfOrderInfoDubbo orderInfoDubbo;
	protected GjfProductAttrDubbo productAttrDubbo;
	protected GjfProductInfoDubbo productInfoDubbo;
	protected GjfStoreInfoDubbo storeInfoDubbo;
	protected GjfTradeDubbo tradeDubbo;
	protected GjfProductAttrStockDubbo productAttrStockDubbo;
	protected GjfBenefitInfoDubbo benefitInfoDubbo;
	protected CountInfoDubbo countInfoDubbo;
	protected GjfProductCommentDubbo prodcutCommetDubbo;

	protected GjfAppUpdateInfoDubbo appUpdateInfoDubbo;
	

	protected ResultVo resultVo;
	
	public ResultVo getResultVo() {
		return resultVo;
	}

	public void setResultVo(ResultVo resultVo) {
		this.resultVo = resultVo;
	}

	public BaseAction() {
	}
	
	//不返回视图，直接输出结果
	protected String renderText(String text) {
		return render(text, "text/plain;charset=UTF-8");
	}
	
	public void printToJson(Object obj) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=UTF-8;no-cache"); // 目的是为了控制浏览器的行为，即控制浏览器用UTF-8进行解码
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(obj));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.flush();
				out.close();
			}
		}
	}

	protected String render(String text, String contentType) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException ioexception) {
		}
		return null;
	}

	public UsersManagerDao getUsersManagerDao() {
		return usersManagerDao;
	}

	public void setUsersManagerDao(UsersManagerDao usersManagerDao) {
		this.usersManagerDao = usersManagerDao;
	}

	public RolesManagerDao getRolesManagerDao() {
		return rolesManagerDao;
	}

	public void setRolesManagerDao(RolesManagerDao rolesManagerDao) {
		this.rolesManagerDao = rolesManagerDao;
	}

	public UsersRolesManagerDao getUsersRolesManagerDao() {
		return usersRolesManagerDao;
	}

	public void setUsersRolesManagerDao(UsersRolesManagerDao usersRolesManagerDao) {
		this.usersRolesManagerDao = usersRolesManagerDao;
	}

	public AuthoritiesManagerDao getAuthoritiesManagerDao() {
		return authoritiesManagerDao;
	}

	public void setAuthoritiesManagerDao(AuthoritiesManagerDao authoritiesManagerDao) {
		this.authoritiesManagerDao = authoritiesManagerDao;
	}

	public RolesAuthoritiesManagerDao getRolesAuthoritiesManagerDao() {
		return rolesAuthoritiesManagerDao;
	}

	public void setRolesAuthoritiesManagerDao(
			RolesAuthoritiesManagerDao rolesAuthoritiesManagerDao) {
		this.rolesAuthoritiesManagerDao = rolesAuthoritiesManagerDao;
	}

	public EpColumnManagerDao getEpColumnManagerDao() {
		return epColumnManagerDao;
	}

	public void setEpColumnManagerDao(EpColumnManagerDao epColumnManagerDao) {
		this.epColumnManagerDao = epColumnManagerDao;
	}

	public EpNewsManagerDao getEpNewsManagerDao() {
		return epNewsManagerDao;
	}

	public void setEpNewsManagerDao(EpNewsManagerDao epNewsManagerDao) {
		this.epNewsManagerDao = epNewsManagerDao;
	}

	public EpAdManagerDao getEpAdManagerDao() {
		return epAdManagerDao;
	}

	public void setEpAdManagerDao(EpAdManagerDao epAdManagerDao) {
		this.epAdManagerDao = epAdManagerDao;
	}

	public EpLinksManagerDao getEpLinksManagerDao() {
		return epLinksManagerDao;
	}

	public void setEpLinksManagerDao(EpLinksManagerDao epLinksManagerDao) {
		this.epLinksManagerDao = epLinksManagerDao;
	}

	public WebSiteManagerDao getWebSiteManagerDao() {
		return webSiteManagerDao;
	}

	public void setWebSiteManagerDao(WebSiteManagerDao webSiteManagerDao) {
		this.webSiteManagerDao = webSiteManagerDao;
	}

	public WebSkinManagerDao getWebSkinManagerDao() {
		return webSkinManagerDao;
	}

	public void setWebSkinManagerDao(WebSkinManagerDao webSkinManagerDao) {
		this.webSkinManagerDao = webSkinManagerDao;
	}

	public WebSkinColorManagerDao getWebSkinColorManagerDao() {
		return webSkinColorManagerDao;
	}

	public void setWebSkinColorManagerDao(
			WebSkinColorManagerDao webSkinColorManagerDao) {
		this.webSkinColorManagerDao = webSkinColorManagerDao;
	}

	public DataHandlerManagerDao getDataHandlerManagerDao() {
		return dataHandlerManagerDao;
	}

	public void setDataHandlerManagerDao(DataHandlerManagerDao dataHandlerManagerDao) {
		this.dataHandlerManagerDao = dataHandlerManagerDao;
	}

	public StandbyManagerDao getStandbyManagerDao() {
		return standbyManagerDao;
	}

	public void setStandbyManagerDao(StandbyManagerDao standbyManagerDao) {
		this.standbyManagerDao = standbyManagerDao;
	}

	public McParameterManagerDao getMcParameterManagerDao() {
		return mcParameterManagerDao;
	}

	public void setMcParameterManagerDao(McParameterManagerDao mcParameterManagerDao) {
		this.mcParameterManagerDao = mcParameterManagerDao;
	}

	public McProductInfoManagerDao getMcProductInfoManagerDao() {
		return mcProductInfoManagerDao;
	}

	public void setMcProductInfoManagerDao(
			McProductInfoManagerDao mcProductInfoManagerDao) {
		this.mcProductInfoManagerDao = mcProductInfoManagerDao;
	}

	public PageTypeManagerDao getPageTypeManagerDao() {
		return pageTypeManagerDao;
	}

	public void setPageTypeManagerDao(PageTypeManagerDao pageTypeManagerDao) {
		this.pageTypeManagerDao = pageTypeManagerDao;
	}

	public AlterUrlManagerDao getAlterUrlManagerDao() {
		return alterUrlManagerDao;
	}

	public void setAlterUrlManagerDao(AlterUrlManagerDao alterUrlManagerDao) {
		this.alterUrlManagerDao = alterUrlManagerDao;
	}

	public ProductColumnManagerDao getProductColumnManagerDao() {
		return productColumnManagerDao;
	}

	public void setProductColumnManagerDao(
			ProductColumnManagerDao productColumnManagerDao) {
		this.productColumnManagerDao = productColumnManagerDao;
	}

	public GjfAddressDubbo getAddressDubbo() {
		return addressDubbo;
	}

	public void setAddressDubbo(GjfAddressDubbo addressDubbo) {
		this.addressDubbo = addressDubbo;
	}

	public GjfEnterpriseColumnDubbo getEnterpriseColumnDubbo() {
		return enterpriseColumnDubbo;
	}

	public void setEnterpriseColumnDubbo(GjfEnterpriseColumnDubbo enterpriseColumnDubbo) {
		this.enterpriseColumnDubbo = enterpriseColumnDubbo;
	}

	public GjfMemberInfoDubbo getMemberInfoDubbo() {
		return memberInfoDubbo;
	}

	public void setMemberInfoDubbo(GjfMemberInfoDubbo memberInfoDubbo) {
		this.memberInfoDubbo = memberInfoDubbo;
	}

	public GjfOrderInfoDubbo getOrderInfoDubbo() {
		return orderInfoDubbo;
	}

	public void setOrderInfoDubbo(GjfOrderInfoDubbo orderInfoDubbo) {
		this.orderInfoDubbo = orderInfoDubbo;
	}

	public GjfProductAttrDubbo getProductAttrDubbo() {
		return productAttrDubbo;
	}

	public void setProductAttrDubbo(GjfProductAttrDubbo productAttrDubbo) {
		this.productAttrDubbo = productAttrDubbo;
	}

	public GjfProductInfoDubbo getProductInfoDubbo() {
		return productInfoDubbo;
	}

	public void setProductInfoDubbo(GjfProductInfoDubbo productInfoDubbo) {
		this.productInfoDubbo = productInfoDubbo;
	}

	public GjfStoreInfoDubbo getStoreInfoDubbo() {
		return storeInfoDubbo;
	}

	public void setStoreInfoDubbo(GjfStoreInfoDubbo storeInfoDubbo) {
		this.storeInfoDubbo = storeInfoDubbo;
	}

	public GjfTradeDubbo getTradeDubbo() {
		return tradeDubbo;
	}

	public void setTradeDubbo(GjfTradeDubbo tradeDubbo) {
		this.tradeDubbo = tradeDubbo;
	}

	public GjfBenefitInfoDubbo getBenefitInfoDubbo() {
		return benefitInfoDubbo;
	}

	public void setBenefitInfoDubbo(GjfBenefitInfoDubbo benefitInfoDubbo) {
		this.benefitInfoDubbo = benefitInfoDubbo;
	}

	public GjfStoreInfoManagerDao getGjfStoreInfoManagerDao() {
		return gjfStoreInfoManagerDao;
	}

	public void setGjfStoreInfoManagerDao(GjfStoreInfoManagerDao gjfStoreInfoManagerDao) {
		this.gjfStoreInfoManagerDao = gjfStoreInfoManagerDao;
	}

	public GjfProductAttrStockDubbo getProductAttrStockDubbo() {
		return productAttrStockDubbo;
	}

	public void setProductAttrStockDubbo(GjfProductAttrStockDubbo productAttrStockDubbo) {
		this.productAttrStockDubbo = productAttrStockDubbo;
	}

	public CountInfoDubbo getCountInfoDubbo() {
		return countInfoDubbo;
	}

	public void setCountInfoDubbo(CountInfoDubbo countInfoDubbo) {
		this.countInfoDubbo = countInfoDubbo;
	}

	public GjfProductCommentDubbo getProdcutCommetDubbo() {
		return prodcutCommetDubbo;
	}

	public void setProdcutCommetDubbo(GjfProductCommentDubbo prodcutCommetDubbo) {
		this.prodcutCommetDubbo = prodcutCommetDubbo;
	}

	public GjfAppUpdateInfoDubbo getAppUpdateInfoDubbo() {
		return appUpdateInfoDubbo;
	}

	public void setAppUpdateInfoDubbo(GjfAppUpdateInfoDubbo appUpdateInfoDubbo) {
		this.appUpdateInfoDubbo = appUpdateInfoDubbo;
	}

	

}