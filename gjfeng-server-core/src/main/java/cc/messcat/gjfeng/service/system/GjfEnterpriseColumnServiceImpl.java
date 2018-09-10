package cc.messcat.gjfeng.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.BeanUtilsEx;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.vo.app.EnterpriseColumnVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.system.GjfEnterpriseColumnDao;
import cc.messcat.gjfeng.entity.GjfEnterpriseColumn;
import cc.messcat.gjfeng.entity.GjfEnterpriseNews;

@Service("gjfEnterpriseColumnService")
public class GjfEnterpriseColumnServiceImpl implements GjfEnterpriseColumnService {

	@Autowired
	@Qualifier("gjfEnterpriseColumnDao")
	private GjfEnterpriseColumnDao gjfEnterpriseColumnDao;

	/*
	 * 查询商品栏目
	 * 
	 * @see cc.messcat.gjfeng.service.system.GjfEnterpriseColumnService#
	 * findIndexColumn(java.lang.String)
	 */
	@Override
	public ResultVo findIndexColumn(String goodsType, Long pageTypeId) {
		List<GjfEnterpriseColumn> columns = gjfEnterpriseColumnDao.findIndexColumn(goodsType, pageTypeId);
		return new ResultVo(200, "查询成功", BeanUtilsEx.changeList(columns, EnterpriseColumnVo.class));
	}

	/*
	 * 根据父类id查询子类栏目
	 * 
	 * @see cc.messcat.gjfeng.service.system.GjfEnterpriseColumnService#
	 * findColumnByFatherId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findColumnByFatherId(Long fatherId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("father", fatherId);
		attrs.put("typeColumn.id", 5L);
		attrs.put("state", "1");
		return new ResultVo(200, "查询成功",
				BeanUtilsEx.changeList(gjfEnterpriseColumnDao.queryList(GjfEnterpriseColumn.class, "num", "asc", attrs),
						EnterpriseColumnVo.class));
	}

	/*
	 * 查询所有一级栏目下的二级栏目
	 * 
	 * @see cc.messcat.gjfeng.service.system.GjfEnterpriseColumnService#
	 * findColumnsByFatherId(java.lang.String, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findColumnsByFatherId(String goodsType, Long pageTypeId) {
		ResultVo vo = findIndexColumn(goodsType, pageTypeId);
		if (ObjValid.isNotValid(vo.getResult())) {
			return new ResultVo(400, "查询成功", null);
		}
		List<EnterpriseColumnVo> columnVos = (List<EnterpriseColumnVo>) vo.getResult();
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		for (EnterpriseColumnVo columnVo : columnVos) {
			dataMap.put(columnVo.getPic2() + "~" + columnVo.getShortName(),
					findColumnByFatherId(columnVo.getId()).getResult());
		}
		return new ResultVo(200, "查询成功", dataMap);
	}

	/*
	 * 查询附近的商品栏目
	 * 
	 * @see cc.messcat.gjfeng.service.system.GjfEnterpriseColumnService#
	 * findO2ONearColumn()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findO2ONearColumn() {
		// 1.查询前3个热门栏目
		Object o = findIndexColumn("0", 1L).getResult();
		if (ObjValid.isNotValid(o)) {
			return new ResultVo(400, "查询失败", null);
		}
		List<EnterpriseColumnVo> columnVos = (List<EnterpriseColumnVo>) o;
		int num = 1;
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		for (EnterpriseColumnVo enterpriseColumnVo : columnVos) {
			if (num > 4) {
				break;
			}
			dataMap.put(enterpriseColumnVo.getShortName(),
					findColumnByFatherId(enterpriseColumnVo.getId()).getResult());
			++num;
		}
		// 2.查询这3个栏目的子栏目
		return new ResultVo(200, "查询成功", dataMap);
	}

	/*
	 * 根据栏目标识获取栏目新闻信息
	 * 
	 * @see cc.messcat.gjfeng.service.system.GjfEnterpriseColumnService#
	 * findBaseInfoByKey(java.lang.String)
	 */
	@Override
	public ResultVo findBaseInfoByKey(String key) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("source", key);
		return new ResultVo(200, "查询成功", gjfEnterpriseColumnDao.query(GjfEnterpriseNews.class, attrs));
	}

	/**
	 * app获取附近一级栏目
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultVo findNearColumnInApp() {
		// 1.查询前3个热门栏目
		Object o = findIndexColumn("0", 1L).getResult();
		if (ObjValid.isNotValid(o)) {
			return new ResultVo(400, "查询失败", null);
		}
		List<EnterpriseColumnVo> columnVos = (List<EnterpriseColumnVo>) o;
		List allInfoList=new ArrayList<>();
		int num = 1;
		
		
		for (EnterpriseColumnVo enterpriseColumnVo : columnVos) {
			Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
			if (num > 4) {
				break;
			}
			dataMap.put("columnName", enterpriseColumnVo.getShortName());
			dataMap.put("subColumn",
					findColumnByFatherId(enterpriseColumnVo.getId()).getResult());
			allInfoList.add(dataMap);
			++num;
		}
		return new ResultVo(200, "查询成功", allInfoList);
	}

	/**
	 * 获取京东首页栏目
	 */
	@Override
	public ResultVo findJdIndexColumn() {		
		List<GjfEnterpriseColumn> columns = gjfEnterpriseColumnDao.findJdIndexColumn();
		return new ResultVo(200, "查询成功", BeanUtilsEx.changeList(columns, EnterpriseColumnVo.class));
	}

	/**
	 * 获取产品供应链栏目
	 * @param frontNum
	 * @return
	 */
	@Override
	public ResultVo findProductModelColumn(String frontNum,String isValidInNav) {
		List<GjfEnterpriseColumn> columns = gjfEnterpriseColumnDao.findProductModelColumn(frontNum,isValidInNav);
		return new ResultVo(200, "查询成功", BeanUtilsEx.changeList(columns, EnterpriseColumnVo.class));
	}
	
	/**
	 * 获取新闻列表
	 * @param frontNum
	 * @return
	 */
	public ResultVo findEnterpriseNewsList(Integer pageNo,Integer pageSize,String keyWord){
		//获取新闻栏目		
		Map<String, Object> attrMap=new HashMap<>();
		attrMap.put("typeColumn.id", 2L);
		attrMap.put("state", "1");
		attrMap.put("frontNum", "NEWSLIST");
		GjfEnterpriseColumn column=gjfEnterpriseColumnDao.query(GjfEnterpriseColumn.class, attrMap);
		if(!BeanUtil.isValid(column)){
			return new ResultVo(400,"栏目不存在");
		}
		
		List<GjfEnterpriseNews> newsList=gjfEnterpriseColumnDao.findEnterpriseNewsList(pageNo, pageSize, keyWord, column.getId());
		return new ResultVo(200,"查询成功",newsList);
	}

	/**
	 * 获取新闻详细信息
	 */
	@Override
	public ResultVo findEnterpriseNewsListsById(Long id) {
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("id", id);
		GjfEnterpriseNews newsList=gjfEnterpriseColumnDao.query(GjfEnterpriseNews.class, attrs);
		return new ResultVo(200,"查询成功",newsList);
	}

	/**
	 * 供应链获取首页栏目和栏目下的子栏目
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultVo findSupplyChainPoIndexColumn() {
		//创建查询条件集合
		Map<String, Object> attrs=new HashMap<>();
		//关键词
		attrs.put("num", "supplyIndex");
		//栏目类型（5产品栏目）
		attrs.put("typeColumn.id", 5L);
		//状态
		attrs.put("state", "1");
		//创建结果集集合
		List resultList=new ArrayList<>();
		//获取父级栏目
		List<GjfEnterpriseColumn> fatherColumn=gjfEnterpriseColumnDao.queryList(GjfEnterpriseColumn.class, "orderColumn", "asc", attrs);
		//如果父级栏目不为空则遍历父级栏目获取父级栏目下的字栏目
		if(BeanUtil.isValid(fatherColumn)){
			//遍历集合
			for(GjfEnterpriseColumn column:fatherColumn){
				//创建接收数据的map集合
				Map<String, Object> resultMap=new HashMap<>();
				//父级栏目名称
				resultMap.put("faterColumnName", column.getNames());
				//父级栏目id
				resultMap.put("faterColumnId", column.getId());
				//父级栏目描述
				resultMap.put("faterColumnIntro", column.getIntro());
				//父级栏目url
				resultMap.put("faterColumnLinkUrl", column.getLinkUrl());
				//父级栏目标识
				resultMap.put("faterColumnFrontNum", column.getFrontNum());
				//清除条件集合
				attrs.clear();
				//父级id
				attrs.put("father", column.getId());
				//状态
				attrs.put("state", "1");
				//获取子栏目信息
				List<GjfEnterpriseColumn> supColumnList=gjfEnterpriseColumnDao.queryList(GjfEnterpriseColumn.class,"orderColumn", "asc" , attrs);
				//把获取的子栏目放到集合中
				resultMap.put("supColumn", supColumnList);
				//把map集合放到list
				resultList.add(resultMap);
			}
		}
		return  new ResultVo(200,"查询成功",resultList);
	}

}