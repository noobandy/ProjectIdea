/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.group.GroupAuthority;
import in.anandm.projectidea.domain.model.user.UserAuthority;
import in.anandm.projectidea.domain.model.user.UserAuthorityRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author Anand
 *
 */
@Repository
public class UserAuthorityRepositoryImpl extends BaseRepository<UserAuthority, Long> implements UserAuthorityRepository {

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IUserAuthorityRepository#saveUserAuthority(in.anandm.projectidea.domain.model.UserAuthority)
	 */
	@Override
	@Transactional
	public void saveUserAuthority(UserAuthority userAuthority) {
		save(userAuthority);

	}

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IUserAuthorityRepository#removeUserAuthority(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeUserAuthority(Long userId, Long authorityId) {
		Search search = new Search(GroupAuthority.class);
		Filter userFilter = Filter.equal("userId", userId);
		Filter authorityFilter = Filter.equal("authorityId", authorityId);
		search.addFilter(Filter.and(userFilter, authorityFilter));

		UserAuthority userAuthority = searchUnique(search);

		remove(userAuthority);

	}

}
