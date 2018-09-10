package cc.messcat.vo;

public class Menu {
	
	private String name;
	private String act;
	private String op;
	
	public Menu(){}
	
	public Menu(String name,String act,String op){
		
		this.name = name;
		this.act = act;
		this.op = op;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAct() {
		return act;
	}
	public void setAct(String act) {
		this.act = act;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	
	

}
