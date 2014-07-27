package in.anandm.apps;

import static org.junit.Assert.*;
import in.anandm.projectidea.domain.service.IUserGroupManagementService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class UserGroupManagementServicecTest extends BaseTest{

	@Autowired
	private IUserGroupManagementService fixture;
	
	@Test
	public void testUserGroupManagementServiceImpl() {
		Assert.notNull(fixture);
	}

	@Test
	public void testAddUserInGroup() {
		
	}

	@Test
	public void testRemoveUserFormGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGrantAuthorityToUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testRevokeAuthorityOfUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGrantAuthorityToGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testRevokeAuthorityOfGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGrantedAuthoritiesOfUser() {
		fail("Not yet implemented");
	}

}
