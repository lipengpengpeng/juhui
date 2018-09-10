package cc.messcat.gjfeng.common.web;

import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;

public class BaseController {
	
	protected ResultVo resultVo;
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	protected Long checkAndConvertIdToLong(String id) throws Exception {
		if (StringUtil.isBlank(id)) {
			throw new Exception("id is null");
		}

		try {
			Long longId = Long.parseLong(id);
			return longId;
		} catch (Exception e) {
			throw new Exception("id is not a number");
		}
	}
}
