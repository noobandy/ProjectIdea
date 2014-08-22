/**
 * 
 */
package in.anandm.projectidea.application.util;

import in.anandm.projectidea.domain.model.authority.AuthorityConstants;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author anandm
 * 
 */
public class SecurityUtility {

	/**
	 * 
	 */
	private SecurityUtility() {
		super();

	}

	public static final String authenticatedUser() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				UserDetails details = (UserDetails) principal;
				return details.getUsername();

			} else {
				return (String) principal;
			}
		}

		return null;
	}

	public static final boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof String) {

				String username = (String) principal;
				return !"anonymous".equals(username);

			} else {
				return true;
			}
		}

		return false;
	}

	public static final boolean hasAuthority(AuthorityConstants authority) {
		Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();

		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			if (authority.equals(AuthorityConstants.valueOf(grantedAuthority
					.getAuthority()))) {
				return true;
			}
		}

		return false;
	}
}
