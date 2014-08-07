/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import java.util.List;

import in.anandm.projectidea.domain.model.Authority;
import in.anandm.projectidea.domain.model.AuthorityConstants;
import in.anandm.projectidea.domain.repository.IAuthorityRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author Anand
 *
 */
@Repository
public class AuthorityRepository extends BaseRepository<Authority, Long>
		implements IAuthorityRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * in.anandm.projectidea.domain.repository.IAuthorityRepository#addAuthority
	 * (in.anandm.projectidea.domain.model.Authority)
	 */

	@Transactional
	@Override
	public void saveAuthority(Authority authority) {
		save(authority);

	}

	@Override
	@Transactional(readOnly = true)
	public Authority findAuthorityByName(AuthorityConstants authorityName) {

		Search search = new Search(Authority.class);
		search.addFilter(Filter.equal("authority", authorityName));

		return super.searchUnique(search);
	}

	@Override
	public List<Authority> findAllAuthority() {
		return findAll();
	}

}
