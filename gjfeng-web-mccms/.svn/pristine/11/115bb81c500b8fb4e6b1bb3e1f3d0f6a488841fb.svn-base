package cc.modules.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import cc.modules.constants.Constants;

public class FileHandler {

	private static final String SLASH = Constants.FILE_SEPARATOR;

	public static void createNewFolder(final String path) throws Exception {

		final File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

	}

	public static void createNewFile(final String filePath) throws Exception {

		final String folderPath = getFolderPathFromFullPath(filePath);
		createNewFolder(folderPath);
		final File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}

	}

	public static void deleteFile(final String filePath) throws Exception {

		final File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			file.delete();
		}

	}

	public static String getFileNameFromFullPath(final String fullPath) {

		final String fullPath2 = getCovertedSlash(fullPath);
		return fullPath2.substring(fullPath2.lastIndexOf(FileHandler.SLASH) + 1);

	}

	public static String getFolderPathFromFullPath(final String fullPath) {

		final String fullPath2 = getCovertedSlash(fullPath);
		return fullPath2.substring(0, fullPath2.lastIndexOf(FileHandler.SLASH));

	}

	private static String getCovertedSlash(final String fullPath) {
		return fullPath.replace("\\", FileHandler.SLASH);
	}

	public static String convertInputStreamToString(final InputStream in, final String enCode) throws Exception {

		final InputStreamReader reader = new InputStreamReader(in, enCode);
		final StringBuffer sb = new StringBuffer();
		String inStr;
		final BufferedReader br = new BufferedReader(reader);
		int i = 0;
		while ((inStr = br.readLine()) != null) {
			sb.append(inStr);
			if(i > 0) {
				sb.append("\r\n");
			}
			i++;
		}

		reader.close();
		br.close();

		return sb.toString();

	}

	public static void writeStringToFile(final String outFilePath, final String outStr, final String enCode) throws Exception {

		final File outFile = new File(outFilePath);
		final FileOutputStream fout = new FileOutputStream(outFile.getAbsolutePath());
		final OutputStreamWriter writer = new OutputStreamWriter(fout, enCode);
		writer.write(outStr);
		writer.flush();

		fout.close();
		writer.close();

	}
}
