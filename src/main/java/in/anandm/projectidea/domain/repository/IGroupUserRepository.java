/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.GroupUser;

/**
 * @author Anand
 *
 */
public interface IGroupUserRepository {
	

	void saveGroupUser(GroupUser groupUser);

	void removeGroupUser(Long groupId, Long userId);
}
