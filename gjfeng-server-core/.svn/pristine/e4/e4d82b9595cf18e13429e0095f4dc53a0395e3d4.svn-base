package cc.messcat.gjfeng.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfAppUpdateInfoDubbo;
import cc.messcat.gjfeng.entity.GjfAppUpdateInfo;
import cc.messcat.gjfeng.service.appupgrade.AppUpdateInfoService;

public class GjfAppUpdateInfoDubboImpl implements GjfAppUpdateInfoDubbo{

	@Autowired
	@Qualifier("appUpdateInfoService")
	private AppUpdateInfoService appUpdateInfoService;

	
	@Override
	public ResultVo addAppUpGradeInfo(GjfAppUpdateInfo appUpGradeInfo) {
		
		return appUpdateInfoService.addAppUpdateInfo(appUpGradeInfo);
	}

	@Override
	public ResultVo updateUpGradeInfo(GjfAppUpdateInfo appUpGradeInfo) {
		
		return appUpdateInfoService.updateAppUpGrade(appUpGradeInfo);
	}

	@Override
	public ResultVo deleteUpGradeInfo(Long id) {
		
		return appUpdateInfoService.deleteAppUpGradeInfo(id);
	}

	@Override
	public ResultVo findAllAppUpGradeInfo() {
		
		return appUpdateInfoService.findAllAppUpGradeInfo(1, 10);
	}

	@Override
	public ResultVo findAppUpGradeByType(String type) {
		
		return appUpdateInfoService.findAppUpgradeByType(type);
	}

}
