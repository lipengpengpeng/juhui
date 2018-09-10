package cc.messcat.gjfeng.web.app.v1;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.util.BaiduApi;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="app/address/",headers = "app-version=v1.0")
public class AddressControllerV1 extends BaseController {
	
	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("response")
	private HttpServletResponse response;
	
	@Autowired
	@Qualifier("addressDubbo")
	private GjfAddressDubbo addressDubbo;
	
	
	/**
	 * @描述 跳转到切换定位地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "toLocal", method = RequestMethod.POST)
	public ModelAndView toLocalAddress(){
		return new ModelAndView("wx/o2o-shop/index-cityChange");
	}

	/**
	 * @描述 我的收货地址
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/getMyAddress", method = RequestMethod.POST)
	@ResponseBody
	public Object getMyAddress(String account,String goodSource){		
		try {
			resultVo=addressDubbo.findMyDeliveryAddress(account,goodSource);			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);			
		}
		return resultVo;
	}
	
	/**
	 * @描述 获取收货地址的详细信息
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/getAddressDetail", method = RequestMethod.POST)
	@ResponseBody
	public Object getAddressDetail(@RequestParam("id") Long id,String account,String goodSource){
		try {			
			resultVo=addressDubbo.findAddressById(id,account,goodSource);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 修改收货地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/updateAddress", method = RequestMethod.POST)
	@ResponseBody
	public Object updateAddress(@RequestParam("id") Long id,String account,String consigneeName,String consigneeSex,String mobile,Long proviceId,Long cityId,Long areaId,String addressDetail,Long townId){		
		try {
			Object[] object={account,consigneeName,consigneeSex,mobile,proviceId,cityId,areaId,addressDetail,id,townId};
			resultVo=addressDubbo.updateAddress(object);	//TODO		
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 修改为默认地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/default", method = RequestMethod.POST)
	@ResponseBody
	public Object setDefaultAddress(@RequestParam("id") Long id,String account,String goodSource){
		try {			
			resultVo=addressDubbo.updateAdressIsDefault(id, account,goodSource);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 删除地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/delAdress", method = RequestMethod.POST)
	@ResponseBody
	public Object delAddress(@RequestParam("id") Long id,String account){
		try {			
			resultVo=addressDubbo.delAddress(id, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;
	}
			
	/**
	 * @描述 新增地址
	 * @return
	 */
	@RequestMapping(value = "v1_0/newsAddress", method = RequestMethod.POST)
	@ResponseBody
	public Object newsAddress(String account,String consigneeName,String consigneeSex,String mobile,Long proviceId,Long cityId,Long areaId,String addressDetail,String goodSource,Long townId){	
		try {			
			Object[] object={account,consigneeName,consigneeSex,mobile,proviceId,cityId,areaId,addressDetail,goodSource,townId};
			resultVo=addressDubbo.addDeliveryAddress(object);
			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;		
	}
	
	/**
	 * @描述 获取省市区
	 * @return
	 */
	@RequestMapping(value = "v1_0/getAreaByType", method = RequestMethod.POST)
	@ResponseBody
	public Object getAreaByType(Long fatherId,String addressType,String goodSource){
		try {		
			resultVo=addressDubbo.findAddress(fatherId, addressType,goodSource);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 根据字母获取省市区
	 * @return
	 */
	@RequestMapping(value = "v1_0/getProvinceByLetter", method = RequestMethod.POST)
	@ResponseBody
	public Object getProvinceByLetter(String letter,String type){
		try {	
			resultVo=addressDubbo.findAddressByLetter(letter, type);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 根据经纬度获取城市名称
	 * @author Karhs
	 * @date 2017年2月3日
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@RequestMapping(value = "v1_0/getCityName", method = RequestMethod.POST)
	@ResponseBody
	public Object getCityName(@RequestParam("latitude") double latitude,@RequestParam("longitude") double longitude){
		String cityName="广州";
		Long cityCode=440100L;
		try {
			request.getSession().setAttribute("longitude",longitude);
			request.getSession().setAttribute("latitude",latitude);
			JSONObject json=BaiduApi.getLngAndLatToAddress(latitude, longitude);
			int status=(int) json.get("status");
			if (status==0) {
				JSONObject localJson=JSONObject.fromObject(JSONObject.fromObject(json.get("result")).get("addressComponent"));
				cityName=(String) localJson.get("city");
				//cityName=cityName.substring(0, cityName.length()-1);
				cityCode=localJson.getLong("adcode");
				request.getSession().setAttribute("localStatus","1");	//定位成功
			}
			resultVo=new ResultVo(200, "查询成功", cityName);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
			resultVo.setResult(cityName);
		}
		request.getSession().setAttribute("cityName",cityName);
		request.getSession().setAttribute("cityCode",cityCode);
		return resultVo;
	}
	
	/**
	 * @描述 设置城市名称到session，比如热门城市或者选择的城市
	 * @author Karhs
	 * @date 2017年2月3日
	 * @param cityName
	 * @return
	 */
	@RequestMapping(value = "setCityName", method = RequestMethod.POST)
	@ResponseBody
	public Object setCityName(@RequestParam("cityName") String cityName){
		try {
			//1.设置城市名称
			request.getSession().setAttribute("cityName",cityName);
			
			//2.根据城市名称获取该城市的经纬度
			Map<String, BigDecimal> map=BaiduApi.getGeocoderLatitude(cityName);
			request.getSession().setAttribute("longitude",map.get("lng"));
			request.getSession().setAttribute("latitude",map.get("lat"));
			
			//3.修改当前定位为成功
			request.getSession().setAttribute("localStatus","1");	//定位成功
			resultVo=new ResultVo(200, "操作成功", cityName);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;
	}
	
				
	/**
	 * @描述 ios新增地址
	 * @return
	 */
	@RequestMapping(value = "v1_0/newsAddressInIos", method = RequestMethod.POST)
	@ResponseBody
	public Object newsAddressInIos(String account,String consigneeName,String consigneeSex,String mobile,Long proviceId,Long cityId,Long areaId,String addressDetail,String goodSource,Long townId){	
		try {							
			Object[] object={account,consigneeName,consigneeSex,mobile,proviceId,cityId,areaId,addressDetail,goodSource,townId};
			resultVo=addressDubbo.addDeliveryAddressInIos(object);			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;		
	}
	
	
	/**
	 * @描述 修改收货地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/updateAddressInIos", method = RequestMethod.POST)
	@ResponseBody
	public Object updateAddressInIos(@RequestParam("id") Long id,String account,String consigneeName,String consigneeSex,String mobile,Long proviceId,Long cityId,Long areaId,String addressDetail,String goodSource,Long townId){		
		try {
			Object[] object={account,consigneeName,consigneeSex,mobile,proviceId,cityId,areaId,addressDetail,id,goodSource,townId};
			resultVo=addressDubbo.updateAddressInIos(object);		
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressControllerV1.class);
		}
		return resultVo;
	}
	
}
