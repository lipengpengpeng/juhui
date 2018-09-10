package cc.messcat.gjfeng.dubbo.core;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfProductComment;

public interface GjfProductCommentDubbo {
	
	/**
	 * 添加评论
	 * @param gjfProductComment
	 * @param fileContent
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public ResultVo addProComment(GjfProductComment gjfProductComment,String fileContent,String fileName,String orderSn)throws Exception;
	
	/**
	 * 获取全部商品评论
	 * @param pageNo
	 * @param pageSize
	 * @param proId
	 * @return
	 */
	public ResultVo getProComByPager(Integer pageNo,Integer pageSize,Long proId,String state);
	
	/**
	 * 获取当前商品评论--后台
	 * @param pageNo
	 * @param pageSize
	 * @param proId
	 * @return
	 */
	public ResultVo getCommentByProId(int pageNo,int pageSize,Long proId);
	
	/**
	 * 删除评论
	 * @param comId
	 * @return
	 */
	public ResultVo removeCom(Long comId);
	
	/**
	 * 统计商品评论数
	 * @param proId
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
