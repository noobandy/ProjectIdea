/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.helper;

import in.anandm.projectidea.domain.model.Group;
import in.anandm.projectidea.infrastructure.persistence.jpa.BaseRepository;
import in.anandm.projectidea.interfaces.rest.resource.Page;

import org.springframework.stereotype.Component;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

/**
 * @author anandm
 * 
 */
@Component
public class GroupHelper extends BaseRepository<Group, Long> {

	public Page<Group> getPage(Page<Group> page) {
		Search search = new Search(Group.class);

		int start = (page.getPage() - 1) * page.getItemsPerPage();

		search.setFirstResult(start);
		search.setMaxResults(page.getItemsPerPage());

		SearchResult<Group> result = searchAndCount(search);

		page.setTotlaItems(result.getTotalCount());

		page.setData(result.getResult());

		return page;
	}
}
