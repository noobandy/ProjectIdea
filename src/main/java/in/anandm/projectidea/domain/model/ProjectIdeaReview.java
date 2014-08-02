/**
 * 
 */
package in.anandm.projectidea.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Anand
 * 
 */
@Entity
public class ProjectIdeaReview {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long projectIdeaId;

	private int star;
	private String remark;

	@ManyToOne
	private User user;

	ProjectIdeaReview() {
		super();
	}

	public ProjectIdeaReview(Long projectIdeaId, int star, String review,
			User user) {
		super();
		this.projectIdeaId = projectIdeaId;
		this.star = star;
		this.remark = review;
		this.user = user;
	}

	// getters
	public Long getId() {
		return id;
	}

	public Long getProjectIdeaId() {
		return projectIdeaId;
	}

	public int getStar() {
		return star;
	}

	public String getRemark() {
		return remark;
	}

	public User getUser() {
		return user;
	}

}
