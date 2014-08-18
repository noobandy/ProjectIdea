/**
 * 
 */
package in.anandm.projectidea.domain.model.group;


/**
 * @author Anand
 *
 */
public interface IGroupUserRepository {
	

	void saveGroupUser(GroupUser groupUser);

	void removeGroupUser(Long groupId, Long userId);
}
