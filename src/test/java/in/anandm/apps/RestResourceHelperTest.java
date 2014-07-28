package in.anandm.apps;

import in.anandm.projectidea.domain.model.ProjectIdeaStatus;
import in.anandm.projectidea.interfaces.rest.helper.RestResourceHelper;
import in.anandm.projectidea.interfaces.rest.resource.Page;
import in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaSummary;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class RestResourceHelperTest extends BaseTest {

	@Autowired
	private RestResourceHelper helper;

	@Test
	public void test() {
		Page page = new Page<ProjectIdeaSummary>("java", 1, 5);

		page.setAuthor("user");
		page.setStatus(ProjectIdeaStatus.PUBLISHED.toString());

		page = helper.getPage(page);

		Assert.notNull(page);
	}

}
