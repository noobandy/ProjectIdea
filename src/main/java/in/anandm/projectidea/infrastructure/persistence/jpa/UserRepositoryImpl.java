/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.application.util.StringUtility;
import in.anandm.projectidea.domain.model.user.User;
import in.anandm.projectidea.domain.model.user.UserQuery;
import in.anandm.projectidea.domain.model.user.UserRepository;
import in.anandm.projectidea.domain.shared.QueryResult;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.Sort;

/**
 * @author Anand
 * 
 */
@Repository
public class UserRepositoryImpl extends BaseRepository<User, Long> implements
		UserRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.anandm.projectidea.domain.repository.IUserRepository#saveUser(in.anandm
	 * .projectidea.domain.model.User)
	 */
	@Override
	@Transactional
	public void saveUser(User user) {
		save(user);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.anandm.projectidea.domain.repository.IUserRepository#findUserByUserName
	 * (java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public User findUserByUserName(String username) {
		Search search = new Search(User.class);
		search.addFilter(Filter.equal("username", username));

		return searchUnique(search);
	}

	@Override
	public QueryResult<User> query(UserQuery query) {

		Search search = queryToSearch(query);
		SearchResult<User> searchResult = searchAndCount(search);
		return new QueryResult<User>(searchResult.getResult(),
				searchResult.getTotalCount());
	}

	@Override
	public List<User> list(UserQuery query) {

		Search search = queryToSearch(query);
		return search(search);
	}

	@Override
	public long count(UserQuery query) {

		Search search = queryToSearch(query);
		return count(search);
	}

	private Search queryToSearch(UserQuery query) {
		Search search = new Search(User.class);
		Integer start = query.getStart();
		Integer maxResults = query.getMaxResults();
		String searchText = query.getSearchText();
		List<Filter> filters = new ArrayList<Filter>();

		Sort sort = Sort.desc("lastModified");

		if (StringUtility.hasText(searchText)) {
			Filter titleFilter = Filter.ilike("username",
					StringUtility.toLikeString(searchText));
			filters.add(titleFilter);
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
