/**
 * 
 */
package in.anandm.projectidea.infrastructure.messaging.container;

import in.anandm.projectidea.domain.model.events.ProjectIdeaPublishedEvent;
import in.anandm.projectidea.domain.model.notification.Event;
import in.anandm.projectidea.domain.model.notification.EventInstance;
import in.anandm.projectidea.domain.model.notification.EventInstanceRepository;
import in.anandm.projectidea.domain.model.notification.EventRepository;
import in.anandm.projectidea.domain.model.notification.EventSubscription;
import in.anandm.projectidea.domain.model.notification.EventSubscriptionQuery;
import in.anandm.projectidea.domain.model.notification.EventSubscriptionRepository;
import in.anandm.projectidea.domain.model.notification.Notification;
import in.anandm.projectidea.domain.model.notification.NotificationRepository;
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

	@Autowired
	private NotificationRepository notificationRepository;

	private EventRepository eventRepository;

	private EventInstanceRepository eventInstanceRepository;

	private EventSubscriptionRepository eventSubscriptionRepository;

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public void onApplicationEvent(ProjectIdeaPublishedEvent event) {
		ProjectIdea projectIdea = event.getProjectIdea();

		User author = projectIdea.getAuthor();

		Event eventChannel = eventRepository.findByTitle(Event
				.userPublishedAProjectIdeaEvent(author));

		EventInstance eventInstance = eventChannel.newInstance(author);

		eventInstanceRepository.saveEventInstance(eventInstance);

		EventSubscriptionQuery query = new EventSubscriptionQuery();

		query.title(eventChannel.getTitle());

		long count = eventSubscriptionRepository.count(query);

		for (int i = 0; i < count; i = i + 100) {
			query.start(i);
			query.maxResult(i + 100);
			for (EventSubscription subscription : eventSubscriptionRepository
					.list(query)) {
				Notification notification = new Notification(eventInstance,
						subscription.getSubscriber());

				notificationRepository.saveNotification(notification);
				Broadcaster broadcaster = BroadcasterFactory.getDefault()
						.lookup(notification.getRecipient());
				try {
					broadcaster.broadcast(mapper
							.writeValueAsString(notification));
				} catch (JsonGenerationException e) {

					e.printStackTrace();
				} catch (JsonMappingException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}
}
