/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

import in.anandm.projectidea.domain.shared.QueryResult;

/**
 * @author anandm
 * 
 */
public interface NotificationRepository {

	void saveNotification(Notification notification);

	QueryResult<Notification> query(NotificationQuery query);

}
