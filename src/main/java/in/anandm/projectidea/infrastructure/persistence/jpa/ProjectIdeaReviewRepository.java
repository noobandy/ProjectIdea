/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.ProjectIdeaReview;
import in.anandm.projectidea.domain.repository.IProjectIdeaReviewRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author anandm
 * 
 */
@Repository
public class ProjectIdeaReviewRepository extends
		BaseRepository<ProjectIdeaReview, Long> implements
		IProjectIdeaReviewRepository {

	@Override
	@Transactional
	public void saveProjectIdeaReview(ProjectIdeaReview projectIdeaReview) {
	}

	@Override
	@Transactional
	public void removeProjectIdeaReviewById(Long id) {
	}

}
