/**
 * 
 */
package in.anandm.projectidea.application.impl;

import in.anandm.projectidea.application.ProjectIdeaService;
import in.anandm.projectidea.domain.model.attachment.Attachment;
import in.anandm.projectidea.domain.model.attachment.AttachmentRepository;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaRepository;
import in.anandm.projectidea.domain.model.projectidea.Specifications;
import in.anandm.projectidea.domain.model.review.Review;
import in.anandm.projectidea.domain.model.review.ReviewQuery;
import in.anandm.projectidea.domain.model.review.ReviewRepository;
import in.anandm.projectidea.domain.model.user.User;
import in.anandm.projectidea.domain.model.user.UserRepository;
import in.anandm.projectidea.domain.shared.AccessDeniedException;
import in.anandm.projectidea.domain.shared.ApplicationException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author anandm
 * 
 */
@Service
public class ProjectIdeaServiceImpl implements ProjectIdeaService {

	private UserRepository userRepository;
	private ProjectIdeaRepository projectIdeaRepository;
	private ReviewRepository reviewRepository;
	private AttachmentRepository attachmentRepository;

	
	
	/**
	 * 
	 */
	ProjectIdeaServiceImpl() {
		super();
		
	}

	@Autowired
	/**
	 * @param userRepository
	 * @param projectIdeaRepository
	 * @param reviewRepository
	 * @param attachmentRepository
	 */
	public ProjectIdeaServiceImpl(UserRepository userRepository,
			ProjectIdeaRepository projectIdeaRepository,
			ReviewRepository reviewRepository,
			AttachmentRepository attachmentRepository) {
		super();
		this.userRepository = userRepository;
		this.projectIdeaRepository = projectIdeaRepository;
		this.reviewRepository = reviewRepository;
		this.attachmentRepository = attachmentRepository;
	}

	@Transactional
	@Override
	@PreAuthorize("hasRole('DRAFT_IDEA')")
	public ProjectIdea draftProjectidea(String author,
			Specifications specifications) {
		User user = userRepository.findUserByUserName(author);

		ProjectIdea projectIdea = new ProjectIdea(user, specifications);
		projectIdeaRepository.saveProjectIdea(projectIdea);
		return projectIdea;

	}
	
	@Transactional
	@Override
	@PreAuthorize("hasRole('DRAFT_IDEA')")
	public ProjectIdea updateDraftedProjectIdea(String author,
			long projectIdeaId, Specifications newSpecifications) {

		User user = userRepository.findUserByUserName(author);
		ProjectIdea idea = projectIdeaRepository
				.findProjectIdeaById(projectIdeaId);

		if (idea.getAuthor().equals(user)) {
			idea.updateSpecifications(newSpecifications);
			return idea;
		} else {
			throw new AccessDeniedException(author
					+ " is trying to update draft : " + projectIdeaId
					+ ", which is created by some one else");
		}

	}

	@Transactional
	@Override
	@PreAuthorize("hasRole('ADD_REVIEW')")
	public Review addReview(String author, long projectIdeaId, int starRating,
			String remark) {
		ReviewQuery query = new ReviewQuery();
		query.author(author);
		query.projectIdea(projectIdeaId);
		List<Review> reviews = reviewRepository.list(query);

		if (reviews.isEmpty()) {
			User user = userRepository.findUserByUserName(author);
			ProjectIdea idea = projectIdeaRepository
					.findProjectIdeaById(projectIdeaId);
			Review review = new Review(idea, starRating, remark, user);
			reviewRepository.saveProjectIdeaReview(review);
			return review;
		} else {
			throw new ApplicationException(
					"only one review per user per project idea is allowed, projectIdeaid : "
							+ projectIdeaId + ", author : " + author);
		}
	}

	@Transactional
	@Override
	@PreAuthorize("hasRole('DELETE_REVIEW')")
	public void deleteReview(String author, long reviewId) {
		User user = userRepository.findUserByUserName(author);
		Review review = reviewRepository.findReviewById(reviewId);
		if (review.getUser().equals(user)) {
			reviewRepository.removeProjectIdeaReviewById(reviewId);
		} else {
			throw new AccessDeniedException(author
					+ " is trying to delete review : " + reviewId
					+ ", which is created by some one else");
		}
	}

	@Transactional
	@Override
	@PreAuthorize("hasRole('DRAFT_IDEA')")
	public Attachment addAttachment(String author, long projectIdeaId,
			MultipartFile file) {
		User user = userRepository.findUserByUserName(author);
		ProjectIdea idea = projectIdeaRepository
				.findProjectIdeaById(projectIdeaId);
		if (idea.getAuthor().equals(user)) {
			if (idea.isDrafted()) {
				String fileName = file.getOriginalFilename();
				long size = file.getSize();
				String extension = fileName
						.substring(fileName.lastIndexOf('.') + 1);
				Attachment attachment = new Attachment(fileName, size,
						extension, idea);
				attachmentRepository.saveAttachment(attachment);
				return attachment;
			} else {
				throw new IllegalStateException(
						"project idea is alrady published : projectIdeaId : "
								+ projectIdeaId);
			}
		} else {
			throw new AccessDeniedException(author
					+ " is trying to add attachment for draft : "
					+ projectIdeaId + ", which is created by some one else");
		}
	}

	@Transactional
	@Override
	@PreAuthorize("hasRole('DRAFT_IDEA')")
	public void deleteAttachment(String author, long attachementId) {
		User user = userRepository.findUserByUserName(author);
		Attachment attachment = attachmentRepository
				.findAttachment(attachementId);

		ProjectIdea idea = attachment.getProjectIdea();

		if (idea.getAuthor().equals(user)) {
			if (idea.isDrafted()) {
				attachmentRepository.removeAttachment(attachementId);
			} else {
				throw new IllegalStateException(
						"project idea is alrady published : projectIdeaId : "
								+ idea.getId());
			}
		} else {
			throw new AccessDeniedException(author
					+ " is trying to delete attachment of draft : "
					+ idea.getId() + ", which is created by some one else");
		}
	}
	
	@Transactional
	@Override
	@PreAuthorize("hasRole('PUBLISH_IDEA')")
	public void publishProjectIdea(String author, long projectIdeaId) {
		User user = userRepository.findUserByUserName(author);
		ProjectIdea idea = projectIdeaRepository
				.findProjectIdeaById(projectIdeaId);

		if (idea.getAuthor().equals(user)) {
			idea.publish();
		} else {
			throw new AccessDeniedException(author
					+ " is trying to publish draft : " + projectIdeaId
					+ ", which is created by some one else");
		}
	}

	@Transactional
	@Override
	@PreAuthorize("hasRole('ARCHIVE_IDEA')")
	public void archiveProjectIdea(String author, long projectIdeaId) {
		User user = userRepository.findUserByUserName(author);
		ProjectIdea idea = projectIdeaRepository
				.findProjectIdeaById(projectIdeaId);

		if (idea.getAuthor().equals(user)) {
			idea.archive();
		} else {
			throw new AccessDeniedException(author
					+ " is trying to archive projectIdea : " + projectIdeaId
					+ ", which is created by some one else");
		}
	}

}
