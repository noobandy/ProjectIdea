/**
 * 
 */
package in.anandm.apps;

import in.anandm.projectidea.domain.model.Group;
import in.anandm.projectidea.domain.repository.IGroupRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author Anand
 *
 */
public class GroupRepositoryTest extends BaseTest {

	@Autowired
	private IGroupRepository fixture;
	
	/**
	 * Test method for {@link in.anandm.projectidea.infrastructure.persistence.GroupRepository#saveGroup(in.anandm.projectidea.domain.model.Group)}.
	 */
	@Test
	public void testSaveGroup() {
		Group group = new Group("userGroup");
		fixture.saveGroup(group);
		
		Group foundGroup = fixture.findGroupByName("userGroup");
		Assert.notNull(foundGroup);
	}

}
