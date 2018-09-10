
package cc.messcat.web.front;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.vo.EpColumnVo;
import cc.modules.commons.PageAction;
import net.sf.json.JSONArray;

public class AjaxIndexAction extends PageAction {

	private static final long serialVersionUID = -70638331100381846L;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private Long goods_id;// 商品id
	private int quantity;// 购物数量
	private Long columnId;// 栏目id
	private Long cart_id;// 购物车id
	private String paySn;// 支付单号
	private Long orderId;


	/**
	 * 加载商品类型一二三级栏目
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String loadProduct() throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String id = request.getParameter("id");
		if (id == null || id.length() == 0) {
			return renderText(null);
		}

		List columns = this.epColumnManagerDao.findSubColumn(Long.valueOf(id));
		List<EpColumnVo> list = new ArrayList<EpColumnVo>(columns.size());
		EpColumnVo ec = null;
		for (int i = 0; i < columns.size(); i++) {
			EnterpriseColumn ecc = (EnterpriseColumn) columns.get(i);
			ec = new EpColumnVo();
			ec.setId(ecc.getId());
			ec.setNames(ecc.getNames());
			ec.setShortName(ecc.getShortName());
			list.add(ec);
		}
		JSONArray json = JSONArray.fromObject(list);
		return renderText(json == null ? null : json.toString());
	}


	public Long getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Long goodsId) {
		goods_id = goodsId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cartId) {
		cart_id = cartId;
	}

	public String getPaySn() {
		return paySn;
	}

	public void setPaySn(String paySn) {
		this.paySn = paySn;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}