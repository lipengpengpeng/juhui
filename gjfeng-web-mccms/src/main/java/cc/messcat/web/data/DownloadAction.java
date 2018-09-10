package cc.messcat.web.data;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import cc.modules.constants.Constants;

public class DownloadAction {

	private String fileName;

	private String inputPath;

	private String jdbcBackupPath;

	public InputStream getInputStream() throws Exception {

		final String filePath = this.jdbcBackupPath + Constants.FILE_SEPARATOR + this.fileName;
		return new FileInputStream(filePath);

	}

	public String execute() throws Exception {

		/*
		 * 
		 * 获取目标文件在服务器中保存的目录。若不在这个目录则拒绝下载，否则，
		 * 
		 * 稍微精通struts2的人可能试图获取WEB-INF下的文件，那就不安全了。
		 */
		String downloadDir = ServletActionContext.getServletContext().getRealPath(Constants.FILE_SEPARATOR+"upload");

		// 获取目标文件的绝对路径
		String downloadFile = ServletActionContext.getServletContext().getRealPath(this.inputPath);

		/*
		 * 
		 * 防止企图下载不在目录downloadDir下的文件，以保障安全。若不在这个目
		 * 
		 * 录则拒绝下载，否则，稍微精通struts2的人可能试图获取WEB-INF下 的文件，那就不安全了。
		 */
		if (!downloadFile.startsWith(downloadDir)) {
			return null;
		}

		return Constants.SUCCESS;

	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getJdbcBackupPath() {
		return jdbcBackupPath;
	}

	public void setJdbcBackupPath(String jdbcBackupPath) {
		this.jdbcBackupPath = jdbcBackupPath;
	}

}