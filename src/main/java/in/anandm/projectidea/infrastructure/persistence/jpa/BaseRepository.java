/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.Group;
import in.anandm.projectidea.domain.model.Page;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
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
