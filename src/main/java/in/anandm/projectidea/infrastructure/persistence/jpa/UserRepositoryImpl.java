/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.user.User;
import in.anandm.projectidea.domain.model.user.UserQuery;
import in.anandm.projectidea.domain.model.user.UserRepository;
import in.anandm.projectidea.domain.shared.QueryResult;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author Anand
 * 
 */
@Repository
public class UserRepositoryImpl extends BaseRepository<User, Long> implements
		UserRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.anandm.projectidea.domain.repository.IUserRepository#saveUser(in.anandm
	 * .projectidea.domain.model.User)
	 */
	@Override
	@Transactional
	public void saveUser(User user) {
		save(user);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.anandm.projectidea.domain.repository.IUserRepository#findUserByUserName
	 * (java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public User findUserByUserName(String username) {
		Search search = new Search(User.class);
		search.addFilter(Filter.equal("username", username));

		return searchUnique(search);
	}

	@Override
	public QueryResult<User> query(UserQuery query) {
		
		return null;
	}

	@Override
	public List<User> list(UserQuery query) {
		
		return null;
	}

	@Override
	public long count(UserQuery query) {
		
		return 0;
	}

}
