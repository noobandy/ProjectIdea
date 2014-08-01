/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.User;
import in.anandm.projectidea.domain.repository.IUserRepository;

import java.io.InputStream;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author Anand
 *
 */
@Repository
public class UserRepository extends BaseRepository<User, Long> implements IUserRepository {

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IUserRepository#saveUser(in.anandm.projectidea.domain.model.User)
	 */
	@Override
	@Transactional
	public void saveUser(User user) {
		save(user);

	}

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IUserRepository#findUserByUserName(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public User findUserByUserName(String username) {
		Search search = new Search(User.class);
		search.addFilter(Filter.equal("username", username));
		
		return searchUnique(search);
	}

	@Override
	public InputStream getProfilePicOfUser(String username) {
		
		return null;
	}

}
