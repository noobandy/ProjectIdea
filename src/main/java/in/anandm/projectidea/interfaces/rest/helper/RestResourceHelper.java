/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.helper;

import in.anandm.projectidea.domain.model.ProjectIdea;
import in.anandm.projectidea.domain.model.ProjectIdeaStatus;
import in.anandm.projectidea.infrastructure.persistence.BaseRepository;
import in.anandm.projectidea.interfaces.rest.resource.Page;
import in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaSummary;
import in.anandm.projectidea.interfaces.rest.resource.TagCount;

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
public class RestResourceHelper extends BaseRepository<ProjectIdea, Long> {

	public Page<ProjectIdeaSummary> getPage(Page<ProjectIdeaSummary> page) {
		Search search = new Search(ProjectIdea.class);

		search.addField("id");
		search.addField("title");
		search.addField("description");

		Filter finalFilter = Filter.and();

		if (page.getAuthor() != null && !"".equals(page.getAuthor().trim())) {

			Filter authorFilter = Filter.equal("author.username", page
					.getAuthor().trim());

			finalFilter.add(authorFilter);
		}

		if (page.getStatus() != null) {

			Filter statusFilter = Filter.equal("status", page.getStatus());

			finalFilter.add(statusFilter);
		}

		if (page.getTag() != null && !"".equals(page.getTag().trim())) {

			Filter tagFilter = Filter.some("tags",
					Filter.equal("tag", page.getTag().trim()));

			finalFilter.add(tagFilter);
		}

		search.addFilter(finalFilter);

		int start = (page.getPage() - 1) * page.getItemsPerPage();

		search.setFirstResult(start);
		search.setMaxResults(page.getItemsPerPage());

		SearchResult<ProjectIdeaSummary> result = searchAndCount(search);

		page.setTotlaItems(result.getTotalCount());
		page.setData((ArrayList<ProjectIdeaSummary>) result.getResult());

		return page;
	}
	
	public List<TagCount> getTagCount(String author,ProjectIdeaStatus status){
		
		Search search = new Search(ProjectIdea.class);
		
		Filter finalFilter = Filter.and();

		if (author != null && !"".equals(author.trim())) {

			Filter authorFilter = Filter.equal("author.username",author.trim());

			finalFilter.add(authorFilter);
		}

		if (status != null) {

			Filter statusFilter = Filter.equal("status", status);

			finalFilter.add(statusFilter);
		}
		
		/**
		 * SELECT 
  t.tag,
  COUNT(*) 
FROM
  tag t 
  LEFT JOIN project_idea_tags pidt 
    ON t.tag = pidt.tags 
  LEFT JOIN project_idea pid 
    ON pidt.project_idea = pid.id 
GROUP BY t.tag 
		 */
		return null;
	}
	
}
