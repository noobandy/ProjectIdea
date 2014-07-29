/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import in.anandm.projectidea.domain.model.ProjectIdea;
import in.anandm.projectidea.domain.repository.IProjectIdeaRepository;

/**
 * @author Anand
 *
 */
@Repository
public class ProjectIdeaRepository extends BaseRepository<ProjectIdea, Long> implements
		IProjectIdeaRepository {

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IProjectIdeaRepository#saveProjectIdea(in.anandm.projectidea.domain.model.ProjectIdea)
	 */
	@Override
	public void saveProjectIdea(ProjectIdea projectIdea) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see in.anandm.projectidea.domain.repository.IProjectIdeaRepository#findProjectIdeaById(java.lang.Long)
	 */
	@Override
	public ProjectIdea findProjectIdeaById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
