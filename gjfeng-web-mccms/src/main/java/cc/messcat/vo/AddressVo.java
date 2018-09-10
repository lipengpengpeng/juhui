package cc.messcat.vo;

public class AddressVo {
	
	private String province;
	private String city;
	private String area;
	private String addr;
	
	public AddressVo(){}
	
	public AddressVo(String province,String city,String area,String addr){
		this.province = province;
		this.city = city;
		this.area = area;
		this.addr = addr;
	}
	
	public String getAreaInfo(){
		return province + city + area;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}
