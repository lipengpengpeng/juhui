package cc.messcat.gjfeng.service.appupgrade;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfAppUpdateInfo;

public interface AppUpdateInfoService {
	
	/**
	 * 添加app更新信息
	 * @return
	 */
	public ResultVo addAppUpdateInfo(GjfAppUpdateInfo appUpdateInfo);
	
	/**
	 * 更新app版本信息
	 * @return
	 */
	public ResultVo updateAppUpGrade(GjfAppUpdateInfo appUpdateInfo);
	
	/**
	 * 删除app版本信息
	 * @param id
	 * @return
	 */
    public ResultVo deleteAppUpGradeInfo(Long id);
    
    /**
     * 获取全部app更新版本信息
     * @param pageNo
     * @param pageSizee
     * @return
     */
    public ResultVo findAllAppUpGradeInfo(Integer pageNo,Integer pageSizee);
    
    /**
     * 获取最新app版本信息
     * @param type
     * @return
     */
    public ResultVo findAppUpgradeByType(String type);

}
