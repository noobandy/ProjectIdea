/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

import in.anandm.projectidea.domain.model.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author anandm
 * 
 */
@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;

	/**
	 * 
	 */
	Event() {
		super();

	}

	/**
	 * @param title
	 */
	public Event(String title) {
		super();
		this.title = title;
	}

	public static String userPublishedAProjectIdeaEvent(User user) {
		StringBuilder builder = new StringBuilder(user.getUsername());
		builder.append(' ');
		builder.append("published a project idea");
		return builder.toString();
	}

	public static final String reviewCreatedEvent() {
		return "someone wrote a review for your project";
	}

	public EventSubscription subscribe(User user) {
		return new EventSubscription(user, this);
	}

	public EventInstance newInstance(User actor) {
		return new EventInstance(this, actor);
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Event other = (Event) obj;
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}
}
