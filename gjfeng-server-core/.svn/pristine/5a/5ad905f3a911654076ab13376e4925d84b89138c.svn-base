package cc.messcat.gjfeng.service.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.FilesUploadModel;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.product.GjfProductCommentDao;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfProductComment;
import cc.messcat.gjfeng.entity.GjfProductInfo;

@Service("gjfProductCommentService")
public class GjfProductCommentServiceImpl implements GjfProductCommentService {

	@Value("${dubbo.application.web.client}")
	private String projectName;

	@Value("${upload.assessment.path}")
	private String imageFolderName;

	@Value("${gjfeng.client.project.url}")
	private String domainName;

	@Autowired
	@Qualifier("gjfProductCommentDao")
	private GjfProductCommentDao gjfProductCommentDao;

	@Override
	public ResultVo addProCom(GjfProductComment gjfProductComment, String fileContent, String fileName, String orderSn)
		throws Exception {
		
		if (!BeanUtil.isValid(gjfProductComment.getMember())) {
			return new ResultVo(400, "用户不存在", "");
		}

		// 获取用户新
		Map<String, Object> map = new HashMap<>();
		map.put("id", gjfProductComment.getMember().getId());
		GjfMemberInfo member = (GjfMemberInfo) gjfProductCommentDao.query(GjfMemberInfo.class, map);
		if (!BeanUtil.isValid(member)) {
			return new ResultVo(400, "用户不存在", "");
		}

		if (!BeanUtil.isValid(gjfProductComment.getProduct())) {
			return new ResultVo(400, "商品不存在", "");
		}

		// 获取商品信息
		Map<String, Object> map0 = new HashMap<>();
		map0.put("id", gjfProductComment.getProduct().getId());
		GjfProductInfo pro = (GjfProductInfo) gjfProductCommentDao.query(GjfProductInfo.class, map0);
		if (!BeanUtil.isValid(pro)) {
			return new ResultVo(400, "商品不存在", "");
		}

		// 处理商品的评分
		BigDecimal oldSroce = pro.getScore();
		if(!BeanUtil.isValid(oldSroce)){
			oldSroce=new BigDecimal(0.00);
		}
		BigDecimal comSroce = new BigDecimal(gjfProductComment.getComScore());
		if (oldSroce.compareTo(BigDecimal.ZERO)==0) {
			pro.setScore(comSroce);
		} else {			
			BigDecimal newSroce = (oldSroce.add(comSroce)).divide(new BigDecimal(2));
			pro.setScore(newSroce);
		}

		// 获取订单信息
		// 获取商品信息
		Map<String, Object> map1 = new HashMap<>();
		map1.put("orderSn", orderSn);
		GjfOrderInfo order = gjfProductCommentDao.query(GjfOrderInfo.class, map1);
		if (!BeanUtil.isValid(order)) {
			return new ResultVo(400, "订单不存在", "");
		}
		// 修复订单状态
		order.setEvaluationStatus("1");
		
		gjfProductComment.setComImg(fileContent);
		gjfProductComment.setComFatherId(0L);
		gjfProductComment.setComTime(new Date());
		gjfProductCommentDao.save(gjfProductComment);
		gjfProductCommentDao.updateObj(pro);
		gjfProductCommentDao.updateObj(order);
		return new ResultVo(200, "评论成功", "");
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultVo getCommentByPage(Integer pageNo, Integer pageSize, Long productId, String state) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product.id", productId);
		// List<GjfProductComment>
		// comList=gjfProductCommentDao.queryList(GjfProductComment.class,
		// pageNo, pageSize, "comTime", "desc", map);
		List<GjfProductComment> comList = gjfProductCommentDao.getProductCommet(pageNo, pageSize, productId, state);
		return new ResultVo(200, "查询成功", comList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultVo getCommentByProId(int pageNo, int pageSize, Long productId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("product.id", productId);
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(gjfProductCommentDao.queryTotalRecord(GjfProductComment.class, attrs))),
				gjfProductCommentDao.queryList(GjfProductComment.class, (pageNo - 1) * pageSize, pageSize, "comTime", "desc", attrs));
		return new ResultVo(200, "查询成功", pager);
	}

	@Override
	public ResultVo removeProCom(Long comId) {
		gjfProductCommentDao.deleteById(comId);
		return new ResultVo(200, "删除成功", "");
	}

	@Override
	public ResultVo countProCom(Long proId, String state) {
		Long count = gjfProductCommentDao.countProComment(proId, state);
		return new ResultVo(200, "查询成功", count);
	}

	@Override
	public ResultVo findProComById(Long id, Long productId) {
		if (ObjValid.isNotValid(id)) {
			return new ResultVo(400, "参数错误", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("product.id", productId);
		return new ResultVo(200, "查询成功", gjfProductCommentDao.query(GjfProductComment.class, attrs));
	}

}
