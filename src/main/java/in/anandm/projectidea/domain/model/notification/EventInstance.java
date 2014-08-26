/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

import in.anandm.projectidea.domain.model.user.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author anandm
 * 
 */
@Entity
public class EventInstance {

	@ManyToOne
	private Event event;

	private Map<String, String> extras = new HashMap<String, String>();

	@ManyToOne
	private User actor;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	/**
	 * 
	 */
	EventInstance() {
		super();

	}

	/**
	 * @param event
	 * @param actor
	 * 
	 */
	EventInstance(Event event, User actor) {
		super();
		this.event = event;
		this.actor = actor;
		this.createdAt = new Date();
	}

	public void putExtra(String key, String value) {
		extras.put(key, value);
	}

	public void deleteExtra(String key) {
		extras.remove(key);
	}

	public Event getEvent() {
		return event;
	}

	public Map<String, String> getExtras() {
		return extras;
	}

	public User getActor() {
		return actor;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

}
