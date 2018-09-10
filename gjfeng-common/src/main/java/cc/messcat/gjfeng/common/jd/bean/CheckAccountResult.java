package cc.messcat.gjfeng.common.jd.bean;

import java.math.BigDecimal;
import java.util.List;


public class CheckAccountResult {
	
	private BigDecimal balance;
	
	private List<AccoutResult> accounts;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<AccoutResult> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccoutResult> accounts) {
		this.accounts = accounts;
	} 
	
	

}
