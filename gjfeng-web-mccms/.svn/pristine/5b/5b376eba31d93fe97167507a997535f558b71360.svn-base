package cc.messcat.service.system;

import java.util.List;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.McParameter;
import cc.messcat.gjfeng.common.bean.Pager;

public class McParameterManagerDaoImpl extends BaseManagerDaoImpl implements McParameterManagerDao {

	public void addMcParameter(McParameter mcParameter) {
		this.mcParameterDao.save(mcParameter);
	}
	
	public void modifyMcParameter(McParameter mcParameter) {
		this.mcParameterDao.update(mcParameter);
	}
	
	public void removeMcParameter(McParameter mcParameter) {
		this.mcParameterDao.delete(mcParameter);
	}

	public void removeMcParameter(Long id) {
		this.mcParameterDao.delete(id);
	}
	
	public McParameter retrieveMcParameter(Long id) {
		return (McParameter) this.mcParameterDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllMcParameters() {
		return this.mcParameterDao.findAll();
	}
	
	public Pager retrieveMcParametersPager(int pageSize, int pageNo) {
		return this.mcParameterDao.getPager(pageSize, pageNo);
	}

	public List<McParameter> findAllWorkOkParameter(){
		return this.mcParameterDao.findAllWorkOkParameter();
	}
}