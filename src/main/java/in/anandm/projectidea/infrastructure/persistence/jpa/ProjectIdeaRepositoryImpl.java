/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.application.util.StringUtility;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaQuery;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaRepository;
import in.anandm.projectidea.domain.model.projectidea.Status;
import in.anandm.projectidea.domain.shared.QueryResult;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Sort;

/**
 * @author Anand
 * 
 */
@Repository
public class ProjectIdeaRepositoryImpl extends
		BaseRepository<ProjectIdea, Long> implements ProjectIdeaRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.repository.IProjectIdeaRepository#
	 * saveProjectIdea(in.anandm.projectidea.domain.model.ProjectIdea)
	 */
	@Override
	@Transactional
	public void saveProjectIdea(ProjectIdea projectIdea) {

		save(projectIdea);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.repository.IProjectIdeaRepository#
	 * findProjectIdeaById(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public ProjectIdea findProjectIdeaById(Long id) {
		return find(id);
	}

	@Override
	public List<ProjectIdea> list(ProjectIdeaQuery query) {
		Search search = queryToSearch(query);
		return search(search);
	}

	@Override
	public long count(ProjectIdeaQuery query) {

		Search search = queryToSearch(query);
		return count(search);
	}

	@Override
	public QueryResult<ProjectIdea> query(ProjectIdeaQuery query) {
		Search search = queryToSearch(query);
		SearchResult<ProjectIdea> searchResult = searchAndCount(search);
		return new QueryResult<ProjectIdea>(searchResult.getResult(),
				searchResult.getTotalCount());
	}

	private Search queryToSearch(ProjectIdeaQuery query) {
		Search search = new Search();

		String author = query.getAuthor();
		String tag = query.getTag();
		Status status = query.getStatus();
		boolean orderByRating = query.isOrderByRating();
		Integer start = query.getStart();
		Integer maxResults = query.getMaxResults();
		String searchText = query.getSearchText();
		List<Filter> filters = new ArrayList<Filter>();

		Sort sort = Sort.desc("lastModified");

		if (StringUtility.hasText(tag)) {
			Filter tagFilter = Filter.some("specifications.tags",
					Filter.equal("tag", tag.trim()));
			filters.add(tagFilter);
		}

		if (StringUtility.hasText(author)) {
			Filter authorFilter = Filter
					.equal("author.username", author.trim());
			filters.add(authorFilter);
		}
		if (status != null) {

			Filter statusFilter = Filter.equal("status", status);
			filters.add(statusFilter);
		}

		if (StringUtility.hasText(searchText)) {
			Filter titleFilter = Filter.ilike("title",
					StringUtility.toLikeString(searchText));
			filters.add(titleFilter);
		}

		if (status != null && status == Status.PUBLISHED && orderByRating) {
			// change default sort
		}

		Filter[] filterArray = new Filter[filters.size()];
		filterArray = filters.toArray(filterArray);

		search.addFilterAnd(filterArray);

		search.addSort(sort);

		if (start != null && maxResults != null) {
			search.setFirstResult(start);
			search.setMaxResults(maxResults);
		}
		return search;
	}

}
