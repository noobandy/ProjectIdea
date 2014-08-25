/**
 * 
 */
package in.anandm.projectidea.infrastructure.messaging.container;

import in.anandm.projectidea.application.ApplicationEvents;
import in.anandm.projectidea.domain.model.events.ProjectIdeaPublishedEvent;
import in.anandm.projectidea.domain.model.events.ReviewCretedEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author anandm
 * 
 */
@Component
public class ApplicationEventsImpl implements ApplicationEvents,
		ApplicationEventPublisherAware {

	private ApplicationEventPublisher eventPublisher;

	@Override
	public void setApplicationEventPublisher(
			ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}

	@Override
	public void projectIdeaWasPublished(ProjectIdeaPublishedEvent event) {
		eventPublisher.publishEvent(event);
	}

	@Override
	public void reviewWasCreted(ReviewCretedEvent event) {
		eventPublisher.publishEvent(event);
	}

}
