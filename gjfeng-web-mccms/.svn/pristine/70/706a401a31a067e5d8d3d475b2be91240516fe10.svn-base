package cc.messcat.web.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.modules.constants.PageConstants;

/**
 * 处理并获取首页所有的数据及属性
 * 
 * @author Administrator
 * 
 */
public class EpIndexAction extends FrontAction {

	private static final long serialVersionUID = 7960729775814109475L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private String index_flag = null;

	/**
	 * 首页入口
	 */
	public String execute() throws Exception {
		super.init();
		this.index_flag = "true";
		return PageConstants.INDEX_KEY;
	}
}
