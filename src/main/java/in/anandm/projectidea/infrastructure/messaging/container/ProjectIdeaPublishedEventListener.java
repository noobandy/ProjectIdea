/**
 * 
 */
package in.anandm.projectidea.infrastructure.messaging.container;

import in.anandm.projectidea.domain.model.events.ProjectIdeaPublishedEvent;

import org.springframework.context.ApplicationListener;

/**
 * @author anandm
 *
 */
public class ProjectIdeaPublishedEventListener implements
ApplicationListener<ProjectIdeaPublishedEvent> {

	@Override
	public void onApplicationEvent(ProjectIdeaPublishedEvent event) {
		//add notification
		//Notification notification = new Notification(NotificationType.PROJECT_IDEA_PUBLISHED, "", resourceURI, recipient)
	}

}
