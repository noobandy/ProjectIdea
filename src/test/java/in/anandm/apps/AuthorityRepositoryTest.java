/**
 * 
 */
package in.anandm.apps;

import in.anandm.projectidea.domain.model.Authority;
import in.anandm.projectidea.domain.model.AuthorityConstants;
import in.anandm.projectidea.domain.repository.IAuthorityRepository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Anand
 *
 */
public class AuthorityRepositoryTest extends BaseTest {

	@Autowired
	private IAuthorityRepository fixture; 
	
	/**
	 * Test method for {@link in.anandm.projectidea.infrastructure.persistence.jpa.AuthorityRepository#saveAuthority(in.anandm.projectidea.domain.model.Authority)}.
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
