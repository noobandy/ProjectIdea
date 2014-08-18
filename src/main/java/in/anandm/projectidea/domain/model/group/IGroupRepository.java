/**
 * 
 */
package in.anandm.projectidea.domain.model.group;

import java.util.List;


/**
 * @author anandm
 *
 */
public interface IGroupRepository {
	

	void saveGroup(Group group);

	Group findGroupByName(String groupName);

	List<Group> findAllGroups();
}
