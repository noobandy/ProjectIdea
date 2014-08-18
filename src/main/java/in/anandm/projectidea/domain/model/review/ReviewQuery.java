/**
 * 
 */
package in.anandm.projectidea.domain.model.review;

/**
 * @author anandm
 * 
 */
public class ReviewQuery {
	private long projectIdeaId;
	private String author;
	private Integer start;
	private Integer maxResults;

	/**
	 * 
	 */
	public ReviewQuery() {
		super();
	}

	public ReviewQuery projectIdea(long projectIdeaId) {
		this.projectIdeaId = projectIdeaId;
		return this;
	}

	public ReviewQuery author(String author) {
		this.author = author;
		return this;
	}

	public ReviewQuery start(int start) {
		this.start = start;

		return this;
	}

	public ReviewQuery maxResult(int maxResult) {
		this.maxResults = maxResult;

		return this;
	}

	public long getProjectIdeaId() {
		return projectIdeaId;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public String getAuthor() {
		return author;
	}

}
