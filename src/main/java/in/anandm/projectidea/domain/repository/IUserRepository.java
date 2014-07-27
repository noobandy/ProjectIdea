/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import org.springframework.transaction.annotation.Transactional;

import in.anandm.projectidea.domain.model.User;

/**
 * @author anandm
 *
 */
public interface IUserRepository {

	@Transactional
	void saveUser(User user);
	
	@Transactional(readOnly=true)
	User findUserByUserName(String username);

	
	
}
