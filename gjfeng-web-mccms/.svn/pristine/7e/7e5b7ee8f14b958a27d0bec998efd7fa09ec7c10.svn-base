package cc.messcat.service.system;

import java.util.List;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Standby;
import cc.messcat.gjfeng.common.bean.Pager;

public class StandbyManagerDaoImpl extends BaseManagerDaoImpl implements StandbyManagerDao {

	public void addStandby(Standby standby) {
		this.standbyDao.save(standby);
	}
	
	public void modifyStandby(Standby standby) {
		this.standbyDao.update(standby);
	}
	
	public void removeStandby(Standby standby) {
		this.standbyDao.delete(standby);
	}

	public void removeStandby(Long id) {
		this.standbyDao.delete(id);
	}
	
	public Standby retrieveStandby(Long id) {
		return (Standby) this.standbyDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllStandbys() {
		return this.standbyDao.findAll();
	}
	
	public Pager retrieveStandbysPager(int pageSize, int pageNo) {
		return this.standbyDao.getPager(pageSize, pageNo);
	}

	public Standby getStandbyById(Long id) {
		return standbyDao.getStandbyById(id);
	}

	public void update(Standby standby) {
		standbyDao.update(standby);
		
	}

	public Standby getFirstEntity() {
		return standbyDao.getFirstEntity();
	}

}