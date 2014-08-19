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
	private int starts;
	private String remarks;
	private String author;

	/**
	 * 
	 */
	public ReviewCommand() {
		super();

	}

	public int getStarts() {
		return starts;
	}

	public void setStarts(int starts) {
		this.starts = starts;
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
		return "ReviewCommand [projectIdeaId=" + projectIdeaId + ", starts="
				+ starts + ", remarks=" + remarks + ", author=" + author + "]";
	}

	
}
