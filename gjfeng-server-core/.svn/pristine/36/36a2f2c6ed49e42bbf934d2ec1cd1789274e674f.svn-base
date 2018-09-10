package cc.messcat.gjfeng.service.product;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfProductComment;

public interface GjfProductCommentService {
	
	/**
	 * 添加商品评论
	 * @param gjfProductComment
	 * @return
	 */
	public ResultVo addProCom(GjfProductComment gjfProductComment,String fileContent,String fileNmae,String orderSn)throws Exception;
	
	/**
	 * 分页获取商品评论
	 * @param pageNo
	 * @param pageSize
	 * @param productId
	 * @return
	 */
	public ResultVo getCommentByPage(Integer pageNo,Integer pageSize,Long productId,String state);
	
	/**
	 * 获取商品评论
	 * @param pageNo
	 * @param pageSize
	 * @param productId
	 * @return
	 */
	public ResultVo getCommentByProId(int pageNo,int pageSize,Long productId);
	
	/**
	 * 删除商品评论
	 * @param comId
	 * @return
	 */
	public ResultVo removeProCom(Long comId);
	
	/**
	 *统计商品评论数
	 * @param comId
	 * @return
	 */
	public ResultVo countProCom(Long proId,String state);

	/**
	 * 根据Id查找商品评论
	 * @param id
	 * @return
	 */
	public ResultVo findProComById(Long id,Long productId);
	
}
