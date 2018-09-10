package cc.messcat.gjfeng.common.util;

import java.io.File;
import java.io.FileOutputStream;

public class IOStream {

	/**
	 * io流
	 * @param string
	 * @throws Exception
	 */
	public static void writeFile(String content,String dir,String fileName) throws Exception{
		File file=new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(
			new File(fileName),true);  //默认采用覆盖模式，新的写入数据会覆盖旧的数据
		fos.write(content.getBytes());       //把字符转换成字节
		fos.close();
	}
}
