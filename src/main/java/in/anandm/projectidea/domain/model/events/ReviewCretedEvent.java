/**
 * 
 */
package in.anandm.projectidea.domain.model.events;

import in.anandm.projectidea.domain.model.review.Review;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.ApplicationEvent;

/**
 * @author anandm
 * 
 */
public class ReviewCretedEvent extends ApplicationEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Review review;
	private Date createdOn;

	/**
	 * @param review
	 * @param createdOn
	 */
	public ReviewCretedEvent(Object source, Review review, Date createdOn) {
		super(source);
		this.review = review;
		this.createdOn = createdOn;
	}

	public Review getReview() {
		return review;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

}
