package cc.messcat.web.subsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.entity.GjfAttrType;
import cc.messcat.gjfeng.entity.GjfAttrValue;
import cc.modules.commons.PageAction;
import cc.modules.constants.ActionConstants;
import net.sf.json.JSONObject;

public class AttrValueAction  extends PageAction {

	private static final long serialVersionUID = 1L;
	
	private Long id; 
	private Long attrTypeId; //属性类型Id
	private String token;	
	private String status; //状态 0:停用 1:启用
	private GjfAttrValue gjfAttrValue; //属性值
	private GjfAttrType gjfAttrType; //属性类型
	private List<GjfAttrType> gjfAttrTypes;
	private List<GjfAttrValue> gjfAttrValues;
	private List<EnterpriseColumn> enterpriseColumns;
	private Map jsonMap = new HashMap<String, Object>();
	
	Map<String, Object> dataMap = new HashMap<String, Object>();
	
	/**
	 * 跳转添加属性值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String newAttrValue(){
		gjfAttrTypes = (List<GjfAttrType>) productAttrDubbo.retrieveAllAttrTypeByStatus("1").getResult();
		enterpriseColumns = epColumnManagerDao.findProductColumn(); //产品栏目
		return "newPage";
	}
	
	/**
	 * 添加属性值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addAttrValue(){
		try {
		    gjfAttrType = (GjfAttrType) productAttrDubbo.retrieveAttrTypeById(attrTypeId).getResult();
			gjfAttrValue.setAttrId(gjfAttrType);
			resultVo = productAttrDubbo.addAttrValue(gjfAttrValue);
			jsonMap.put("result", resultVo.getCode());
			jsonMap.put("msg", resultVo.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("result", "400");
			jsonMap.put("msg", "添加出错!请稍后再试");
		}
		JSONObject json = JSONObject.fromObject(jsonMap);
		return renderText(json == null ? null : json.toString());
	}
	
	/**
	 * 修改属性值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String modifyAttrValue(){
		try {
			gjfAttrType = (GjfAttrType) productAttrDubbo.retrieveAttrTypeById(attrTypeId).getResult();
			gjfAttrValue.setAttrId(gjfAttrType);
			resultVo = productAttrDubbo.modifyAttrValue(gjfAttrValue);
			jsonMap.put("result", resultVo.getCode());
			jsonMap.put("msg", resultVo.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("result", "400");
			jsonMap.put("msg", "修改出错!请稍后再试");
		}
		JSONObject json = JSONObject.fromObject(jsonMap);
		return renderText(json == null ? null : json.toString());
	}
	
	/**
	 * 修改属性值状态
	 * @return
	 */
	public String modifyAttrValueStatus(){
		try {
			resultVo = productAttrDubbo.modifyAttrValueStatus(id,status,token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.retrieveAttrValueByPage();
	}
	
	/**
	 * 根据Id查找属性值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String retrieveAttrValueById(){
		try {
			resultVo = productAttrDubbo.retrieveAttrValueById(id);
			//产品栏目
			enterpriseColumns = epColumnManagerDao.findProductColumn();
			this.gjfAttrValue = (GjfAttrValue) resultVo.getResult();
			this.gjfAttrType = (GjfAttrType) productAttrDubbo.retrieveAttrTypeById(gjfAttrValue.getAttrId().getId()).getResult();
			gjfAttrTypes = (List<GjfAttrType>) productAttrDubbo.retrieveAllAttrTypeByStatus("1").getResult();
			List<GjfAttrType> temp = new ArrayList<GjfAttrType>();
			for(GjfAttrType gjfAttrType : gjfAttrTypes){
				if (gjfAttrValue.getAttrId().getId() == gjfAttrType.getId()) {
					temp.add(gjfAttrType);
				}
			}
			gjfAttrTypes.removeAll(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editPage";
	}
	
	/**
	 * 根据Id和Token查找属性值
	 * @return
	 */
	public String retrieveAttrValueByIdAndToken(){
		try {
			resultVo = productAttrDubbo.retrieveAttrValueById(id,token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 根据属性类型id、token查询属性值
	 * @return
	 */
	public String retrieveAttrValueByAttrTypeId(){
		try {		
			@SuppressWarnings("unchecked")
			List<GjfAttrValue> gjfAttrValues = (List<GjfAttrValue>) productAttrDubbo.retrieveAttrValueByAttrTypeId(attrTypeId,token).getResult();
				for (GjfAttrValue attrV1 : gjfAttrValues) {
					List<Object[]> list = new ArrayList<Object[]>();
					for (GjfAttrValue attrV2 : gjfAttrValues) {
						if (attrV1.getAttrId().getId().longValue() == attrV2.getAttrId().getId().longValue()) {
							Object[] objects = { attrV2.getId(), attrV2.getAttrValue() };
							list.add(objects);
						}
						dataMap.put(attrV1.getAttrId().getAttrName(), list);
					}
				}
				return "ajaxFinAttr";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询所有属性值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String retrieveAllAttrValue(){
		try {
			resultVo = productAttrDubbo.retrieveAllAttrValue(status);
			this.gjfAttrValues = (List<GjfAttrValue>) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ActionConstants.SUCCESS_KEY;
	}
	
	/**
	 * 分页查询属性值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String retrieveAttrValueByPage(){
		try {
			resultVo = productAttrDubbo.retrieveAttrValueByPage(pageNo,pageSize);
			this.pager = (Pager) resultVo.getResult();
			this.gjfAttrValues = pager.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ActionConstants.SUCCESS_KEY;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public GjfAttrValue getGjfAttrValue() {
		return gjfAttrValue;
	}

	public void setGjfAttrValue(GjfAttrValue gjfAttrValue) {
		this.gjfAttrValue = gjfAttrValue;
	}

	public List<GjfAttrValue> getGjfAttrValues() {
		return gjfAttrValues;
	}

	public void setGjfAttrValues(List<GjfAttrValue> gjfAttrValues) {
		this.gjfAttrValues = gjfAttrValues;
	}

	public GjfAttrType getGjfAttrType() {
		return gjfAttrType;
	}

	public void setGjfAttrType(GjfAttrType gjfAttrType) {
		this.gjfAttrType = gjfAttrType;
	}

	public List<GjfAttrType> getGjfAttrTypes() {
		return gjfAttrTypes;
	}

	public void setGjfAttrTypes(List<GjfAttrType> gjfAttrTypes) {
		this.gjfAttrTypes = gjfAttrTypes;
	}

	public List<EnterpriseColumn> getEnterpriseColumns() {
		return enterpriseColumns;
	}

	public void setEnterpriseColumns(List<EnterpriseColumn> enterpriseColumns) {
		this.enterpriseColumns = enterpriseColumns;
	}

	public Map getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map jsonMap) {
		this.jsonMap = jsonMap;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	
	
}
