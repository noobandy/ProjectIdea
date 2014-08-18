/**
 * 
 */
package in.anandm.projectidea.domain.model.tag;


import java.util.List;

/**
 * @author anandm
 *
 */
public interface TagRepository {

	List<Tag> findAllTags();
}
