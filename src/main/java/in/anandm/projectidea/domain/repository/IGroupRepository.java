/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import org.springframework.transaction.annotation.Transactional;

import in.anandm.projectidea.domain.model.Group;

/**
 * @author anandm
 *
 */
public interface IGroupRepository {
	
	@Transactional
	void saveGroup(Group group);

	@Transactional(readOnly=true)
	Group findGroupByName(String groupName);

}
