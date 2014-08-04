/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.helper;

import in.anandm.projectidea.domain.model.ProjectIdeaReview;
import in.anandm.projectidea.infrastructure.persistence.jpa.BaseRepository;
import in.anandm.projectidea.interfaces.rest.resource.Page;

import java.util.ArrayList;
import java.util.List;

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

	public Page<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview> getProjectIdeaReviewPage(
			Page<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview> page,
			Long projectIdeaId) {
		Search search = new Search(ProjectIdeaReview.class);

		search.addField("id");
		search.addField("projectIdeaId");
		search.addField("star");
		search.addField("remark");
		search.addField("user.username");

		Filter projectIdeaFilter = Filter.equal("projectIdeaId", projectIdeaId);

		search.addFilter(projectIdeaFilter);

		int start = (page.getPage() - 1) * page.getItemsPerPage();

		search.setFirstResult(start);
		search.setMaxResults(page.getItemsPerPage());

		search.setResultMode(Search.RESULT_ARRAY);

		SearchResult<ProjectIdeaReview> result = searchAndCount(search);

		page.setTotlaItems(result.getTotalCount());

		List<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview> reviews = new ArrayList<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview>();

		for (Object obj : result.getResult()) {

			Object[] row = (Object[]) obj;
			reviews.add(new in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview(
					(Long) row[0], (Long) row[1], (Integer) row[2],
					(String) row[3], (String) row[4]));

		}

		page.setData(reviews);

		return page;
	}
}
