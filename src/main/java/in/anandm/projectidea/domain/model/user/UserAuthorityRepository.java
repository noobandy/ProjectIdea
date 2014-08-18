/**
 * 
 */
package in.anandm.projectidea.domain.model.user;


/**
 * @author Anand
 *
 */
public interface UserAuthorityRepository {
	
	void saveUserAuthority(UserAuthority userAuthority);


	void removeUserAuthority(Long userId, Long authorityId);
}
