/**
 * 
 */
package in.anandm.projectidea.domain.model;

import in.anandm.projectidea.interfaces.rest.resource.EstimatedTime;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Anand
 * 
 */
@Entity
public class ProjectIdea {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	private String description;
	private Long estimatedTimeInMilliseconds;
	private String estimatedTimeInWords;

	@ManyToOne
	private User author;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	@Enumerated(EnumType.STRING)
	private ProjectIdeaStatus status;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	private Set<Tag> tags = new HashSet<Tag>();

	ProjectIdea() {
		super();
	}

	public static ProjectIdea draftNewIdea(User user, String title) {
		ProjectIdea idea = new ProjectIdea();
		idea.author = user;
		idea.title = title;
		idea.status = ProjectIdeaStatus.DRAFTED;
		idea.description = "";
		idea.lastModified = new Date();
		return idea;
	}

	public void updateEstimatedTime(EstimatedTime estimatedTime) {
		this.estimatedTimeInMilliseconds = estimatedTime.timeInMilliseconds();
		this.estimatedTimeInWords = estimatedTime.toString();
		lastModified = new Date();
	}

	public void updateDescription(String description) {
		this.description = description;
		lastModified = new Date();
	}

	public void addTag(Tag tag) {
		tags.add(tag);
		lastModified = new Date();
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);

		lastModified = new Date();
	}

	public void publishIdea() {
		status = ProjectIdeaStatus.PUBLISHED;
		lastModified = new Date();
	}

	// getters
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Long getEstimatedTimeInMilliseconds() {
		return estimatedTimeInMilliseconds;
	}

	public String getEstimatedTimeInWords() {
		return estimatedTimeInWords;
	}

	public User getAuthor() {
		return author;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public ProjectIdeaStatus getStatus() {
		return status;
	}

	public Set<Tag> getTags() {
		return tags;
	}

}
