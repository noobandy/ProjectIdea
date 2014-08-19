/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.application.util.StringUtility;
import in.anandm.projectidea.domain.model.notification.Notification;
import in.anandm.projectidea.domain.model.notification.NotificationQuery;
import in.anandm.projectidea.domain.model.notification.NotificationRepository;
import in.anandm.projectidea.domain.shared.QueryResult;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Sort;

/**
 * @author anandm
 * 
 */
public class NotificationRepositoryImpl extends
		BaseRepository<Notification, Long> implements NotificationRepository {

	@Override
	public void saveNotification(Notification notification) {
		save(notification);
	}

	@Override
	public QueryResult<Notification> query(NotificationQuery query) {

		Search search = queryToSearch(query);

		SearchResult<Notification> result = searchAndCount(search);

		QueryResult<Notification> queryResult = new QueryResult<Notification>(
				result.getResult(), result.getTotalCount());
		return queryResult;
	}

	private Search queryToSearch(NotificationQuery query) {
		Search search = new Search(Notification.class);
		String recipient = query.getRecipient();
		Integer start = query.getStart();
		Integer maxResults = query.getMaxResults();
		boolean dismissed = query.isDismissed();

		List<Filter> filters = new ArrayList<Filter>();

		if (StringUtility.hasText(recipient)) {
			Filter recipientFilter = Filter.equal("recipient.username",
					recipient);
			filters.add(recipientFilter);
		}

		Filter nonDismissedFilter = Filter.equal("dismissed", dismissed);
		filters.add(nonDismissedFilter);

		Sort recentFirst = Sort.desc("createdOn");

		Filter[] filterArray = new Filter[filters.size()];
		filterArray = filters.toArray(filterArray);

		search.addFilterAnd(filterArray);

		search.addSort(recentFirst);

		if (start != null && maxResults != null) {
			search.setFirstResult(start);
			search.setMaxResults(maxResults);
		}

		return search;
	}
}
