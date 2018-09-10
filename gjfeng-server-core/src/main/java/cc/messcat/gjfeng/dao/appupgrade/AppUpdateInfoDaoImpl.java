package cc.messcat.gjfeng.dao.appupgrade;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;

@Repository("appUpdateInfoDao")
public class AppUpdateInfoDaoImpl extends BaseHibernateDaoImpl<Serializable> implements AppUpdateInfoDao {

}
