/**
 * 
 */
package in.anandm.projectidea.domain.model.events;

import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.ApplicationEvent;

/**
 * @author anandm
 * 
 */
public class ProjectIdeaPublishedEvent extends ApplicationEvent implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectIdea projectIdea;
	private Date publishedOn;

	/**
	 * @param projectIdea
	 * @param publishedOn
	 */
	public ProjectIdeaPublishedEvent(Object source,ProjectIdea projectIdea, Date publishedOn) {
		super(source);
		this.projectIdea = projectIdea;
		this.publishedOn = publishedOn;
	}

	public ProjectIdea getProjectIdea() {
		return projectIdea;
	}

	public Date getPublishedOn() {
		return publishedOn;
	}

}
