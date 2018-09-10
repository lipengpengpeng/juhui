package cc.messcat.dao.system;

import java.util.List;

import cc.messcat.entity.McParameter;
import cc.messcat.gjfeng.common.bean.Pager;

public interface McParameterDao {

	public void save(McParameter mcParameter);
	
	public void update(McParameter mcParameter);
	
	public void delete(McParameter mcParameter);
	
	public void delete(Long id);
	
	public McParameter get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);
	
	public List<McParameter> findAllWorkOkParameter();

}