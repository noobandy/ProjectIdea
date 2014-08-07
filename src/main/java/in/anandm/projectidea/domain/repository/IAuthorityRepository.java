/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import java.util.List;

import in.anandm.projectidea.domain.model.Authority;
import in.anandm.projectidea.domain.model.AuthorityConstants;
import in.anandm.projectidea.domain.model.Page;

/**
 * @author Anand
 *
 */
public interface IAuthorityRepository {

	
	void saveAuthority(Authority authority);
	

	List<Authority> findAllAuthority();
	Authority findAuthorityByName(AuthorityConstants authorityName);
	
	Page<Authority> page(Integer pageNumber,Integer itemsPerPage);
}
