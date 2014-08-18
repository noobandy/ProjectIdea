/**
 * 
 */
package in.anandm.projectidea.domain.model.review;

import in.anandm.projectidea.application.util.ArgumentValidator;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.user.User;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	Review() {
		super();
	}

	public Review(ProjectIdea projectIdea, int star, String review, User user) {
		super();
		ArgumentValidator.notNull(projectIdea, "Project idea is null");
		ArgumentValidator.notNull(user, "author is null");
		ArgumentValidator.between(0, 5, star,
				"value of star must be between 0 - 5 , start : " + star);

		this.projectIdea = projectIdea;
		this.star = star;
		this.remark = review;
		this.user = user;
		lastModified = new Date();
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
