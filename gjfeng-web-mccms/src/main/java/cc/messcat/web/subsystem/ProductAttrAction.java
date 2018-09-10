package cc.messcat.web.subsystem;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.entity.GjfAttrValue;
import cc.messcat.gjfeng.entity.GjfProductAttr;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.modules.commons.PageAction;
import net.sf.json.JSONObject;

public class ProductAttrAction extends PageAction  {

	private static final long serialVersionUID = 1L;
	
	private Long id; 
	private Long proId; //商品Id
	private Long attrTypeId; //属性类型Id
	private Long attrValueId;
	private String token;	
	private String status; //状态 0:停用 1:启用
	private GjfProductAttr gjfProductAttr; //商品属性
	private List<GjfProductAttr> gjfProductAttrs;
	private GjfProductInfo gjfProductInfo;
	private List<GjfAttrValue> gjfAttrValues;
	private GjfAttrValue gjfAttrValue;
	private Map jsonMap = new HashMap<String, Object>();
	
	/**
	 * 跳转添加商品属性页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String newProductAttr(){
		gjfProductInfo = (GjfProductInfo) productInfoDubbo.findProductById(proId).getResult();
		gjfAttrValues = (List<GjfAttrValue>) (productAttrDubbo.retrieveAllAttrValue("1").getResult());
		return "ProductAttr_new";
	}
	
	/**
	 * 添加商品属性
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addProductAttr(){
		try {
			if (gjfProductAttr != null && proId != null && attrValueId != null) {
				gjfProductInfo = (GjfProductInfo) productInfoDubbo.findProductById(proId).getResult();
				gjfProductAttr.setProductId(gjfProductInfo);
				gjfAttrValue = (GjfAttrValue) productAttrDubbo.retrieveAttrValueById(attrValueId).getResult();
				gjfProductAttr.setAttrValueId(gjfAttrValue);
				resultVo = productAttrDubbo.addProductAttr(gjfProductAttr);
				
				jsonMap.put("result", resultVo.getCode());
				jsonMap.put("msg", resultVo.getMsg());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("result", "400");
			jsonMap.put("msg", "添加出错!请稍后再试");
		}
		JSONObject json = JSONObject.fromObject(jsonMap);
		return renderText(json == null ? null : json.toString());
	}
	
	/**
	 * 根据商品Id查找商品属性（分页）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String retrieveProductAttrById(){
		try {
			resultVo = productAttrDubbo.retrieveProductAttrByProId(proId,pageNo,pageSize);
			this.pager = (Pager) resultVo.getResult();
			this.gjfProductAttrs = pager.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ProductAttrList";
	}
	
	/**
	 * 删除商品属性
	 * @return
	 */
	public String delProductAttrById(){
		try {
			this.gjfProductAttr = (GjfProductAttr) productAttrDubbo.retrieveProductAttrById(id).getResult();
			this.proId = gjfProductAttr.getProductId().getId();
			resultVo = productAttrDubbo.delProductAttr(gjfProductAttr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.retrieveProductAttrById();
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

	public Long getAttrTypeId() {
		return attrTypeId;
	}

	public void setAttrTypeId(Long attrTypeId) {
		this.attrTypeId = attrTypeId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public GjfProductAttr getGjfProductAttr() {
		return gjfProductAttr;
	}

	public void setGjfProductAttr(GjfProductAttr gjfProductAttr) {
		this.gjfProductAttr = gjfProductAttr;
	}

	public List<GjfProductAttr> getGjfProductAttrs() {
		return gjfProductAttrs;
	}

	public void setGjfProductAttrs(List<GjfProductAttr> gjfProductAttrs) {
		this.gjfProductAttrs = gjfProductAttrs;
	}

	public GjfProductInfo getGjfProductInfo() {
		return gjfProductInfo;
	}

	public void setGjfProductInfo(GjfProductInfo gjfProductInfo) {
		this.gjfProductInfo = gjfProductInfo;
	}

	public List<GjfAttrValue> getGjfAttrValues() {
		return gjfAttrValues;
	}

	public void setGjfAttrValues(List<GjfAttrValue> gjfAttrValues) {
		this.gjfAttrValues = gjfAttrValues;
	}

	public Long getAttrValueId() {
		return attrValueId;
	}

	public void setAttrValueId(Long attrValueId) {
		this.attrValueId = attrValueId;
	}

	public GjfAttrValue getGjfAttrValue() {
		return gjfAttrValue;
	}

	public void setGjfAttrValue(GjfAttrValue gjfAttrValue) {
		this.gjfAttrValue = gjfAttrValue;
	}

	public Map getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	
}
