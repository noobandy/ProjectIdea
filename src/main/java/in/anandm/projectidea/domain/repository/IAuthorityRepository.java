/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.Authority;
import in.anandm.projectidea.domain.model.AuthorityConstants;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Anand
 *
 */
public interface IAuthorityRepository {

	@Transactional
	void saveAuthority(Authority authority);
	
	@Transactional(readOnly=true)
	Authority findAuthorityByName(AuthorityConstants authorityName);
	
	
}
