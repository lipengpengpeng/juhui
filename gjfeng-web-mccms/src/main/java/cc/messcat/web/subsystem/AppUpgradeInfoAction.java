package cc.messcat.web.subsystem;

import java.util.Date;

import cc.messcat.gjfeng.entity.GjfAppUpdateInfo;
import cc.modules.commons.PageAction;

public class AppUpgradeInfoAction extends PageAction{	

	private static final long serialVersionUID = 2270558212912392151L;

	private GjfAppUpdateInfo appUpGradeInfo;

	/**
	 * 获取全部app版本信息
	 * @return
	 */
	public String findAllAppUpgradeInfo(){
		try{
			resultVo=appUpdateInfoDubbo.findAllAppUpGradeInfo();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "appUpgrade_list";
	}
	
	/**
	 * 跳转到新增app版本信息页面
	 * @return
	 */
	public String goNewInfoPage(){
		return "new_appgredeinfo";
	}
	
	/**
	 * 新增app版本信息
	 * @return
	 */
	public String newAppUpgredeInfo(){
		try{
			appUpGradeInfo.setAddTime(new Date());
			resultVo=appUpdateInfoDubbo.addAppUpGradeInfo(appUpGradeInfo);
		}catch(Exception e){
		   e.printStackTrace();	
		}
		return findAllAppUpgradeInfo();
	}
	
	/**
	 * 
	 * 删除版本信息
	 * @return
	 */
	public String deleteAppUpGredeInfo(){
		try{
			appUpdateInfoDubbo.deleteUpGradeInfo(appUpGradeInfo.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return findAllAppUpgradeInfo();
	}

	
	
	public GjfAppUpdateInfo getAppUpGradeInfo() {
		return appUpGradeInfo;
	}

	public void setAppUpGradeInfo(GjfAppUpdateInfo appUpGradeInfo) {
		this.appUpGradeInfo = appUpGradeInfo;
	}

	
	
}
