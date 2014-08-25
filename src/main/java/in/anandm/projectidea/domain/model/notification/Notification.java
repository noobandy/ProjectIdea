/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

import in.anandm.projectidea.domain.model.user.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	private String message;
	private Map<String, String> extras = new HashMap<String, String>();
	private Date createdOn;

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
	 * 
	 */
	public Notification(NotificationType type, String message, User recipient) {
		super();
		this.type = type;
		this.message = message;
		this.recipient = recipient;
	}

	public void dismiss() {
		dismissed = true;
	}

	public void putExtra(String key, String value) {
		extras.put(key, value);
	}

	public void deleteExtra(String key) {
		extras.remove(key);
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public boolean isDismissed() {
		return dismissed;
	}

	public User getRecipient() {
		return recipient;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

}
