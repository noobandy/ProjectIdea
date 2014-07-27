/**
 * 
 */
package in.anandm.projectidea.domain.service;

import in.anandm.projectidea.domain.model.AuthorityConstants;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author anandm
 * 
 */
@Service
public class AuthServiceImpl implements IAuthService {

	@Override
	public boolean isAllowed(AuthorityConstants authority) {

	
		Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			if("ROLE_ANONYMOUS".equals(grantedAuthority.getAuthority())){
				return false;
			}else{
				if (authority.equals(AuthorityConstants
						.valueOf(grantedAuthority.getAuthority()))) {
					return true;
				}
			}
			
		}
		return false;
	}

}
