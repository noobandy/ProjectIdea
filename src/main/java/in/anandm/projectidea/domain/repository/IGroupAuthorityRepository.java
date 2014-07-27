/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import org.springframework.transaction.annotation.Transactional;

import in.anandm.projectidea.domain.model.GroupAuthority;

/**
 * @author Anand
 *
 */
public interface IGroupAuthorityRepository {
	
	@Transactional
	void saveGroupAuthority(GroupAuthority groupAuthority);
	
	@Transactional
	void removeGroupAuthority(Long groupId,Long authorityId);
}
