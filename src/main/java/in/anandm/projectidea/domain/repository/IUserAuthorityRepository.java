/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.UserAuthority;

/**
 * @author Anand
 *
 */
public interface IUserAuthorityRepository {
	
	void saveUserAuthority(UserAuthority userAuthority);


	void removeUserAuthority(Long userId, Long authorityId);
}
