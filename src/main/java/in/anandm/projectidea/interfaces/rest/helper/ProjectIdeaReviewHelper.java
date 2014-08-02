/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.helper;

import in.anandm.projectidea.domain.model.ProjectIdeaReview;
import in.anandm.projectidea.infrastructure.persistence.jpa.BaseRepository;
import in.anandm.projectidea.interfaces.rest.resource.Page;

import org.springframework.stereotype.Component;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

/**
 * @author anandm
 * 
 */
@Component
public class ProjectIdeaReviewHelper extends
		BaseRepository<ProjectIdeaReview, Long> {

	public Page<ProjectIdeaReview> getProjectIdeaReviewPage(
			Page<ProjectIdeaReview> page, Long projectIdeaId) {
		Search search = new Search(ProjectIdeaReview.class);

		Filter projectIdeaFilter = Filter.equal("projectIdeaId", projectIdeaId);

		search.addFilter(projectIdeaFilter);

		int start = (page.getPage() - 1) * page.getItemsPerPage();

		search.setFirstResult(start);
		search.setMaxResults(page.getItemsPerPage());

		SearchResult<ProjectIdeaReview> result = searchAndCount(search);

		page.setTotlaItems(result.getTotalCount());
		page.setData(result.getResult());
		return page;
	}
}
