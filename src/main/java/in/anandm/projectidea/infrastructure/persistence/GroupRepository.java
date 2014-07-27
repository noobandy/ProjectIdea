/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import in.anandm.projectidea.domain.model.Group;
import in.anandm.projectidea.domain.repository.IGroupRepository;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author Anand
 *
 */
@Repository
public class GroupRepository extends BaseRepository<Group, Long> implements IGroupRepository {

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IGroupRepository#saveGroup(in.anandm.projectidea.domain.model.Group)
	 */
	@Override
	public void saveGroup(Group group) {
		save(group);

	}

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IGroupRepository#findGroupByName(java.lang.String)
	 */
	@Override
	public Group findGroupByName(String groupName) {
		Search search = new Search(Group.class);
		search.addFilter(Filter.equal("groupName", groupName));
		return searchUnique(search);
	}
}
