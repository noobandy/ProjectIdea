/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.resource;

import java.io.Serializable;

/**
 * @author anandm
 * 
 */
public class ProjectIdeaReview implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long projectIdeaId;
	private Integer stars;
	private String remark;
	private String author;

	/**
	 * 
	 */
	public ProjectIdeaReview() {
		super();

	}

	/**
	 * @param id
	 * @param projectIdeaId
	 * @param stars
	 * @param remark
	 * @param author
	 */
	public ProjectIdeaReview(Long id, Long projectIdeaId, Integer stars,
			String remark, String author) {
		super();
		this.id = id;
		this.projectIdeaId = projectIdeaId;
		this.stars = stars;
		this.remark = remark;
		this.author = author;
	}

	public Long getProjectIdeaId() {
		return projectIdeaId;
	}

	public void setProjectIdeaId(Long projectIdeaId) {
		this.projectIdeaId = projectIdeaId;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "ProjectIdeaReview [id=" + id + ", projectIdeaId="
				+ projectIdeaId + ", stars=" + stars + ", remark=" + remark
				+ ", author=" + author + "]";
	}

}
