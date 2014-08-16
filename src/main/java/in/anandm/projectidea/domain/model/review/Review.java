/**
 * 
 */
package in.anandm.projectidea.domain.model.review;

import in.anandm.projectidea.domain.model.ProjectIdea;
import in.anandm.projectidea.domain.model.User;

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
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private ProjectIdea projectIdea;

	private int star;
	private String remark;

	@ManyToOne
	private User user;

	Review() {
		super();
	}

	public Review(ProjectIdea projectIdeaId, int star, String review, User user) {
		super();
		this.projectIdea = projectIdeaId;
		this.star = star;
		this.remark = review;
		this.user = user;
	}

	// getters
	public Long getId() {
		return id;
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
