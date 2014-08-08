/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.Page;
import in.anandm.projectidea.domain.model.Rule;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;

/**
 * @author anandm
 *
 */
@Repository
public class BaseRepository<T, ID extends Serializable> extends
		GenericDAOImpl<T, ID> {

	@Autowired
	@Override
	public void setSearchProcessor(JPASearchProcessor searchProcessor) {

		super.setSearchProcessor(searchProcessor);
	}

	@PersistenceContext
	@Override
	public void setEntityManager(EntityManager entityManager) {

		super.setEntityManager(entityManager);
	}

	
	 public List<T> findAll(
	            final int page,
	            final int maxRes,
	            final String column,
	            final boolean desc,
	            final boolean isSearch,
	            final String baseFilters,
	            final in.anandm.projectidea.domain.model.Filter filters)
	            throws ClassNotFoundException {
	        Search search = new Search();
	        search.setFirstResult(-1);
	        search.addSort(column, desc);
	        search.setMaxResults(maxRes);
	        search.setPage(page);
	        if (baseFilters != null) {
	            search = applyBaseFilters(search, baseFilters);
	        }
	        if (isSearch && filters != null) {
	            search = applyFilters(search, filters);
	        }
	        List<T> records = this.search(search);
	        return records;
	    }

	    /**
	     * Count.
	     *
	     * @param isSearch the is search
	     * @param baseFilters the base filters
	     * @param filters the filters
	     * @return the int
	     * */
	    public int count(
	            final boolean isSearch,
	            final String baseFilters,
	            final in.anandm.projectidea.domain.model.Filter filters) {
	        Search search = new Search();
	        if (baseFilters != null) {
	            search = applyBaseFilters(search, baseFilters);
	        }
	        if (isSearch && filters != null) {
	            search = applyFilters(search, filters);
	        }
	        return this.count(search);
	    }

	    /**
	     * Apply base filters.
	     *
	     * @param search the search
	     * @param baseFilters the base filters
	     * @return the search
	     */
	    private Search applyBaseFilters(final Search search, final String baseFilters) {
	        String[] arrFilters = baseFilters.split(",");
	        for (String filter : arrFilters) {
	            String[] tokens = filter.split("=");
	            Filter f = Filter.equal(tokens[0], tokens[1]);
	            search.addFilter(f);
	        }
	        return search;
	    }

	    /**
	     * Apply filters.
	     *
	     * @param search the search
	     * @param filters the filters
	     * @return the search
	     */
	    private Search applyFilters(
	            final Search search,
	            final in.anandm.projectidea.domain.model.Filter filters) {
	        Filter[] filterRules = new Filter[filters.getRules().length];
	        int count = 0;
	        for (Rule rule : filters.getRules()) {
	            String data = rule.getData();
	            if (rule.getOp().equalsIgnoreCase("bw")) {
	                data = data + "%";
	            } else if (rule.getOp().equalsIgnoreCase("ew")) {
	                data = "%" + data;
	            } else if (rule.getOp().equalsIgnoreCase("cn")) {
	                data = "%" + data + "%";
	            }
	            Filter filter = null;
	            filter = new Filter(rule.getField(), data, rule.getMappedOperator());
	            filterRules[count++] = filter;
	        }
	        if (filters.getGroupOp().equalsIgnoreCase("AND")) {
	            search.addFilterAnd(filterRules);
	        } else {
	            search.addFilterOr(filterRules);
	        }
	        return search;
	    }

	    /**
	     * Find all.
	     *
	     * @param page the page
	     * @param maxRes the max res
	     * @param column the column
	     * @param desc the desc
	     * @param isSearch the is search
	     * @param baseFilters the base filters
	     * @param filters the filters
	     * @return the list
	     * @throws ClassNotFoundException the class not found exception*/
	    public List<T> findAll(
	            final String column,
	            final boolean desc)
	            throws ClassNotFoundException {
	        Search search = new Search();
	        search.addSort(column, desc);
	        List<T> records = this.search(search);
	        return records;
	    }
	public Page<T> page(Integer pageNumber, Integer itemsPerPage) {
		Search search = new Search();

		int start = (pageNumber - 1) * itemsPerPage;

		search.setFirstResult(start);
		search.setMaxResults(itemsPerPage);

		SearchResult<T> result = searchAndCount(search);

		Page<T> page = new Page<T>(pageNumber, itemsPerPage,
				result.getTotalCount(), result.getResult());

		return page;
	}
}
