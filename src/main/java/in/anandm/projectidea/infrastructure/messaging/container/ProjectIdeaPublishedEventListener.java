/**
 * 
 */
package in.anandm.projectidea.infrastructure.messaging.container;

import in.anandm.projectidea.domain.model.events.ProjectIdeaPublishedEvent;
import in.anandm.projectidea.domain.model.notification.Notification;
import in.anandm.projectidea.domain.model.notification.NotificationType;
import in.anandm.projectidea.domain.model.user.User;

import java.io.IOException;

import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author anandm
 * 
 */
@Component
public class ProjectIdeaPublishedEventListener implements
		ApplicationListener<ProjectIdeaPublishedEvent> {

	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void onApplicationEvent(ProjectIdeaPublishedEvent event) {
		// add notification
		// Notification notification = new
		// Notification(NotificationType.PROJECT_IDEA_PUBLISHED, "",
		// resourceURI, recipient)
		User author = event.getProjectIdea().getAuthor();
		Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(
				author.getUsername());
		StringBuilder messageBuilder = new StringBuilder("Project idea ");
		messageBuilder.append(event.getProjectIdea().getSpecifications()
				.getTitle());
		messageBuilder.append(' ');
		messageBuilder.append("published");
		Notification notification = new Notification(
				NotificationType.PROJECT_IDEA_PUBLISHED,
				messageBuilder.toString(), "projectIdea/1", author);
		
		try {
			broadcaster.broadcast(mapper.writeValueAsString(notification));
		} catch (JsonGenerationException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
