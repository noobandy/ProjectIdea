/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

/**
 * @author anandm
 * 
 */
public interface EventInstanceRepository {

	void saveEventInstance(EventInstance eventInstance);

	void findEventInstanceById(Long id);

}
