/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.authority.Authority;
import in.anandm.projectidea.domain.model.authority.AuthorityConstants;
import in.anandm.projectidea.domain.model.authority.AuthorityRepository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author Anand
 *
 */
@Repository
public class AuthorityRepositoryImpl extends BaseRepository<Authority, Long>
		implements AuthorityRepository {

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
