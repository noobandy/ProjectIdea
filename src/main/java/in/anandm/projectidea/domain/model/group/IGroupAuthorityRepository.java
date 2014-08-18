/**
 * 
 */
package in.anandm.projectidea.domain.model.group;


/**
 * @author Anand
 *
 */
public interface IGroupAuthorityRepository {
	

	void saveGroupAuthority(GroupAuthority groupAuthority);
	

	void removeGroupAuthority(Long groupId,Long authorityId);
}
