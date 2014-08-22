/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.group.Group;
import in.anandm.projectidea.domain.model.group.IGroupRepository;

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
public class GroupRepository extends BaseRepository<Group, Long> implements
		IGroupRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.anandm.projectidea.domain.repository.IGroupRepository#saveGroup(in
	 * .anandm.projectidea.domain.model.Group)
	 */
	@Override
	@Transactional
	public void saveGroup(Group group) {
		save(group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.anandm.projectidea.domain.repository.IGroupRepository#findGroupByName
	 * (java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Group findGroupByName(String groupName) {
		Search search = new Search(Group.class);
		search.addFilter(Filter.equal("groupName", groupName));
		return searchUnique(search);
	}

	@Override
	public List<Group> findAllGroups() {

		return findAll();
	}

	@Override
	public Group findgroupById(Long id) {

		return find(id);
	}

}
