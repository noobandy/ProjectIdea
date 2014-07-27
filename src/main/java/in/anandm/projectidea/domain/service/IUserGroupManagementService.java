/**
 * 
 */
package in.anandm.projectidea.domain.service;

import in.anandm.projectidea.domain.model.AuthorityConstants;

import java.util.List;

/**
 * @author Anand
 *
 */
public interface IUserGroupManagementService {

	void addUserInGroup(String username,String groupName);
	
	void removeUserFormGroup(String username,String groupName);
	
	void grantAuthorityToUser(String username,AuthorityConstants authority);
	
	void revokeAuthorityOfUser(String username,AuthorityConstants authority);
	
	void grantAuthorityToGroup(String groupName,AuthorityConstants authority);
	
	void revokeAuthorityOfGroup(String groupName,AuthorityConstants authority);
	
	List<AuthorityConstants> getGrantedAuthoritiesOfUser(String username);
	
	
}
