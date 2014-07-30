/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.Tag;

import java.util.List;

/**
 * @author anandm
 *
 */
public interface ITagRepository {

	List<Tag> findAllTags();
}
