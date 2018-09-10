package cc.messcat.web.left;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.modules.commons.PageAction;
import cc.modules.util.CollectionUtil;

public class CollectionAction extends PageAction {

	private static final long serialVersionUID = 9181493715735538988L;
	private String dtree;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		String url = "collection/epNewsAction!query.action?father=";
		List<EnterpriseColumn> columnList = this.epColumnManagerDao.findAllEnterpriseColumn();
		if (CollectionUtil.isListNotEmpty(columnList)) {
			ColumnTree tree = new ColumnTree();
			this.dtree = tree.getTree(columnList, url, false);
		}

		return "success";

	}

	public String getDtree() {
		return dtree;
	}

	public void setDtree(String dtree) {
		this.dtree = dtree;
	}

}