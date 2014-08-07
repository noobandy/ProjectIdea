/**
 * 
 */
package in.anandm.projectidea.domain.service;

import in.anandm.projectidea.domain.model.Authority;
import in.anandm.projectidea.domain.model.AuthorityConstants;
import in.anandm.projectidea.domain.repository.IAuthorityRepository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author anandm
 * 
 */
@Service
public class AuthServiceImpl implements IAuthService {

	private IAuthorityRepository authorityRepository;

	private boolean initialized = false;

	@Autowired
	public AuthServiceImpl(IAuthorityRepository authorityRepository) {
		super();
		this.authorityRepository = authorityRepository;
	}

	@Override
	public boolean isAllowed(AuthorityConstants authority) {

		Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			if ("ROLE_ANONYMOUS".equals(grantedAuthority.getAuthority())) {
				return false;
			} else {
				if (authority.equals(AuthorityConstants
						.valueOf(grantedAuthority.getAuthority()))) {
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public int initOrUpdate() {
		int createdAuthoritiesCount = 0;
		if (!initialized) {
			List<Authority> authorities = authorityRepository
					.findAllAuthority();

			for (AuthorityConstants authoryConstant : AuthorityConstants
					.values()) {
				Authority authority = new Authority(authoryConstant);
				if (!authorities.contains(authority)) {
					authorityRepository.saveAuthority(authority);
					createdAuthoritiesCount++;
				}
			}

			initialized = true;
		}

		return createdAuthoritiesCount;
	}

}
