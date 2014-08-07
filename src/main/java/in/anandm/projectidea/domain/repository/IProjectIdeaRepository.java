/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.Page;
import in.anandm.projectidea.domain.model.ProjectIdea;

/**
 * @author anandm
 * 
 */
public interface IProjectIdeaRepository {

	
	void saveProjectIdea(ProjectIdea projectIdea);

	ProjectIdea findProjectIdeaById(Long id);
	
	Page<ProjectIdea> page(Integer pageNumber,Integer itemsPerPage);
}
