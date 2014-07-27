/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import in.anandm.projectidea.domain.model.GroupAuthority;
import in.anandm.projectidea.domain.repository.IGroupAuthorityRepository;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author Anand
 *
 */
@Repository
public class GroupAuthorityRepository extends BaseRepository<GroupAuthority, Long> implements IGroupAuthorityRepository {

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IGroupAuthorityRepository#saveGroupAuthority(in.anandm.projectidea.domain.model.GroupAuthority)
	 */
	@Override
	public void saveGroupAuthority(GroupAuthority groupAuthority) {
		save(groupAuthority);

	}

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IGroupAuthorityRepository#removeGroupAuthority(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void removeGroupAuthority(Long groupId, Long authorityId) {
		Search search = new Search(GroupAuthority.class);
		Filter goupFilter = Filter.equal("groupId", groupId);
		Filter authorityFilter = Filter.equal("authorityId", authorityId);
		search.addFilter(Filter.and(goupFilter, authorityFilter));

		GroupAuthority groupAuthority = searchUnique(search);

		remove(groupAuthority);

	}

}
