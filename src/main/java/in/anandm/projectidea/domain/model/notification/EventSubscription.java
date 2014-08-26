/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

import in.anandm.projectidea.domain.model.user.User;

import java.util.Date;

/**
 * @author anandm
 * 
 */
public class EventSubscription {

	private Long id;
	private User subscriber;
	private Event event;
	private Date subscribedAt;
	private Date unsubscribedAt;

	/**
	 * 
	 */
	EventSubscription() {
		super();

	}

	/**
	 * @param subscriber
	 * @param event
	 */
	EventSubscription(User subscriber, Event event) {
		super();
		this.subscriber = subscriber;
		this.event = event;
		this.subscribedAt = new Date();
	}

	public void unsubcribe() {
		unsubscribedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public User getSubscriber() {
		return subscriber;
	}

	public Event getEvent() {
		return event;
	}

	public Date getSubscribedAt() {
		return subscribedAt;
	}

	public Date getUnsubscribedAt() {
		return unsubscribedAt;
	}

}
