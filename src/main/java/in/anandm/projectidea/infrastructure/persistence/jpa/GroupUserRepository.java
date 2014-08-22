/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.group.GroupUser;
import in.anandm.projectidea.domain.model.group.IGroupUserRepository;

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
public class GroupUserRepository extends BaseRepository<GroupUser, Long>
		implements IGroupUserRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.anandm.projectidea.domain.repository.IGroupUserRepository#saveGroupUser
	 * (in.anandm.projectidea.domain.model.GroupUser)
	 */
	@Override
	@Transactional
	public void saveGroupUser(GroupUser groupUser) {
		save(groupUser);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.anandm.projectidea.domain.repository.IGroupUserRepository#removeGroupUser
	 * (java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeGroupUser(Long groupId, Long userId) {
		Search search = new Search(GroupUser.class);
		Filter goupFilter = Filter.equal("groupId", groupId);
		Filter userFilter = Filter.equal("userId", userId);
		search.addFilter(Filter.and(goupFilter, userFilter));

		GroupUser groupUser = searchUnique(search);

		remove(groupUser);

	}

	@Override
	public List<GroupUser> findGroupUsersOfUser(Long userId) {
		Search search = new Search(GroupUser.class);
		Filter userFilter = Filter.equal("userId", userId);
		search.addFilter(userFilter);

		return search(search);
	}

	@Override
	public List<GroupUser> findGroupUsersOfGroup(Long groupId) {
		Search search = new Search(GroupUser.class);
		Filter goupFilter = Filter.equal("groupId", groupId);

		search.addFilter(goupFilter);

		return search(search);
	}

}
