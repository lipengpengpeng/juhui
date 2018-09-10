package cc.modules.commons;

import java.io.FileInputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import cc.modules.constants.Constants;
import cc.modules.util.FileHandler;

public class MCCounter {

	private static final String COUNTER = "counter";
	private static final String COUNTER_FILE = Constants.FILE_SEPARATOR+"common"+Constants.FILE_SEPARATOR+"counter.txt";
	private static ServletContext application;

	public static Long getCountFromFile() throws Exception {
		String counterFilePath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(Constants.FILE_SEPARATOR) + COUNTER_FILE;
		String counterStr = FileHandler.convertInputStreamToString(new FileInputStream(counterFilePath), Constants.ENCODING);
		return Long.valueOf(counterStr.trim());
	}

	public synchronized static void count() throws Exception {

		if (application == null) {
			setApplication();
			Long newCounter = Long.valueOf(getCountFromFile());
			newCounter++;
			setCounter(newCounter);
		} else {
			Long count = (Long) application.getAttribute(COUNTER);
			count++;
			setCounter(count);
		}

	}

	public static Long getCounter() {
		if (application == null) {
			setApplication();
		}
		return (Long) application.getAttribute(COUNTER);
	}

	public static void setCounter(Long counter) throws Exception {
		if (application == null) {
			setApplication();
		} else {
			counter++;
			application.setAttribute(COUNTER, counter);
		}
	}

	private static void setApplication() {
		application = ServletActionContext.getRequest().getSession().getServletContext();
	}

	public void schedule() throws Exception {
		if (application != null) {
			Long count = (Long) application.getAttribute(COUNTER);
			String counterFilePath = application.getRealPath(Constants.FILE_SEPARATOR) + COUNTER_FILE;
			FileHandler.writeStringToFile(counterFilePath, count.toString(), Constants.ENCODING);
		}
	}

}
