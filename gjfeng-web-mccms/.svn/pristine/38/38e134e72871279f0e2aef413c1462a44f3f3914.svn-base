package cc.messcat.web.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.entity.EnterpriseColumn;
public class EpServiceAction extends FrontAction{
private static final long serialVersionUID = 2628772958303651473L;
	
	/**
	 * 选中的栏目
	 */
	private EnterpriseColumn selectColumn;
	private List years;
	private int car_buy_year;
	private int car_buy_month;
	private int car_mileage;
	
	/**
	 * 首页入口
	 */
	public String execute() throws Exception {
		super.init();
		
		return "service_business_detail";
	}
	
	public String enterMaintainSelectPage() throws Exception{
		List tempyears=new ArrayList();
		ActionContext ctx=ActionContext.getContext();
		Map session=ctx.getSession();
		int carProductYear= Integer.parseInt(session.get("selected_CarProductive").toString());
		int curYear=new Date().getYear()+1900;
		if(carProductYear==curYear){
			tempyears.add(carProductYear);
		}else if(carProductYear<curYear){
			int year;
			int temp=curYear-carProductYear;
			for(int i=temp;i>=0;i--){
				year=carProductYear+i;
				tempyears.add(year);
			}
		}
		years=tempyears;
		return "enterMaintainSelectPage";	
	}
    
	public String checkMaintenanceForCar() throws Exception{
		
		return "checkMaintenanceForCar";	
	}
	
	
	public EnterpriseColumn getSelectColumn() {
		return selectColumn;
	}

	public void setSelectColumn(EnterpriseColumn selectColumn) {
		this.selectColumn = selectColumn;
	}

	public List getYears() {
		return years;
	}

	public void setYears(List years) {
		this.years = years;
	}

	public int getCar_buy_year() {
		return car_buy_year;
	}

	public void setCar_buy_year(int carBuyYear) {
		car_buy_year = carBuyYear;
	}

	public int getCar_buy_month() {
		return car_buy_month;
	}

	public void setCar_buy_month(int carBuyMonth) {
		car_buy_month = carBuyMonth;
	}

	public int getCar_mileage() {
		return car_mileage;
	}

	public void setCar_mileage(int carMileage) {
		car_mileage = carMileage;
	}
}
