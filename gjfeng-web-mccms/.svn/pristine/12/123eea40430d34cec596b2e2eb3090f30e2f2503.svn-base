package cc.messcat.service.system;

import java.util.List;

import cc.messcat.entity.McParameter;
import cc.messcat.gjfeng.common.bean.Pager;

public interface McParameterManagerDao {

	public void addMcParameter(McParameter mcParameter);
	
	public void modifyMcParameter(McParameter mcParameter);
	
	public void removeMcParameter(McParameter mcParameter);
	
	public void removeMcParameter(Long id);
	
	public McParameter retrieveMcParameter(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllMcParameters();
	
	public Pager retrieveMcParametersPager(int pageSize, int pageNo);
	
	public List<McParameter> findAllWorkOkParameter();
}