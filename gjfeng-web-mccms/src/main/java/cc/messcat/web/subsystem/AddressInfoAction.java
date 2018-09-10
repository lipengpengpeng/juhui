package cc.messcat.web.subsystem;

import cc.modules.commons.PageAction;

public class AddressInfoAction extends PageAction  {

	private static final long serialVersionUID = 1L;

	private String addressType;
	
	private Long fatherId;
	
	/**
	 * 查询省市区
	 * @return
	 */
	public Object findAllAddress(){
		try {
			resultVo = addressDubbo.findAddress(fatherId, addressType,"0");
			printToJson(resultVo.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}
	
	
	
}
