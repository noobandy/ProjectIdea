/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.User;

/**
 * @author anandm
 *
 */
public interface IUserRepository {


	void saveUser(User user);
	
	User findUserByUserName(String username);

	
	
}
