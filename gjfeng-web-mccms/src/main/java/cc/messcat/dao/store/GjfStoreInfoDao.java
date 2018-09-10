package cc.messcat.dao.store;

import java.util.List;

import cc.messcat.entity.GjfStoreInfo;
import cc.messcat.gjfeng.common.bean.Pager;

public interface GjfStoreInfoDao {
	
	public void save(GjfStoreInfo gjfStoreInfo);
	
	public void update(GjfStoreInfo gjfStoreInfo);
	
	public void delete(GjfStoreInfo gjfStoreInfo);
	
	public void delete(Long id);
	
	public GjfStoreInfo get(Long id);
	
	@SuppressWarnings("unchecked")
	public List findAll();
	
	public Pager getPager(int pageSize, int pageNo);

	public GjfStoreInfo findSelfSupport();
}
