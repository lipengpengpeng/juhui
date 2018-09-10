package cc.messcat.web.data;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import cc.modules.commons.PageAction;
import cc.modules.constants.Constants;
import cc.modules.util.FileHandler;
import cc.modules.util.ZipCompressor;
import cc.messcat.entity.BackupHistory;

public class DataHandlerAction extends PageAction {

	private static final long serialVersionUID = 1047529336193894118L;

	/**
	 * 0代表已删除文件
	 */
	private static final String STATUS_DISABLE = "0";

	/**
	 * 1代表文件存在
	 */
	private static final String STATUS_ENABLE = "1";

	private static final String SLASH = Constants.FILE_SEPARATOR;

	private Long id;

	private List<BackupHistory> historyList;

	/**
	 * 备份信息，从jdbc.properties获取
	 */
	private String jdbcHostIp;
	private String jdbcDatabaseName;
	private String jdbcUser;
	private String jdbcPassword;
	private String jdbcMysqlPath;
	private String jdbcBackupPath;

	/**
	 * 错误信息标志位
	 */
	public boolean showMesscat = false;

	@SuppressWarnings("unchecked")
	public String showHistory() throws Exception {

		try {

			if (this.historyList != null) {
				this.historyList = null;
			}

			super.pager = this.dataHandlerManagerDao.getAllBackupHistories(pageSize, pageNo);
			this.historyList = super.pager.getResultList();

		} catch (Exception ex) {
			addActionMessage("页面加载出错!");
		}

		return Constants.SUCCESS;

	}

	/**
	 * 这里delete只删除文件，不删除数据库里的记录，只是将状态设置为1
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {

		try {

			this.showMesscat = false;
			final BackupHistory backupHistory = this.dataHandlerManagerDao.getHistory(this.id);
			FileHandler.deleteFile(this.getFullPath(backupHistory.getPath()));
			backupHistory.setStatus(DataHandlerAction.STATUS_DISABLE);
			this.dataHandlerManagerDao.updateHistory(backupHistory);
			this.showMesscat = true;

			addActionMessage("删除成功！");

		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("删除失败！");
		}

		return this.showHistory();

	}

	public String backupDatabase() throws Exception {

		try {

			this.showMesscat = false;

			//开始备份
			final String type = "数据库备份";
			final String namePrefix = "数据库脚本_";
			final String backupFileExt = ".sql";
			final BackupHistory backupHistory = this.getBackupHistory(namePrefix, backupFileExt, type);
			final String fullPath = this.getFullPath(backupHistory.getPath());
			final String enCode = "utf8";

			//调用mysql的cmd
			Runtime rt = Runtime.getRuntime();
			final String command = this.jdbcMysqlPath + " -h" + this.jdbcHostIp + " -u" + this.jdbcUser + " -p" + this.jdbcPassword
				+ " " + this.jdbcDatabaseName;

			final Process child = rt.exec(command);
			final InputStream in = child.getInputStream();

			//组合控制台输出信息字符串
			final String outStr = FileHandler.convertInputStreamToString(in, enCode);
			if (outStr == null || outStr.length() == 0) {
				throw new Exception("数据库备份配置文件错误！");
			}

			//新建文件夹和文件
			FileHandler.createNewFile(fullPath);

			//把内容装入文件里
			FileHandler.writeStringToFile(fullPath, outStr, enCode);

			//保存备份记录
			this.dataHandlerManagerDao.saveHistory(backupHistory);

			addActionMessage("备份数据库完成！");
			this.showMesscat = true;

		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("备份数据库失败！");
			ex.printStackTrace();
		}

		return this.showHistory();

	}

	public String backupFile() throws Exception {

		try {

			this.showMesscat = false;

			//开始备份
			final String type = "数据文件备份";
			final String namePrefix = "数据文件_";
			final String backupFileExt = ".zip";
			final BackupHistory backupHistory = this.getBackupHistory(namePrefix, backupFileExt, type);

			//组合要压缩的文件夹和路径
			final String srcPath = ServletActionContext.getServletContext().getRealPath(DataHandlerAction.SLASH);
			String srcPath1 = srcPath.replace("\\", DataHandlerAction.SLASH);
			while (srcPath1.endsWith(DataHandlerAction.SLASH)) {
				srcPath1 = srcPath1.substring(0, srcPath1.length() - 1);
			}

			//指定要压缩的目录
			final String[] folders = { "input", "output", "upload", "userfiles" };

			//压缩文件
			final String fullPath = this.getFullPath(backupHistory.getPath());
			ZipCompressor.compress(srcPath1, fullPath, folders);

			//保存备份记录
			this.dataHandlerManagerDao.saveHistory(backupHistory);
			addActionMessage("备份数据文件完成！");
			this.showMesscat = true;

		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("备份数据文件失败！");
		}

		return this.showHistory();

	}

	private BackupHistory getBackupHistory(final String namePrefix, final String backupFileExt, final String type) {

		final BackupHistory backupHistory = new BackupHistory();
		backupHistory.setDateTime(new Date());
		backupHistory.setType(type);
		backupHistory.setStatus(DataHandlerAction.STATUS_ENABLE);

		// 使用日期组合文件名和文件路径
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		final String dateTimeStr = formatter.format(backupHistory.getDateTime());
		backupHistory.setName(namePrefix + dateTimeStr);
		backupHistory.setPath(dateTimeStr + backupFileExt);

		return backupHistory;

	}

	private String getFullPath(String path) {
		return this.jdbcBackupPath + DataHandlerAction.SLASH + path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<BackupHistory> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<BackupHistory> historyList) {
		this.historyList = historyList;
	}

	public String getJdbcHostIp() {
		return jdbcHostIp;
	}

	public void setJdbcHostIp(String jdbcHostIp) {
		this.jdbcHostIp = jdbcHostIp;
	}

	public String getJdbcDatabaseName() {
		return jdbcDatabaseName;
	}

	public void setJdbcDatabaseName(String jdbcDatabaseName) {
		this.jdbcDatabaseName = jdbcDatabaseName;
	}

	public String getJdbcUser() {
		return jdbcUser;
	}

	public void setJdbcUser(String jdbcUser) {
		this.jdbcUser = jdbcUser;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	public String getJdbcMysqlPath() {
		return jdbcMysqlPath;
	}

	public void setJdbcMysqlPath(String jdbcMysqlPath) {
		this.jdbcMysqlPath = jdbcMysqlPath;
	}

	public String getJdbcBackupPath() {
		return jdbcBackupPath;
	}

	public void setJdbcBackupPath(String jdbcBackupPath) {
		this.jdbcBackupPath = jdbcBackupPath;
	}

}
