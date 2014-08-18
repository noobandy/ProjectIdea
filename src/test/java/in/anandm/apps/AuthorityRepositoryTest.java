/**
 * 
 */
package in.anandm.apps;

import in.anandm.projectidea.domain.model.authority.Authority;
import in.anandm.projectidea.domain.model.authority.AuthorityConstants;
import in.anandm.projectidea.domain.model.authority.AuthorityRepository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Anand
 *
 */
public class AuthorityRepositoryTest extends BaseTest {

	@Autowired
	private AuthorityRepository fixture; 
	
	/**
	 * Test method for {@link in.anandm.projectidea.infrastructure.persistence.jpa.AuthorityRepositoryImpl#saveAuthority(in.anandm.projectidea.domain.model.authority.Authority)}.
	 */
	@Test
	public void testAuthority() {
		Assert.assertNotNull(fixture);
	}

	@Test
	public void testAuthorityAdd(){
		Authority authority = new Authority(AuthorityConstants.ROLE_USER);
		
		fixture.saveAuthority(authority);
		
	}
}
