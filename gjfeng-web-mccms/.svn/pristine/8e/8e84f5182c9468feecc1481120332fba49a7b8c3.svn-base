package cc.modules.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUpload {

	private FTPClient ftp;
	private String ftpUrl;
	private String ftpPort;
	private String ftpUser;
	private String ftpPassword;
	private String ftpWorkingDirectory;
	private int count;

	public void ftpUpload(String clientWorkingDirectory, String parentFolderName) throws Exception {
		if (this.connect()) {
			if (parentFolderName != null && !"".equals(parentFolderName)) {
				ftp.makeDirectory(parentFolderName);
				ftp.changeWorkingDirectory(parentFolderName);
			}
			this.upload(new File(clientWorkingDirectory));
		}
	}

	private void upload(File file) throws Exception {

		if (file.isDirectory()) {
			ftp.makeDirectory(file.getName());
			ftp.changeWorkingDirectory(file.getName());
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file1 = files[i];
				if (file1.isDirectory()) {
					upload(file1);
					ftp.changeToParentDirectory();
				} else {
					FileInputStream input = new FileInputStream(file1);
					ftp.storeFile(file1.getName(), input);
					String replyCode = String.valueOf(ftp.getReplyCode());
					if (!replyCode.startsWith("1") && !replyCode.startsWith("2")) {
						throw new Exception(ftp.getReplyString());
					}
					input.close();
				}
			}
		} else {
			FileInputStream input = new FileInputStream(file);
			ftp.storeFile(file.getName(), input);
			String replyCode = String.valueOf(ftp.getReplyCode());
			if (!replyCode.startsWith("1") && !replyCode.startsWith("2")) {
				throw new Exception(ftp.getReplyString());
			}
			input.close();
		}

	}

	private boolean connect() throws Exception {

		ftp = new FTPClient();

		// 连接FTP服务器, 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
		ftp.connect(ftpUrl, Integer.valueOf(ftpPort));
		ftp.login(ftpUser, ftpPassword);// 登录
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);// 用以上传图片声音之类的文件
		int reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return false;
		}
		ftp.changeWorkingDirectory(ftpWorkingDirectory);
		return true;

	}

	public String getFtpUrl() {
		return ftpUrl;
	}

	public void setFtpUrl(String ftpUrl) {
		this.ftpUrl = ftpUrl;
	}

	public String getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpWorkingDirectory() {
		return ftpWorkingDirectory;
	}

	public void setFtpWorkingDirectory(String ftpWorkingDirectory) {
		this.ftpWorkingDirectory = ftpWorkingDirectory;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
