package cc.messcat.web.subsystem;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfProductAttr;
import cc.messcat.gjfeng.entity.GjfProductAttrStock;
import cc.modules.commons.PageAction;
import net.sf.json.JSONObject;


public class ProductAttrStockAction extends PageAction {

	private Long id; 
	private Long proId; //商品Id
	private String status;
	private GjfProductAttrStock gjfProductAttrStock;
	private List<GjfProductAttrStock> gjfProductAttrStocks ;
	private Map<StringBuffer, GjfProductAttrStock> resultMap = new HashMap<>();
	
	
	/**
	 * 跳转添加页面
	 * @return
	 */
	public String newProductAttrStock(){
		return "newPage";
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String addProductAttrStock(){	
		return null;
	}
	
	/**
	 * 启用停用
	 * @return
	 */
	public String updateStatus(){
		JSONObject json = new JSONObject();
		try {
			GjfProductAttrStock gjfProductAttrStock = (GjfProductAttrStock) productAttrStockDubbo.findProductAttrStockById(id).getResult();
			gjfProductAttrStock.setStatus(status);
			resultVo = productAttrStockDubbo.updateProductAttrStock(gjfProductAttrStock);
			json = JSONObject.fromObject(resultVo);
		} catch (Exception e) {
			e.printStackTrace();
			json = JSONObject.fromObject(new ResultVo(400,"操作出错",null));
		}
		return renderText(json == null ? null : json.toString());
	}
	
	
	/**
	 * 根据商品Id查找（分页）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String retrieveProductAttrStockByProId(){
		try {
			resultVo = productAttrStockDubbo.findProductAttrStockByProId(proId,pageNo,pageSize);
			this.pager = (Pager) resultVo.getResult();
			this.gjfProductAttrStocks = pager.getResultList();	
			for(int j = 0 ; j<gjfProductAttrStocks.size();  j++){
				String [] id = gjfProductAttrStocks.get(j).getProductAttrIds().split(",");
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i<id.length; i++){
					GjfProductAttr gjfProductAttr  = (GjfProductAttr) productAttrDubbo.retrieveProductAttrById(Long.parseLong(id[i])).getResult();
					sb.append(gjfProductAttr.getAttrValueId().getAttrValue()).append("、");
				}
					resultMap.put(sb, gjfProductAttrStocks.get(j));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public List<GjfProductAttrStock> getGjfProductAttrStocks() {
		return gjfProductAttrStocks;
	}

	public void setGjfProductAttrStocks(List<GjfProductAttrStock> gjfProductAttrStocks) {
		this.gjfProductAttrStocks = gjfProductAttrStocks;
	}

	public GjfProductAttrStock getGjfProductAttrStock() {
		return gjfProductAttrStock;
	}

	public void setGjfProductAttrStock(GjfProductAttrStock gjfProductAttrStock) {
		this.gjfProductAttrStock = gjfProductAttrStock;
	}

	public Map<StringBuffer, GjfProductAttrStock> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<StringBuffer, GjfProductAttrStock> resultMap) {
		this.resultMap = resultMap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
	
	
}
