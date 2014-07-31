/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.helper;

import in.anandm.projectidea.domain.model.ProjectIdea;
import in.anandm.projectidea.domain.model.ProjectIdeaStatus;
import in.anandm.projectidea.domain.model.User;
import in.anandm.projectidea.domain.repository.IUserRepository;
import in.anandm.projectidea.infrastructure.persistence.BaseRepository;
import in.anandm.projectidea.interfaces.rest.resource.Page;
import in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaSummary;
import in.anandm.projectidea.interfaces.rest.resource.TagCount;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author anandm
 * 
 */
@Component
public class RestResourceHelper extends BaseRepository<ProjectIdea, Long> {

	@Autowired
	private IUserRepository userRepository;

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

		page.setTotlaItems(count(search));

		int start = (page.getPage() - 1) * page.getItemsPerPage();

		search.setFirstResult(start);
		search.setMaxResults(page.getItemsPerPage());
		
		search.setResultMode(Search.RESULT_ARRAY);

		List<ProjectIdeaSummary> ideaSummaries = new ArrayList<ProjectIdeaSummary>();
		for (Object result : search(search)) {

			Object[] row = (Object[]) result;
			ideaSummaries.add(new ProjectIdeaSummary((Long) row[0],
					(String) row[1], (String) row[2]));

		}

		page.setData(ideaSummaries);

		return page;
	}

	public List<TagCount> getTagCount(ProjectIdeaStatus status) {
		Query query = em().createNativeQuery(
				"SELECT " + "  t.tag AS tag," + "  COUNT(pidt.tags) AS count "
						+ "FROM" + "  tag t "
						+ "  LEFT JOIN project_idea_tags pidt "
						+ "    ON t.tag = pidt.tags "
						+ "  LEFT JOIN project_idea pid "
						+ "    ON pidt.project_idea = pid.id "
						+ "WHERE pid.status =:status " + "GROUP BY t.tag ",
				"TagCount");

		query.setParameter("status", status.toString());
		return mapResultSet(query.getResultList());
	}

	public List<TagCount> getTagCountOfUser(String author,
			ProjectIdeaStatus status) {

		User user = userRepository.findUserByUserName(author);
		if (user != null) {
			Query query = em().createNativeQuery(
					"SELECT " + "  t.tag AS tag,"
							+ "  COUNT(pidt.tags) AS count " + "FROM"
							+ "  tag t "
							+ "  LEFT JOIN project_idea_tags pidt "
							+ "    ON t.tag = pidt.tags "
							+ "  LEFT JOIN project_idea pid "
							+ "    ON pidt.project_idea = pid.id "
							+ "WHERE pid.status =:status "
							+ "  AND pid.author =:authorId "
							+ "GROUP BY t.tag ", "TagCount");
			query.setParameter("status", status.toString());
			query.setParameter("authorId", user.getId());
			return mapResultSet(query.getResultList());
		} else {
			return Collections.EMPTY_LIST;
		}

	}

	private static List<TagCount> mapResultSet(List<Object> objects) {
		List<TagCount> tagCounts = new ArrayList<TagCount>();

		for (Object result : objects) {
			Object[] row = (Object[]) result;
			tagCounts.add(new TagCount((String) row[0], ((BigInteger) row[1]).intValue()));
		}
		return tagCounts;
	}
}
