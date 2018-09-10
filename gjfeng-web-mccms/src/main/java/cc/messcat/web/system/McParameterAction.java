package cc.messcat.web.system;

import java.util.List;

import cc.messcat.entity.McParameter;
import cc.modules.commons.PageAction;

public class McParameterAction extends PageAction {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private McParameter mcParameter;
	
	private List<McParameter> mcParameters;
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		try {
			this.setPageSize(20);
			super.pager = this.mcParameterManagerDao.retrieveMcParametersPager(pageSize, pageNo);
			this.mcParameters = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveMcParameterById() throws Exception {
		try {
			this.mcParameter = this.mcParameterManagerDao.retrieveMcParameter(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "edit";
	}

	public String newPage() throws Exception {
		return "newPage";
	}
	
	public String viewPage() throws Exception {
		try {
			this.mcParameter = this.mcParameterManagerDao.retrieveMcParameter(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "view";
	}
	
	public String newMcParameter() throws Exception {
		try {
			this.mcParameterManagerDao.addMcParameter(this.mcParameter);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return this.execute();
	}
	
	public String editMcParameter() throws Exception {
		try {
			McParameter mcParameter0 = this.mcParameterManagerDao.retrieveMcParameter(this.id);
			mcParameter0.setName(mcParameter.getName());
			mcParameter0.setNoOrder(mcParameter.getNoOrder());
			mcParameter0.setWrOk(mcParameter.getWrOk());

			this.mcParameterManagerDao.modifyMcParameter(mcParameter0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			ex.printStackTrace();
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return this.execute();
	}
	
	public String delMcParameter() throws Exception {
		try {
			this.mcParameterManagerDao.removeMcParameter(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return this.execute();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public McParameter getMcParameter() {
		return mcParameter;
	}

	public void setMcParameter(McParameter mcParameter) {
		this.mcParameter = mcParameter;
	}

	public List<McParameter> getMcParameters() {
		return mcParameters;
	}

	public void setMcParameters(
			List<McParameter> mcParameters) {
		this.mcParameters = mcParameters;
	}

}