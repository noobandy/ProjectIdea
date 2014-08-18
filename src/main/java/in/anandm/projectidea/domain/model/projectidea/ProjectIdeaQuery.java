/**
 * 
 */
package in.anandm.projectidea.domain.model.projectidea;

import in.anandm.projectidea.application.util.StringUtility;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.Sort;

/**
 * @author anandm
 * 
 */
public class ProjectIdeaQuery {
	private String author;
	private String tag;
	private Status status;
	private boolean orderByRating;
	private Integer start;
	private Integer maxResults;
	private String searchText;

	/**
	 * 
	 */
	public ProjectIdeaQuery() {
		super();

	}

	public ProjectIdeaQuery author(String author) {
		this.author = author;

		return this;
	}

	public ProjectIdeaQuery tag(String tag) {
		this.tag = tag;

		return this;
	}

	public ProjectIdeaQuery status(Status status) {
		this.status = status;

		return this;
	}

	public ProjectIdeaQuery start(int start) {
		this.start = start;

		return this;
	}

	public ProjectIdeaQuery maxResult(int maxResult) {
		this.maxResults = maxResult;

		return this;
	}

	public ProjectIdeaQuery search(String searchText) {
		this.searchText = searchText;

		return this;
	}

	public ProjectIdeaQuery newestFirst() {
		this.orderByRating = false;

		return this;
	}

	public ProjectIdeaQuery topRatedFirst() {
		this.orderByRating = true;

		return this;
	}

	public String getAuthor() {
		return author;
	}

	public String getTag() {
		return tag;
	}

	public Status getStatus() {
		return status;
	}

	public boolean isOrderByRating() {
		return orderByRating;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public String getSearchText() {
		return searchText;
	}

	Search queryToSearch(ProjectIdeaQuery query) {
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
