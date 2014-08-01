/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.User;

import java.io.InputStream;

/**
 * @author anandm
 * 
 */
public interface IUserRepository {

	void saveUser(User user);

	User findUserByUserName(String username);

	InputStream getProfilePicOfUser(String username);

}
