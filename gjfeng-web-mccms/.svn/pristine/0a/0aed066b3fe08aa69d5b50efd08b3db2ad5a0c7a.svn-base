package cc.messcat.web.left;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.modules.commons.PageAction;
import cc.modules.util.CollectionUtil;

public class ColumnAction extends PageAction {

	private static final long serialVersionUID = -368600535571436478L;
	private String dtree;

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		String url = "column/epColumnAction!query.action?father=";
		List<EnterpriseColumn> columnList = this.epColumnManagerDao.findAllEnterpriseColumn();
		if (CollectionUtil.isListNotEmpty(columnList)) {
			ColumnTree tree = new ColumnTree();
			this.dtree = tree.getTree(columnList, url, true);
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