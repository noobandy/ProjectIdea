/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

/**
 * @author anandm
 *
 */
public interface EventRepository {

	void saveEvent(Event event);
	Event findByTitle(String title);
	
}
