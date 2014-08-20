/**
 * 
 */
package in.anandm.projectidea.application.util;

import org.springframework.security.core.Authentication;
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
}
