package cc.messcat.gjfeng.service.member;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.member.GjfSearchHistoryDao;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfSearchHistory;

@Service("gjfSearchHistoryService")
public class GjfSearchHistoryServiceImpl implements GjfSearchHistoryService {
	
	@Autowired
	@Qualifier("gjfSearchHistoryDao")
	private GjfSearchHistoryDao gjfSearchHistoryDao;

	/* 
	 * 添加搜索历史
	 * @see cc.messcat.gjfeng.service.member.GjfSearchHistoryService#addSearchHistory(cc.messcat.gjfeng.entity.GjfMemberInfo, java.lang.String)
	 */
	@Override
	public ResultVo addSearchHistory(GjfMemberInfo gjfMemberInfo, String keyWord) {
		if (ObjValid.isNotValid(gjfMemberInfo) || StringUtil.isBlank(keyWord)) {
			return new ResultVo(400, "添加失败", null);
		}
		GjfSearchHistory gjfSearchHistory = new GjfSearchHistory();
		gjfSearchHistory.setAddTime(new Date());
		gjfSearchHistory.setKeyWord(keyWord);
		gjfSearchHistory.setMemberId(gjfMemberInfo);
		gjfSearchHistoryDao.save(gjfSearchHistory);
		return new ResultVo(200, "添加成功", null);
	}

	/* 
	 * 查询搜索历史
	 * @see cc.messcat.gjfeng.service.member.GjfSearchHistoryService#findSearchHistory(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findSearchHistory(Long memberId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.id", memberId);
		return new ResultVo(200, "查询成功", gjfSearchHistoryDao.queryList(GjfSearchHistory.class, 0, 10, "addTime", "desc", attrs));
	}

}
