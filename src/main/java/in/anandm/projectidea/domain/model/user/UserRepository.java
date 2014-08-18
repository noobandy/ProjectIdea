/**
 * 
 */
package in.anandm.projectidea.domain.model.user;

import in.anandm.projectidea.domain.shared.QueryResult;

import java.util.List;

/**
 * @author anandm
 * 
 */
public interface UserRepository {

	void saveUser(User user);

	User findUserByUserName(String username);

	QueryResult<User> query(UserQuery query);

	List<User> list(UserQuery query);

	long count(UserQuery query);
}
