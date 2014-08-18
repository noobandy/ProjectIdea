/**
 * 
 */
package in.anandm.projectidea.domain.model.review;

import in.anandm.projectidea.domain.shared.QueryResult;

import java.util.List;

/**
 * @author anandm
 * 
 */
public interface ReviewRepository {

	void saveProjectIdeaReview(Review review);
	
	void removeProjectIdeaReviewById(Long id);

	Review findReviewById(long id);
	List<Review> list(ReviewQuery query);

	long count(ReviewQuery query);

	QueryResult<Review> query(ReviewQuery query);

}
