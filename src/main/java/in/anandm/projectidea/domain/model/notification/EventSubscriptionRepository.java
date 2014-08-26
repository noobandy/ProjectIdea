/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

import in.anandm.projectidea.domain.shared.QueryResult;

import java.util.List;

/**
 * @author anandm
 * 
 */
public interface EventSubscriptionRepository {

	void saveEventSubscription(EventSubscription subscription);

	List<EventSubscription> list(EventSubscriptionQuery query);

	long count(EventSubscriptionQuery query);
	
	QueryResult<EventSubscription> query(EventSubscriptionQuery query);
}
