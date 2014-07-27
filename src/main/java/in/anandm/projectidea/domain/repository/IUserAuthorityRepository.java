/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import org.springframework.transaction.annotation.Transactional;

import in.anandm.projectidea.domain.model.UserAuthority;

/**
 * @author Anand
 *
 */
public interface IUserAuthorityRepository {
	
	@Transactional
	void saveUserAuthority(UserAuthority userAuthority);

	@Transactional
	void removeUserAuthority(Long userId, Long authorityId);
}
