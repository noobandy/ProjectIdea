/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import org.springframework.transaction.annotation.Transactional;

import in.anandm.projectidea.domain.model.GroupUser;

/**
 * @author Anand
 *
 */
public interface IGroupUserRepository {
	
	@Transactional
	void saveGroupUser(GroupUser groupUser);

	@Transactional
	void removeGroupUser(Long groupId, Long userId);
}
