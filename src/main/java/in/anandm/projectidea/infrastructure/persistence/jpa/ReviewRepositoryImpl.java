/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.application.util.StringUtility;
import in.anandm.projectidea.domain.model.review.Review;
import in.anandm.projectidea.domain.model.review.ReviewQuery;
import in.anandm.projectidea.domain.model.review.ReviewRepository;
import in.anandm.projectidea.domain.shared.QueryResult;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Sort;

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

		return find(id);
	}

	@Override
	public List<Review> list(ReviewQuery query) {

		Search search = queryToSearch(query);
		return search(search);
	}

	@Override
	public long count(ReviewQuery query) {

		Search search = queryToSearch(query);
		return count(search);
	}

	@Override
	public QueryResult<Review> query(ReviewQuery query) {

		Search search = queryToSearch(query);
		SearchResult<Review> searchResult = searchAndCount(search);
		return new QueryResult<Review>(searchResult.getResult(),
				searchResult.getTotalCount());
	}

	private Search queryToSearch(ReviewQuery query) {
		Search search = new Search(Review.class);
		String author = query.getAuthor();
		long projectIdeaId = query.getProjectIdeaId();

		Integer start = query.getStart();
		Integer maxResults = query.getMaxResults();

		List<Filter> filters = new ArrayList<Filter>();

		Sort sort = Sort.desc("lastModified");

		if (projectIdeaId > 0) {
			Filter projectIdeaFilter = Filter.equal("projectIdea.id",
					projectIdeaId);
			filters.add(projectIdeaFilter);
		}
		if (StringUtility.hasText(author)) {
			Filter authorFilter = Filter
					.equal("author.username", author.trim());
			filters.add(authorFilter);
		}

		Filter[] filterArray = new Filter[filters.size()];
		filterArray = filters.toArray(filterArray);

		search.addFilterAnd(filterArray);

		search.addSort(sort);

		if (start != null && maxResults != null) {
			search.setFirstResult(start);
			search.setMaxResults(maxResults);
		}
		return search;
	}
}
