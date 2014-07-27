/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

import in.anandm.projectidea.domain.model.GroupAuthority;
import in.anandm.projectidea.domain.model.UserAuthority;
import in.anandm.projectidea.domain.repository.IUserAuthorityRepository;

/**
 * @author Anand
 *
 */
@Repository
public class UserAuthorityRepository extends BaseRepository<UserAuthority, Long> implements IUserAuthorityRepository {

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IUserAuthorityRepository#saveUserAuthority(in.anandm.projectidea.domain.model.UserAuthority)
	 */
	@Override
	public void saveUserAuthority(UserAuthority userAuthority) {
		save(userAuthority);

	}

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IUserAuthorityRepository#removeUserAuthority(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void removeUserAuthority(Long userId, Long authorityId) {
		Search search = new Search(GroupAuthority.class);
		Filter userFilter = Filter.equal("userId", userId);
		Filter authorityFilter = Filter.equal("authorityId", authorityId);
		search.addFilter(Filter.and(userFilter, authorityFilter));

		UserAuthority userAuthority = searchUnique(search);

		remove(userAuthority);

	}

}
