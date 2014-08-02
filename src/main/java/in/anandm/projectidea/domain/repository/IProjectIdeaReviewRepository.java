/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.ProjectIdeaReview;

/**
 * @author anandm
 * 
 */
public interface IProjectIdeaReviewRepository {

	void saveProjectIdeaReview(ProjectIdeaReview projectIdeaReview);

	void removeProjectIdeaReviewById(Long id);

}
