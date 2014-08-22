/**
 * 
 */
package in.anandm.projectidea.domain.service;

import in.anandm.projectidea.domain.model.authority.Authority;
import in.anandm.projectidea.domain.model.authority.AuthorityConstants;
import in.anandm.projectidea.domain.model.authority.AuthorityRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author anandm
 * 
 */
@Service
public class AuthorityServiceImpl implements IAuthorityService {

	private AuthorityRepository authorityRepository;

	private boolean initialized = false;

	@Autowired
	public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
		super();
		this.authorityRepository = authorityRepository;
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
