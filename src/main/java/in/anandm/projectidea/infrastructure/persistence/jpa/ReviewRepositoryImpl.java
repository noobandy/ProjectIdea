/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.projectidea.QueryResult;
import in.anandm.projectidea.domain.model.review.Review;
import in.anandm.projectidea.domain.model.review.ReviewQuery;
import in.anandm.projectidea.domain.model.review.ReviewRepository;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author anandm
 * 
 */
@Repository
public class ReviewRepositoryImpl extends BaseRepository<Review, Long>
		implements ReviewRepository {

	@Override
	public void saveProjectIdeaReview(Review review) {
		save(review);
	}

	@Override
	public void removeProjectIdeaReviewById(Long id) {
		removeById(id);
	}

	@Override
	public Review findReviewById(long id) {
		
		return null;
	}

	@Override
	public List<Review> list(ReviewQuery query) {
		
		return null;
	}

	@Override
	public long count(ReviewQuery query) {
		
		return 0;
	}

	@Override
	public QueryResult<Review> query(ReviewQuery query) {
		
		return null;
	}

}
