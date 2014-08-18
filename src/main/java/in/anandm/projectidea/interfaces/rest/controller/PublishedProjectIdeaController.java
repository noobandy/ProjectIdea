/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.application.util.PaginationUtility;
import in.anandm.projectidea.domain.model.attachment.Attachment;
import in.anandm.projectidea.domain.model.attachment.AttachmentRepository;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaQuery;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaRepository;
import in.anandm.projectidea.domain.model.projectidea.Status;
import in.anandm.projectidea.domain.model.review.Review;
import in.anandm.projectidea.domain.model.review.ReviewQuery;
import in.anandm.projectidea.domain.model.review.ReviewRepository;
import in.anandm.projectidea.domain.shared.QueryResult;
import in.anandm.projectidea.interfaces.rest.resource.TagCount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author anandm
 * 
 */
@Controller
@RequestMapping("/api")
public class PublishedProjectIdeaController {

	@Autowired
	private ProjectIdeaRepository projectIdeaRepository;

	@Autowired
	private AttachmentRepository attachmentRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	@RequestMapping(value = "/publishedProjectIdeas/tagCounts", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<TagCount>> getPublishedProjectIdeasTagCounts(
			@RequestParam(value = "author", required = false) String author) {

		return null;
	}

	@RequestMapping(value = "/publishedProjectIdeas", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<QueryResult<ProjectIdea>> getPublishedProjectIdeas(
			@RequestParam(value = "page", required = true) int pageNumber,
			@RequestParam(value = "recordsPerPage", required = true) int pageSize,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "sort", required = false) String sort) {

		ProjectIdeaQuery query = new ProjectIdeaQuery();
		query.status(Status.PUBLISHED);
		query.author(author);
		query.tag(tag);
		query.start(PaginationUtility.start(pageNumber, pageSize));
		query.maxResult(pageSize);

		QueryResult<ProjectIdea> result = projectIdeaRepository.query(query);

		return new ResponseEntity<QueryResult<ProjectIdea>>(result,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/publishedProjectIdeas/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<ProjectIdea> getPublishedProjectIdea(
			@PathVariable(value = "id") long projectIdeaId) {

		ProjectIdea projectIdea = projectIdeaRepository
				.findProjectIdeaById(projectIdeaId);
		if (projectIdea == null) {
			return new ResponseEntity<ProjectIdea>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ProjectIdea>(projectIdea, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/publishedProjectIdeas/{id}/attachments", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<Attachment>> getPublishedProjectIdeaAttachments(
			@PathVariable(value = "id") long projectIdeaId) {

		List<Attachment> attachments = attachmentRepository
				.findAttachmentsOfProjectIdea(projectIdeaId);
		return new ResponseEntity<List<Attachment>>(attachments, HttpStatus.OK);
	}

	@RequestMapping(value = "/publishedProjectIdeas/{id}/reviews", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<QueryResult<Review>> getPublishedProjectIdeaReviews(
			@PathVariable(value = "id") long projectIdeaId,
			@RequestParam(value = "page", required = true) int pageNumber,
			@RequestParam(value = "recordsPerPage", required = true) int pageSize) {

		ReviewQuery query = new ReviewQuery();
		query.projectIdea(projectIdeaId);
		query.start(PaginationUtility.start(pageNumber, pageSize));
		query.maxResult(pageSize);
		QueryResult<Review> result = reviewRepository.query(query);

		return new ResponseEntity<QueryResult<Review>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/publishedProjectIdeas/{id}/reviews", method = RequestMethod.POST)
	public void postPublishedProjectIdeaReviews(
			@PathVariable(value = "id") long projectIdeaId,
			@RequestBody ReviewCommand command) {

		
		
	}

	@RequestMapping(value = "/publishedProjectIdeas/{projectIdeaId}/reviews/{reviewId}", method = RequestMethod.GET)
	public void getPublishedProjectIdeaReview(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "reviewId") long reviewId) {

	}

	@RequestMapping(value = "/publishedProjectIdeas/{projectIdeaId}/reviews/{reviewId}", method = RequestMethod.PUT)
	public void updatePublishedProjectIdeaReview(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "reviewId") long reviewId) {

	}

	@RequestMapping(value = "/publishedProjectIdeas/{projectIdeaId}/reviews/{reviewId}", method = RequestMethod.DELETE)
	public void deletePublishedProjectIdeaReview(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "reviewId") long reviewId) {

	}

}
