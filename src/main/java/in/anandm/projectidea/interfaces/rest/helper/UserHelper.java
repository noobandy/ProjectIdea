/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.helper;

import in.anandm.projectidea.domain.model.User;
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
public class UserHelper extends BaseRepository<User, Long> {

	public Page<User> getPage(Page<User> page) {
		Search search = new Search(User.class);

		int start = (page.getPage() - 1) * page.getItemsPerPage();

		search.setFirstResult(start);
		search.setMaxResults(page.getItemsPerPage());

		SearchResult<User> result = searchAndCount(search);

		page.setTotlaItems(result.getTotalCount());

		page.setData(result.getResult());

		return page;
	}
}
