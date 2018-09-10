package cc.messcat.web.left;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.jsp.JspException;

import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

@SuppressWarnings("unchecked")
public class Authorize {
	public String ifAnyGranted;

	public Authorize() {
		this.ifAnyGranted = "ifAnyGranted";
	}

	private Set authoritiesToRoles(Collection c) {
		Set target = new HashSet();

		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			GrantedAuthority authority = (GrantedAuthority) iterator.next();

			if (null == authority.getAuthority()) {
				throw new IllegalArgumentException(
					"Cannot process GrantedAuthority objects which return null from getAuthority() - attempting to process "
						+ authority.toString());
			}

			target.add(authority.getAuthority());
		}

		return target;
	}

	public String getIfAnyGranted() {
		return this.ifAnyGranted;
	}

	private Collection getPrincipalAuthorities() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

		if (null == currentUser) {
			return Collections.EMPTY_LIST;
		}

		if ((null == currentUser.getAuthorities()) || (currentUser.getAuthorities().length < 1)) {
			return Collections.EMPTY_LIST;
		}

		Collection granted = Arrays.asList(currentUser.getAuthorities());

		return granted;
	}

	private Set parseAuthoritiesString(String auth) {
		Set requiredAuthorities = new HashSet();
		String[] authorities = StringUtils.commaDelimitedListToStringArray(auth);

		for (int i = 0; i < authorities.length; ++i) {
			String authority = authorities[i];

			String role = authority.trim();
			role = StringUtils.deleteAny(role, "\t\n\r\f");

			requiredAuthorities.add(new GrantedAuthorityImpl(role));
		}

		return requiredAuthorities;
	}

	private Set retainAll(Collection granted, Set required) {
		Set grantedRoles = authoritiesToRoles(granted);
		Set requiredRoles = authoritiesToRoles(required);
		grantedRoles.retainAll(requiredRoles);

		return rolesToAuthorities(grantedRoles, granted);
	}

	private Set rolesToAuthorities(Set grantedRoles, Collection granted) {
		Set target = new HashSet();

		for (Iterator iterator = grantedRoles.iterator(); iterator.hasNext();) {
			String role = (String) iterator.next();

			for (Iterator grantedIterator = granted.iterator(); grantedIterator.hasNext();) {
				GrantedAuthority authority = (GrantedAuthority) grantedIterator.next();

				if (authority.getAuthority().equals(role)) {
					target.add(authority);

					break;
				}
			}
		}

		return target;
	}

	public void setIfAnyGranted(String ifAnyGranted) throws JspException {
		this.ifAnyGranted = ifAnyGranted;
	}

	public boolean isAuthorize(String auth) {

		Collection granted = getPrincipalAuthorities();

		Set grantedCopy = retainAll(granted, parseAuthoritiesString(auth));

		if (grantedCopy.isEmpty())
			return false;

		return true;
	}

}
