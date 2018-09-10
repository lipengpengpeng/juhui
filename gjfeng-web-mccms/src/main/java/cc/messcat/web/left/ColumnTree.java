package cc.messcat.web.left;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.entity.EnterpriseColumn;

@SuppressWarnings("unchecked")
public class ColumnTree {

	private static final long serialVersionUID = -368600535571436478L;

	public String getTree(List<EnterpriseColumn> columnList, String url, boolean isColumn) {

		// 初始化tree
		StringBuilder dtree = new StringBuilder();
		Map map = new HashMap();
		Authorize isAuthorize = new Authorize();

		// 循环所有的栏目
		for (EnterpriseColumn ec : columnList) {

			if (isAuthorize.isAuthorize(ec.getNames()) || (map.get(ec.getFather().toString()) != null)) {
				if (ec.getFather() == 0) {
					map.put(String.valueOf(ec.getId()), "true");
				}

				if ("link".equals(ec.getTypeColumn().getTemplateType())) {
					if (isColumn) {
						dtree.append("d.add(").append(ec.getId()).append(",").append(ec.getFather()).append(",'").append(
							ec.getNames()).append(" [<font color=red>外部链接</font>]','").append(ec.getLinkUrl()).append("','")
							.append(ec.getNames()).append("','mainFrame');");
					} else {
						continue;
					}
				}

				if ("content".equals(ec.getTypeColumn().getTemplateType())) {
					dtree.append("d.add(").append(ec.getId()).append(",").append(ec.getFather()).append(",'").append(ec.getNames())
						.append(" [<font color=red>单页</font>]','").append(url).append(ec.getId()).append("','").append(
							ec.getNames()).append("','mainFrame');");
				}

				if ("list".equals(ec.getTypeColumn().getTemplateType())) {
					dtree.append("d.add(").append(ec.getId()).append(",").append(ec.getFather()).append(",'").append(ec.getNames())
						.append(" [<font color=red>新闻</font>]','").append(url).append(ec.getId()).append("','").append(
							ec.getNames()).append("','mainFrame');");
				}

				if ("mostlist".equals(ec.getTypeColumn().getTemplateType())) {
					dtree.append("d.add(").append(ec.getId()).append(",").append(ec.getFather()).append(",'").append(ec.getNames())
						.append(" [<font color=red>模块列表</font>]','").append(url).append(ec.getId()).append("','").append(
							ec.getNames()).append("','mainFrame');");
				}
				
				if("product".equals(ec.getTypeColumn().getTemplateType())) {
					if (isColumn) {
						dtree.append("d.add(").append(ec.getId()).append(",").append(ec.getFather()).append(",'").append(ec.getNames())
							.append(" [<font color=red>产品</font>]','").append(url).append(ec.getId()).append("','").append(
								ec.getNames()).append("','mainFrame');");
					}else{
						dtree.append("d.add(").append(ec.getId()).append(",").append(ec.getFather()).append(",'").append(ec.getNames())
							.append(" [<font color=red>产品</font>]','collection/productColumnAction!queryProductColumn.action?colId=").append(ec.getId()).append("','")
								.append(ec.getNames()).append("','mainFrame');");
					}
				}
				
				if("other".equals(ec.getTypeColumn().getTemplateType())) {
					if (isColumn) {
						dtree.append("d.add(").append(ec.getId()).append(",").append(ec.getFather()).append(",'").append(ec.getNames())
							.append(" [<font color=red>定制栏目</font>]','").append(url).append(ec.getId()).append("','").append(
								ec.getNames()).append("','mainFrame');");
					}else{
						//用当前栏目ID替换掉URL中的“@”字符
						String featuresUrl = ec.getTypeColumn().getFeaturesUrl();
						featuresUrl = featuresUrl.replace("@", ec.getId().toString());
						
						dtree.append("d.add(").append(ec.getId()).append(",").append(ec.getFather()).append(",'").append(ec.getNames())
							.append(" [<font color=red>定制栏目</font>]','").append(featuresUrl).append("','").append(
								ec.getNames()).append("','mainFrame');");
					}
				}
			}
		}

		return dtree.toString();
	}

}