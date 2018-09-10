package cc.modules.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import cc.modules.constants.Constants;

public class ZipCompressor {

	private static final String SLASH = Constants.FILE_SEPARATOR;

	private static final int BUFFER = 8192;

	public static void compress(final String srcPath, final String zipFilePath, final String[] designations) throws Exception {

		final File file = new File(srcPath);
		if (!file.exists()) {
			throw new RuntimeException(srcPath + "不存在!");
		}

		final FileOutputStream outputStream = new FileOutputStream(zipFilePath);
		final CheckedOutputStream cos = new CheckedOutputStream(outputStream, new CRC32());
		final ZipOutputStream zipOutputStream = new ZipOutputStream(cos);
		final String basedir = "";

		ZipCompressor.compressDirectoryWithDesignation(file, zipOutputStream, basedir, designations);

		zipOutputStream.close();
		outputStream.close();

	}

	/**
	 * 在第一级目录下， 指定压缩部分文件夹String[] designations.
	 * 
	 * @param file
	 * @param zipOutputStream
	 * @param basedir
	 * @param designations
	 * @throws Exception
	 */
	private static void compressDirectoryWithDesignation(final File file, final ZipOutputStream zipOutputStream,
		final String basedir, final String[] designations) throws Exception {

		final File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (ZipCompressor.existDesignFolder(files[i].getName(), designations)) {
				ZipCompressor.compressDirectoryOrFile(files[i], zipOutputStream, basedir + file.getName() + ZipCompressor.SLASH);
			}
		}

	}

	private static void compressDirectoryOrFile(final File file, final ZipOutputStream zipOutputStream, final String basedir)
		throws Exception {

		if (file.isDirectory()) {
			ZipCompressor.compressDirectory(file, zipOutputStream, basedir);
		} else {
			ZipCompressor.compressFile(file, zipOutputStream, basedir);
		}

	}

	private static void compressDirectory(final File file, final ZipOutputStream zipOutputStream, final String basedir)
		throws Exception {

		final File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			ZipCompressor.compressDirectoryOrFile(files[i], zipOutputStream, basedir + file.getName() + ZipCompressor.SLASH);
		}

	}

	private static boolean existDesignFolder(final String fileName, final String[] designations) {

		boolean existFlag = false;
		for (int i = 0; i < designations.length; i++) {
			if (fileName.equalsIgnoreCase(designations[i])) {
				existFlag = true;
			}
		}

		return existFlag;

	}

	private static void compressFile(final File file, final ZipOutputStream zipOutputStream, final String basedir) throws Exception {

		final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		final ZipEntry zipEntry = new ZipEntry(basedir + file.getName());
		zipOutputStream.putNextEntry(zipEntry);

		int count;
		byte data[] = new byte[ZipCompressor.BUFFER];
		while ((count = bis.read(data, 0, ZipCompressor.BUFFER)) != -1) {
			zipOutputStream.write(data, 0, count);
		}

		bis.close();

	}

}
