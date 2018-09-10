package cc.messcat.gjfeng.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfProductCommentDubbo;
import cc.messcat.gjfeng.entity.GjfProductComment;
import cc.messcat.gjfeng.service.product.GjfProductCommentService;

public class GjfProductCommentDubboImpl implements GjfProductCommentDubbo{
	
	@Autowired
	@Qualifier("gjfProductCommentService")
	private GjfProductCommentService gjfProductCommentService;

	@Override
	public ResultVo addProComment(GjfProductComment gjfProductComment, String fileContent, String fileName,String orderSn)
			throws Exception {
		return gjfProductCommentService.addProCom(gjfProductComment, fileContent, fileName,orderSn);
	}

	@Override
	public ResultVo getProComByPager(Integer pageNo, Integer pageSize, Long proId,String state) {
		return gjfProductCommentService.getCommentByPage(pageNo, pageSize, proId,state);
	}
	
	@Override
	public ResultVo getCommentByProId(int pageNo, int pageSize, Long proId) {
		return gjfProductCommentService.getCommentByProId(pageNo, pageSize, proId);
	}

	@Override
	public ResultVo removeCom(Long comId) {
		// TODO Auto-generated method stub
		return gjfProductCommentService.removeProCom(comId);
	}

	@Override
	public ResultVo countProCom(Long proId,String state) {
		// TODO Auto-generated method stub
		return gjfProductCommentService.countProCom(proId,state);
	}

	@Override
	public ResultVo findProComById(Long id,Long productId) {
		return gjfProductCommentService.findProComById(id,productId);
	}

}
