/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

/**
 * @author anandm
 * 
 */
public class EventSubscriptionQuery {
	private String eventTitle;
	private Integer start;
	private Integer maxResults;
	private String subscriber;

	/**
	 * 
	 */
	public EventSubscriptionQuery() {
		super();

	}

	public EventSubscriptionQuery title(String title) {
		eventTitle = title;
		return this;
	}

	public EventSubscriptionQuery subscriber(String subscriber) {
		this.subscriber = subscriber;
		return this;
	}

	public EventSubscriptionQuery start(int start) {
		this.start = start;

		return this;
	}

	public EventSubscriptionQuery maxResult(int maxResult) {
		this.maxResults = maxResult;

		return this;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public String getSubscriber() {
		return subscriber;
	}

}
