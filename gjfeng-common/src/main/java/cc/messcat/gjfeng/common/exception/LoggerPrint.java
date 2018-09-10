package cc.messcat.gjfeng.common.exception;

import java.util.Date;

import org.apache.log4j.Logger;

import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import net.sf.json.JSONObject;

public class LoggerPrint {

	/**
	 * @描述 把逻辑层抛出的异常转换成resultVo类
	 * @author Karhs
	 * @date 2016年10月14日
	 * @param e
	 * @param classZz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static ResultVo getResult(Exception e,Class classZz){
		Logger logger = Logger.getLogger(classZz);
		ResultVo resultVo;
		try {
			if (StringUtil.isBlank(e.getMessage())) {
				resultVo=new ResultVo(400, "网络异常,请重试","");
				logger.error(DateHelper.dataToString(new Date(), "yyyy-MM-dd HH:mm:ss")+":"+classZz.getName()+"-----"+e.toString());
			}else {
				System.out.println(e.getMessage().toString());
				resultVo=(ResultVo) JSONObject.toBean(JSONObject.fromObject(e.getMessage()), ResultVo.class);
				logger.error(DateHelper.dataToString(new Date(), "yyyy-MM-dd HH:mm:ss")+":"+classZz.getName()+"-----"+e.getMessage());
			}
		} catch (Exception e2) {
			resultVo=new ResultVo(400, "网络异常,请重试","");
			logger.error(DateHelper.dataToString(new Date(), "yyyy-MM-dd HH:mm:ss")+":"+classZz.getName()+"-----"+e.toString());
			e2.printStackTrace();
		}
		e.printStackTrace();  //测试环境可以打印出控制台，生产环境要注释掉 TODO	
		return resultVo;
	}
}
