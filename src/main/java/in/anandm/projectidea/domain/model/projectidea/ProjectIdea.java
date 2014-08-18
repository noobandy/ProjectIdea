/**
 * 
 */
package in.anandm.projectidea.domain.model.projectidea;

import in.anandm.projectidea.application.util.ArgumentValidator;
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
 * @author anandm
 * 
 */
@Entity(name = "Idea")
public class ProjectIdea {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Specifications specifications;
	@ManyToOne
	private User author;
	private Status status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	/**
	 * 
	 */
	ProjectIdea() {
		super();

	}

	/**
	 * @param specifications
	 */
	public ProjectIdea(User author, Specifications specifications) {
		super();
		ArgumentValidator.notNull(author, "author is null");
		ArgumentValidator.notNull(specifications, "specifications is null");
		this.author = author;
		this.specifications = specifications;
		this.status = Status.DRAFTED;
	}

	public void updateSpecifications(Specifications specifications) {
		ArgumentValidator.notNull(specifications, "specifications is null");
		if (Status.DRAFTED.equals(status)) {
			this.specifications = specifications;
		} else {
			throw new IllegalStateException(
					"only drafted project's specification can be updated projectIdeaId : "
							+ id);
		}

	}

	public boolean isPublished() {
		return Status.PUBLISHED.equals(status);
	}

	public boolean isArchived() {
		return Status.ARCHIVED.equals(status);
	}

	public boolean isDrafted() {
		return Status.DRAFTED.equals(status);
	}

	public void publish() {
		status = Status.PUBLISHED;
	}

	public void archive() {
		status = Status.ARCHIVED;
	}

	public Specifications getSpecifications() {
		return specifications;
	}

	public Status getStatus() {
		return status;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public User getAuthor() {
		return author;
	}

	public Long getId() {
		return id;
	}

}
