/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.tag.Tag;
import in.anandm.projectidea.domain.model.tag.TagRepository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author anandm
 * 
 */
@Repository
public class TagRepositoryImpl extends BaseRepository<Tag, String> implements
		TagRepository {

	@Override
	@Transactional(readOnly = true)
	public List<Tag> findAllTags() {

		return findAll();
	}

}
