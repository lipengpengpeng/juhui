package cc.messcat.service.data;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.BackupHistory;
import cc.messcat.gjfeng.common.bean.Pager;

public class DataHandlerManagerDaoImpl extends BaseManagerDaoImpl implements DataHandlerManagerDao {

	private static final long serialVersionUID = -9011648800165862044L;

	public BackupHistory getHistory(Long id) {
		return this.dataHandlerDao.get(id);
	}

	public void deleteHistory(BackupHistory backupHistory) {
		this.dataHandlerDao.delete(backupHistory);
	}

	public void deleteHistory(Long long1) {
		this.dataHandlerDao.delete(long1);
	}

	public Pager getAllBackupHistories(int pageSize, int pageNo) {
		return this.dataHandlerDao.getBackupHistoryPager(pageSize, pageNo);
	}

	public void saveHistory(BackupHistory backupHistory) {
		this.dataHandlerDao.save(backupHistory);
	}

	public void updateHistory(BackupHistory backupHistory) {
		this.dataHandlerDao.update(backupHistory);
	}

}
