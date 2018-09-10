package cc.modules.util;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * 文件下载工具类
 * @author StevenWang
 *
 */
public class DownloadFileUtil {
	
	/**
	 * 下载方法
	 * @param filepath 文件路径
	 * @param filename 文件名称
	 * @throws Exception
	 */
	public static void downloadFile(final String filepath, String filename)
			throws Exception {
		try {
			final HttpServletResponse res = ServletActionContext.getResponse();
			filename = URLEncoder.encode(filename, "UTF-8");
			res.setContentType("application/x-download;charset=utf-8");
			res.addHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			final FileInputStream in = new FileInputStream(filepath);
			final java.io.OutputStream out = res.getOutputStream();
			final byte[] bts = new byte[1024];
			int len = 0;
			while ((len = in.read(bts)) != -1) {
				out.write(bts, 0, len);
			}
			out.flush();
			out.close();
			in.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
