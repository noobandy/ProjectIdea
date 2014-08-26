/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

import in.anandm.projectidea.domain.model.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author anandm
 * 
 */
@Entity
public class Notification {

	@Id
	private Long id;

	@ManyToOne
	private EventInstance eventInstance;

	@ManyToOne
	private User recipient;

	private boolean dismissed;

	/**
	 * 
	 */
	Notification() {
		super();

	}

	/**
	 * @param eventInstance
	 * @param recipient
	 */
	public Notification(EventInstance eventInstance, User recipient) {
		super();
		this.eventInstance = eventInstance;
		this.recipient = recipient;
		dismissed = false;
	}

	public void dismiss() {
		dismissed = true;
	}

	public Long getId() {
		return id;
	}

	public boolean isDismissed() {
		return dismissed;
	}

	public User getRecipient() {
		return recipient;
	}

}
