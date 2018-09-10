package cc.messcat.gjfeng.common.vo.app;

import java.util.List;

public class OrderAddModel {

	private List<OrderAddVo> orderAddVos;

	public List<OrderAddVo> getOrderAddVos() {
		return orderAddVos;
	}

	public void setOrderAddVos(List<OrderAddVo> orderAddVos) {
		this.orderAddVos = orderAddVos;
	}

	public OrderAddModel(List<OrderAddVo> orderAddVos) {
		super();
		this.orderAddVos = orderAddVos;
	}

	public OrderAddModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
