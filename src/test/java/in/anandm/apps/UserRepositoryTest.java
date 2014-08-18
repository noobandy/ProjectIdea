/**
 * 
 */
package in.anandm.apps;

import in.anandm.projectidea.domain.model.user.UserRepository;
import in.anandm.projectidea.domain.model.user.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author Anand
 *
 */
public class UserRepositoryTest extends BaseTest{

	@Autowired
	private UserRepository fixture;
	
	/**
	 * Test method for {@link in.anandm.projectidea.infrastructure.persistence.jpa.UserRepositoryImpl#saveUser(in.anandm.projectidea.domain.model.user.User)}.
	 */
	@Test
	public void testSaveUser() {
		User user = new User("Anand Mohan", "anandm@mkcl.org", "anand", "anand");
		fixture.saveUser(user);
		User foundUser = fixture.findUserByUserName("anand");
		Assert.notNull(foundUser);
	}

}
