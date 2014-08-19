/**
 * 
 */
package in.anandm.projectidea.infrastructure.messaging.jms;

import in.anandm.projectidea.application.ApplicationEvents;
import in.anandm.projectidea.domain.model.events.ProjectIdeaPublishedEvent;
import in.anandm.projectidea.domain.model.events.ReviewCretedEvent;

/**
 * @author anandm
 * 
 */
public class JMSApplicationEventsImpl implements ApplicationEvents {

	@Override
	public void projectIdeaWasPublished(ProjectIdeaPublishedEvent event) {

	}

	@Override
	public void reviewWasCreted(ReviewCretedEvent event) {

	}

}
