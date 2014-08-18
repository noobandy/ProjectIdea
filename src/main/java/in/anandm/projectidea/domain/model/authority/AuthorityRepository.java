/**
 * 
 */
package in.anandm.projectidea.domain.model.authority;

import java.util.List;

/**
 * @author Anand
 *
 */
public interface AuthorityRepository {

	
	void saveAuthority(Authority authority);
	

	List<Authority> findAllAuthority();
	Authority findAuthorityByName(AuthorityConstants authorityName);
}
