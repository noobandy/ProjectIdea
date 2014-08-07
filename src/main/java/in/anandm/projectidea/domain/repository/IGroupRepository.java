/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.Group;
import in.anandm.projectidea.domain.model.Page;

/**
 * @author anandm
 *
 */
public interface IGroupRepository {
	

	void saveGroup(Group group);

	Group findGroupByName(String groupName);
	
	Page<Group> page(Integer pageNumber,Integer itemsPerPage);

}
