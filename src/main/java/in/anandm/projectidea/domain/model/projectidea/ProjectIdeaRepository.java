/**
 * 
 */
package in.anandm.projectidea.domain.model.projectidea;

import java.util.List;

/**
 * @author anandm
 * 
 */
public interface ProjectIdeaRepository {

	void saveProjectIdea(ProjectIdea projectIdea);

	ProjectIdea findProjectIdeaById(Long id);

	List<ProjectIdea> list(ProjectIdeaQuery query);

	long count(ProjectIdeaQuery query);

	QueryResult<ProjectIdea> query(ProjectIdeaQuery query);
	
}
