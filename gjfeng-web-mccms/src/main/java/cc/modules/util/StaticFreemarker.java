package cc.modules.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import cc.messcat.entity.WebSite;
import cc.modules.constants.Constants;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class StaticFreemarker {

	public StaticFreemarker() {
	}

	/**
	 * @param webSite
	 * @param relativePath
	 * @param ftl
	 * @param packageName
	 *            包名
	 * @param htmlName
	 * @param map
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void init(WebSite webSite, String relativePath, String ftl, String packageName, String htmlName, final Map map)
		throws IOException, TemplateException {

		Configuration freemarkerCfg = new Configuration();
		freemarkerCfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), (new StringBuilder(Constants.FILE_SEPARATOR))
			.append(relativePath).toString());
		freemarkerCfg.setEncoding(Locale.getDefault(), "utf8");

		String path = ServletActionContext.getServletContext().getRealPath(Constants.FILE_SEPARATOR);

		String ctx = webSite.getDomain();
		if ("".equals(ctx))
			ctx = (new StringBuilder(webSite.getDomain())).toString();

		if (map.get("ctx") == null)
			map.put("ctx", ctx);

		Template template = freemarkerCfg.getTemplate(ftl);
		template.setEncoding("utf8");

		if (!"".equals(packageName)) {
			File dirFile = new File((new StringBuilder(String.valueOf(path))).append(packageName).toString());
			boolean bFile = dirFile.exists();
			if (!bFile) {
				bFile = dirFile.mkdirs();
				if (!bFile)
					return;
			}
		}

		File htmlFile = new File((new StringBuilder(String.valueOf(path))).append(packageName).append(htmlName).toString());
		boolean hFile = htmlFile.exists();
		try {
			if (!hFile) {
				htmlFile.createNewFile();
			}
		} catch (Exception e) {
			return;
		}

		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "utf8"));
		template.process(map, out);
		out.flush();
		out.close();
	}
}