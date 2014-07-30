/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence;

import in.anandm.projectidea.domain.model.Tag;
import in.anandm.projectidea.domain.repository.ITagRepository;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author anandm
 * 
 */
@Repository
public class TagRepository extends BaseRepository<Tag, String> implements
		ITagRepository {

	@Override
	public List<Tag> findAllTags() {

		return findAll();
	}

}
