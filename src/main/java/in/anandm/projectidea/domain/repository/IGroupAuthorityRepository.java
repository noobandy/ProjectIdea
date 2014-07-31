/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.GroupAuthority;

/**
 * @author Anand
 *
 */
public interface IGroupAuthorityRepository {
	

	void saveGroupAuthority(GroupAuthority groupAuthority);
	

	void removeGroupAuthority(Long groupId,Long authorityId);
}
