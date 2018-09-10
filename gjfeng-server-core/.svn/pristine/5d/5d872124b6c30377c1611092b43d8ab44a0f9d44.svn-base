package cc.messcat.gjfeng.service.appupgrade;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.appupgrade.AppUpdateInfoDao;
import cc.messcat.gjfeng.entity.GjfAppUpdateInfo;

@Service("appUpdateInfoService")
public class AppUpdateInfoServiceImpl implements AppUpdateInfoService{

	@Autowired
	@Qualifier("appUpdateInfoDao")
	private AppUpdateInfoDao appUpdateInfoDao;

    /**
     * 添加app更新版本信息
     */
	@Override
	public ResultVo addAppUpdateInfo(GjfAppUpdateInfo appUpdateInfo) {
		appUpdateInfoDao.save(appUpdateInfo);
		return new ResultVo(200,"添加成功",null);
	}

	/**
	 * 更新 app版本信息  
	 */
	@Override
	public ResultVo updateAppUpGrade(GjfAppUpdateInfo appUpdateInfo) {
		appUpdateInfoDao.update(appUpdateInfo);
		return new ResultVo(200,"更新成功",null);
	}

	/**
	 * 删除app版本信息
	 */
	@Override
	public ResultVo deleteAppUpGradeInfo(Long id) {
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("id", id);
		GjfAppUpdateInfo appInfo=appUpdateInfoDao.query(GjfAppUpdateInfo.class, attrs);
		appUpdateInfoDao.delete(appInfo);
		return new ResultVo(200,"删除成功",null);
	}

	/**
	 * 获取全部app版本信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAllAppUpGradeInfo(Integer pageNo, Integer pageSizee) {
		Map<String, Object> attrs=new HashMap<String, Object>();
		List<GjfAppUpdateInfo> list=appUpdateInfoDao.queryList(GjfAppUpdateInfo.class, "addTime", "desc", attrs);
		return new ResultVo(200,"查询成功",list);
	}

	/**
	 * 获取最新app版本信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAppUpgradeByType(String type) {
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("type", type);
		List<GjfAppUpdateInfo> list=appUpdateInfoDao.queryList(GjfAppUpdateInfo.class, "addTime", "desc", attrs);
		GjfAppUpdateInfo appUpgredeInfo=new GjfAppUpdateInfo();
		if(BeanUtil.isValid(list)){
			appUpgredeInfo=list.get(0);
		}
		return new ResultVo(200,"查询成功",appUpgredeInfo);
	}
}
