/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.ProjectIdea;
import in.anandm.projectidea.domain.repository.IProjectIdeaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Anand
 * 
 */
@Repository
public class ProjectIdeaRepository extends BaseRepository<ProjectIdea, Long>
		implements IProjectIdeaRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.repository.IProjectIdeaRepository#
	 * saveProjectIdea(in.anandm.projectidea.domain.model.ProjectIdea)
	 */
	@Override
	@Transactional
	public void saveProjectIdea(ProjectIdea projectIdea) {
		
		save(projectIdea);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.repository.IProjectIdeaRepository#
	 * findProjectIdeaById(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=true)
	public ProjectIdea findProjectIdeaById(Long id) {
		return find(id);
	}

}
