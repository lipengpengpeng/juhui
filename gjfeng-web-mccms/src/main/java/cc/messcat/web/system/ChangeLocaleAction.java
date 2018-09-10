package cc.messcat.web.system;

import java.util.Locale;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocaleProvider;

import cc.messcat.bases.BaseAction;

public class ChangeLocaleAction extends BaseAction implements LocaleProvider {

	private static final long serialVersionUID = -3838030056635393005L;

	private String parameter;

	public String execute() throws Exception {
		ActionContext.getContext().setLocale(getLocale());
		ServletActionContext.getRequest().getSession().setAttribute("WW_TRANS_I18N_LOCALE", getLocale());
		return "success";
	}

	public Locale getLocale() {
		Locale locale = null;
		if ("zh".equals(this.parameter)) {
			locale = new Locale("zh", "CN");
		} else if ("en".equals(this.parameter)) {
			locale = new Locale("en", "US");
		}
		return locale;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
