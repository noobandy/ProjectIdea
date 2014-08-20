/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.dto;


/**
 * @author anandm
 * 
 */
public class ReviewCommand {

	
	private long projectIdeaId;
	private int stars;
	private String remarks;
	private String author;

	/**
	 * 
	 */
	public ReviewCommand() {
		super();

	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getProjectIdeaId() {
		return projectIdeaId;
	}

	public void setProjectIdeaId(long projectIdeaId) {
		this.projectIdeaId = projectIdeaId;
	}

	@Override
	public String toString() {
		return "ReviewCommand [projectIdeaId=" + projectIdeaId + ", stars="
				+ stars + ", remarks=" + remarks + ", author=" + author + "]";
	}

	

}
