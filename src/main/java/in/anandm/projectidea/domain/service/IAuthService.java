/**
 * 
 */
package in.anandm.projectidea.domain.service;

import in.anandm.projectidea.domain.model.AuthorityConstants;


/**
 * @author anandm
 *
 */
public interface IAuthService {

	
	boolean isAllowed(AuthorityConstants authority);
	
}
