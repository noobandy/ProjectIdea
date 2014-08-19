/**
 * 
 */
package in.anandm.projectidea.domain.model.notification;

/**
 * @author anandm
 * 
 */
public class NotificationQuery {
	private String recipient;
	private boolean dismissed;
	private Integer start;
	private Integer maxResults;

	/**
	 * 
	 */
	public NotificationQuery() {
		super();

	}

	public NotificationQuery recipient(String recipient) {
		this.recipient = recipient;

		return this;
	}

	public NotificationQuery dismissed() {
		this.dismissed = true;

		return this;
	}

	public NotificationQuery start(int start) {
		this.start = start;

		return this;
	}

	public NotificationQuery maxResult(int maxResult) {
		this.maxResults = maxResult;

		return this;
	}

	public String getRecipient() {
		return recipient;
	}

	public boolean isDismissed() {
		return dismissed;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

}
