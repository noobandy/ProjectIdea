/**
 * 
 */
package in.anandm.projectidea.domain.model.group;

import java.util.List;


/**
 * @author Anand
 *
 */
public interface IGroupUserRepository {
	

	void saveGroupUser(GroupUser groupUser);

	void removeGroupUser(Long groupId, Long userId);
	
	List<GroupUser> findGroupUsersOfUser(Long userId);
	
	List<GroupUser> findGroupUsersOfGroup(Long groupId);
}
