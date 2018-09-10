package cc.messcat.web.collection;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cc.messcat.bases.BaseAction;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public abstract class UploadAbstractAction extends BaseAction {

	private File upload = null;
	private String uploadContentType = null;
	private String uploadFileName = null;
	private String allowTypes = null;
	private String uploadTime;
	// ������5ע�������
	private String savePath;

	// ������5ע��ķ���
	public void setSavePath(String value) {
		this.savePath = value;
	}

	@SuppressWarnings("deprecation")
	public String getSavePath() throws Exception {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setUpload(File upload) {
		this.upload = upload;

	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;

	}

	public File getUpload() {
		return (this.upload);
	}

	public String getUploadContentType() {
		return (this.uploadContentType);
	}

	public String getUploadFileName() {
		return (this.uploadFileName);
	}

	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public void doExecute(boolean imageSize) throws Exception {
		DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String formatDate = format.format(new Date());
		int position = uploadFileName.lastIndexOf(".");
		String extension = uploadFileName.substring(position);
		Calendar calendar = Calendar.getInstance();
		uploadTime = String.valueOf(calendar.getTimeInMillis());
		setUploadFileName(formatDate + uploadTime + extension);
		if (imageSize == true) {
			// ------------------�������ͼ-------------------
			Image src = javax.imageio.ImageIO.read(upload); // ����Image����
			float tagsize = 300;
			int old_w = src.getWidth(null); // �õ�Դͼ��
			int old_h = src.getHeight(null);
			int new_w = 0;
			int new_h = 0; // �õ�Դͼ��
			float tempdouble;
			if (old_w > old_h) {
				tempdouble = old_w / tagsize;
			} else {
				tempdouble = old_h / tagsize;
			}
			new_w = Math.round(old_w / tempdouble);
			new_h = Math.round(old_h / tempdouble);// ������ͼ����
			BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); // ������С���ͼ
			FileOutputStream newimage = new FileOutputStream(getSavePath() + "\\" + uploadFileName); // ����ļ���
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
			encoder.encode(tag); // ��JPEG����
			newimage.close();
		} else {
			FileOutputStream fos = new FileOutputStream(getSavePath() + "\\" + uploadFileName);
			FileInputStream fis = new FileInputStream(upload);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		}
		// File file=new File(getSavePath()+ "\\" +"1");
		// file.mkdir();
		// File bigFile = new File(getSavePath() + "\\" + uploadFileName);
		// bigFile.delete();
	}
}
