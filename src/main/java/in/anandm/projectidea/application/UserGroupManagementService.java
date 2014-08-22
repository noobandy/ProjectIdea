/**
 * 
 */
package in.anandm.projectidea.application;

import in.anandm.projectidea.domain.model.authority.AuthorityConstants;

import java.util.Set;

/**
 * @author Anand
 *
 */
public interface UserGroupManagementService {

	void addUserInGroup(String username,String groupName);
	
	void removeUserFormGroup(String username,String groupName);
	
	void grantAuthorityToUser(String username,AuthorityConstants authority);
	
	void revokeAuthorityOfUser(String username,AuthorityConstants authority);
	
	void grantAuthorityToGroup(String groupName,AuthorityConstants authority);
	
	void revokeAuthorityOfGroup(String groupName,AuthorityConstants authority);
	
	Set<AuthorityConstants> getGrantedAuthoritiesOfUser(String username);
	
	
}
