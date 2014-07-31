/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.Authority;
import in.anandm.projectidea.domain.model.AuthorityConstants;

/**
 * @author Anand
 *
 */
public interface IAuthorityRepository {

	
	void saveAuthority(Authority authority);
	

	Authority findAuthorityByName(AuthorityConstants authorityName);
	
	
}
