/**
 * 
 */
package in.anandm.projectidea.domain.model.projectidea;

import in.anandm.projectidea.domain.shared.QueryResult;

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
