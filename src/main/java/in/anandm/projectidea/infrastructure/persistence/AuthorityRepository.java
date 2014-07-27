/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence;

import in.anandm.projectidea.domain.model.Authority;
import in.anandm.projectidea.domain.model.AuthorityConstants;
import in.anandm.projectidea.domain.repository.IAuthorityRepository;

import org.springframework.stereotype.Repository;

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
	@Override
	public void saveAuthority(Authority authority) {
		save(authority);

	}

	@Override
	public Authority findAuthorityByName(AuthorityConstants authorityName) {

		Search search = new Search(Authority.class);
		search.addFilter(Filter.equal("authority", authorityName));

		return super.searchUnique(search);
	}

}
