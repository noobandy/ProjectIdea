/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

import in.anandm.projectidea.domain.model.user.User;

import java.util.Date;

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

	private NotificationType type;
	private String message;
	private String resourceURI;
	private Date createdOn;
	private boolean dismissed;

	@ManyToOne
	private User recipient;

	/**
	 * 
	 */
	Notification() {
		super();

	}

	/**
	 * @param type
	 * @param resourceURI
	 */
	public Notification(NotificationType type, String message,
			String resourceURI, User recipient) {
		super();
		this.type = type;
		this.message = message;
		this.resourceURI = resourceURI;
		this.recipient = recipient;
	}

	public void dismiss() {
		dismissed = true;
	}

	public Long getId() {
		return id;
	}

	public NotificationType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public boolean isDismissed() {
		return dismissed;
	}

	public User getRecipient() {
		return recipient;
	}

}
