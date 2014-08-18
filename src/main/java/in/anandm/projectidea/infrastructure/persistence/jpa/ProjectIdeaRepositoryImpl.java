/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaQuery;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaRepository;
import in.anandm.projectidea.domain.shared.QueryResult;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Anand
 * 
 */
@Repository
public class ProjectIdeaRepositoryImpl extends
		BaseRepository<ProjectIdea, Long> implements ProjectIdeaRepository {

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
	@Transactional(readOnly = true)
	public ProjectIdea findProjectIdeaById(Long id) {
		return find(id);
	}

	@Override
	public List<ProjectIdea> list(ProjectIdeaQuery query) {

		return null;
	}

	@Override
	public long count(ProjectIdeaQuery query) {

		return 0;
	}

	@Override
	public QueryResult<ProjectIdea> query(ProjectIdeaQuery query) {

		return null;
	}

}
