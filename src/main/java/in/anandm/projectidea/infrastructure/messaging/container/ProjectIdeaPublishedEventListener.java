/**
 * 
 */
package in.anandm.projectidea.infrastructure.messaging.container;

import in.anandm.projectidea.domain.model.events.ProjectIdeaPublishedEvent;
import in.anandm.projectidea.domain.model.notification.Notification;
import in.anandm.projectidea.domain.model.notification.NotificationRepository;
import in.anandm.projectidea.domain.model.notification.NotificationType;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.user.User;

import java.io.IOException;

import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author anandm
 * 
 */
@Component
public class ProjectIdeaPublishedEventListener implements
		ApplicationListener<ProjectIdeaPublishedEvent> {

	private static final String PROJECT_IDEA_ID = "projectIdeaId";

	@Autowired
	private NotificationRepository notificationRepository;

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onApplicationEvent(ProjectIdeaPublishedEvent event) {
		// add notification
		// Notification notification = new
		// Notification(NotificationType.PROJECT_IDEA_PUBLISHED, "",
		// resourceURI, recipient)
		ProjectIdea projectIdea = event.getProjectIdea();

		User author = projectIdea.getAuthor();

		StringBuilder messageBuilder = new StringBuilder("Project idea ");
		messageBuilder.append(event.getProjectIdea().getSpecifications()
				.getTitle());
		messageBuilder.append(' ');
		messageBuilder.append("published");

		Notification notification = new Notification(
				NotificationType.PROJECT_IDEA_PUBLISHED,
				messageBuilder.toString(), author);

		notification.putExtra(PROJECT_IDEA_ID,
				String.valueOf(projectIdea.getId()));

		notificationRepository.saveNotification(notification);

		Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(
				author.getUsername());

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
