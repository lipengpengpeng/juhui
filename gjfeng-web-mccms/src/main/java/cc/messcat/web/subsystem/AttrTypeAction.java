package cc.messcat.web.subsystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.entity.GjfAttrType;
import cc.modules.commons.PageAction;
import cc.modules.constants.ActionConstants;
import net.sf.json.JSONObject;

public class AttrTypeAction extends PageAction {

	private static final long serialVersionUID = 1L;

	private Long id; 
	private String token;	
	private String status; //状态 0:停用 1:启用
	private GjfAttrType gjfAttrType; //属性类型
	private List<GjfAttrType> gjfAttrTypes;
	private Map jsonMap = new HashMap<String, Object>();
	
	/**
	 * 跳转添加属性类型
	 * @return
	 */
	public String newArrtType(){
		return "newPage";
	}
	
	/**
	 * 添加属性类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addAttrType(){
		try {
			resultVo = productAttrDubbo.addAttrType(gjfAttrType);
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
	 * 修改属性类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String modifyAttrType(){
		try {
			resultVo = productAttrDubbo.modifyAttrType(gjfAttrType);
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
	 * 修改属性类型状态
	 * @return
	 */
	public String modifyAttrTypeStatus(){
		try {
			resultVo = productAttrDubbo.modifyAttrTypeStatus(id,status,token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retrieveAttrTypeByPage();
	}
	
	/**
	 * 根据Id查找属性类型
	 * @return
	 */
	public String retrieveAttrTypeById(){
		try {
			resultVo = productAttrDubbo.retrieveAttrTypeById(id);
			gjfAttrType = (GjfAttrType) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editPage";
	}
	
	/**
	 * 根据Id和Token查找属性类型
	 * @return
	 */
	public String retrieveAttrTypeByIdAndToken(){
		try {
			resultVo = productAttrDubbo.retrieveAttrTypeById(id,token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 根据状态查找所有的属性类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String retrieveAllAttrTypeByStatus(){
		try {
			resultVo = productAttrDubbo.retrieveAllAttrTypeByStatus(status);
			this.gjfAttrTypes = (List<GjfAttrType>) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ActionConstants.SUCCESS_KEY;
	}
	
	
	/**
	 * 分页查找所有的属性类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String retrieveAttrTypeByPage(){
		try {
			resultVo = productAttrDubbo.retrieveAttrTypeByPage(pageNo,pageSize);
			pager = (Pager) resultVo.getResult();
			this.gjfAttrTypes = pager.getResultList();
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

	public Map getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	
}
