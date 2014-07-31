/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.Group;

/**
 * @author anandm
 *
 */
public interface IGroupRepository {
	

	void saveGroup(Group group);

	Group findGroupByName(String groupName);

}
