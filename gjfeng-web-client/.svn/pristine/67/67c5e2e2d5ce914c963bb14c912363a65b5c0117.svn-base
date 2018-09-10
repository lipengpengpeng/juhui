package cc.messcat.gjfeng.web.app.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfCartInfoDubbo;
import cc.messcat.gjfeng.util.SessionUtil;

@Controller
@RequestMapping(value="app/cart/",headers = "app-version=v1.0")
public class CartControllerV1 extends BaseController {

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("cartInfoDubbo")
	private GjfCartInfoDubbo cartInfoDubbo;

	/**
	 * @描述 我的购物车
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/myCart", method = RequestMethod.POST)
	@ResponseBody
	public Object toMyCart(String account) {		
		try {
			 resultVo = cartInfoDubbo.findMyCart(account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, CartControllerV1.class);	
		}
		return resultVo;
	}

	/**
	 * @描述 添加商品到购物车
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param proId
	 * @param proAttrId
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "v1_0/addCart", method = RequestMethod.POST)
	@ResponseBody
	public Object addCart(OrderAddVo orderAddVo,String account) {
		try {			
			resultVo = cartInfoDubbo.addCart(orderAddVo, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, CartControllerV1.class);
		}
		return resultVo;
	}

	/**
	 * @描述 修改购物车商品数量
	 * @author Karhs
	 * @date 2017年1月17日
	 * @param id
	 * @param goodsNum
	 * @return
	 */
	@RequestMapping(value = "v1_0/updateCartNum", method = RequestMethod.POST)
	@ResponseBody
	public Object updateCart(@RequestParam("id") Long id, @RequestParam("goodsNum") Long goodsNum,String account) {
		try {
			resultVo = cartInfoDubbo.updateCartNum(id, goodsNum, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, CartControllerV1.class);
		}
		return resultVo;
	}

	/**
	 * @描述 删除购物车商品
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param proId
	 * @param proAttrId
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "v1_0/delCart/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object delCart(@PathVariable("id") Long id,String account) {
		try {
			resultVo = cartInfoDubbo.delCart(id, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, CartControllerV1.class);
		}
		return resultVo;
	}
}
