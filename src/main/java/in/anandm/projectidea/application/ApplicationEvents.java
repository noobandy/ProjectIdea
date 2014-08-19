/**
 * 
 */
package in.anandm.projectidea.application;

import in.anandm.projectidea.domain.model.events.ProjectIdeaPublishedEvent;
import in.anandm.projectidea.domain.model.events.ReviewCretedEvent;

/**
 * @author anandm
 * 
 */
public interface ApplicationEvents {

	void projectIdeaWasPublished(ProjectIdeaPublishedEvent event);

	void reviewWasCreted(ReviewCretedEvent event);
}
