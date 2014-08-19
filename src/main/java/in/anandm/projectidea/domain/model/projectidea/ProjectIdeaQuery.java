/**
 * 
 */
package in.anandm.projectidea.domain.model.projectidea;


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

	
}
