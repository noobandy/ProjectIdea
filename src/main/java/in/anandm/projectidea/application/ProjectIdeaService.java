/**
 * 
 */
package in.anandm.projectidea.application;

import in.anandm.projectidea.domain.model.attachment.Attachment;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.projectidea.Specifications;
import in.anandm.projectidea.domain.model.review.Review;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author anandm
 * 
 */
public interface ProjectIdeaService {

	public ProjectIdea draftProjectidea(String author,
			Specifications specifications);

	public ProjectIdea updateDraftedProjectIdea(String author,
			long projectIdea, Specifications newSpecifications);

	public Review addReview(String author, long projectIdeaId, int starRating,
			String remark);

	public void deleteReview(String author, long reviewid);

	public Attachment addAttachment(String author, long projectIdea,MultipartFile file);

	public void deleteAttachment(String author, long attachementId);

	public void publishProjectIdea(String author, long projectIdeaId);

	public void archiveProjectIdea(String author, long projectIdeaId);

}
