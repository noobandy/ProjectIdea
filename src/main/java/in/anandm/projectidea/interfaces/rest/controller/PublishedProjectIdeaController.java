/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.application.ProjectIdeaService;
import in.anandm.projectidea.application.util.PaginationUtility;
import in.anandm.projectidea.application.util.StringUtility;
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
import in.anandm.projectidea.interfaces.rest.helper.RestResourceHelper;
import in.anandm.projectidea.interfaces.rest.resource.TagCount;
import in.anandm.projectidea.interfaces.rest.validator.ReviewCommandValidator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

	@Autowired
	private ProjectIdeaService projectIdeaService;

	@Autowired
	private ReviewCommandValidator reviewCommandValidator;

	@Autowired
	private RestResourceHelper restResourceHelper;

	@RequestMapping(value = "/publishedProjectIdeas/tagCounts", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<TagCount>> getPublishedProjectIdeasTagCounts(
			@RequestParam(value = "author", required = false) String author) {

		List<TagCount> counts = new ArrayList<TagCount>();

		if (StringUtility.hasText(author)) {
			counts = restResourceHelper.getTagCountOfUser(author,
					Status.PUBLISHED);
		} else {
			counts = restResourceHelper.getTagCount(Status.PUBLISHED);
		}

		return new ResponseEntity<List<TagCount>>(counts, HttpStatus.OK);
	}

	@RequestMapping(value = "/publishedProjectIdeas", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<QueryResult<ProjectIdea>> getPublishedProjectIdeas(
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
	public @ResponseBody ResponseEntity<ProjectIdea> getPublishedProjectIdea(
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
	public @ResponseBody ResponseEntity<List<Attachment>> getPublishedProjectIdeaAttachments(
			@PathVariable(value = "id") long projectIdeaId) {

		List<Attachment> attachments = attachmentRepository
				.findAttachmentsOfProjectIdea(projectIdeaId);
		return new ResponseEntity<List<Attachment>>(attachments, HttpStatus.OK);
	}

	@RequestMapping(value = "/publishedProjectIdeas/{id}/reviews", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<QueryResult<Review>> getPublishedProjectIdeaReviews(
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

	@Secured(value = { "isAuthenticated()" })
	@RequestMapping(value = "/publishedProjectIdeas/{id}/reviews", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Review> postPublishedProjectIdeaReviews(
			@PathVariable(value = "id") long projectIdeaId,
			@RequestBody ReviewCommand command, BindingResult errors) {

		reviewCommandValidator.validate(command, errors);
		if (errors.hasErrors()) {
			return new ResponseEntity<Review>(HttpStatus.BAD_REQUEST);
		} else {
			Review review = projectIdeaService.addReview(command.getAuthor(),
					command.getProjectIdeaId(), command.getStarts(),
					command.getRemarks());
			return new ResponseEntity<Review>(review, HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/publishedProjectIdeas/{projectIdeaId}/reviews/{reviewId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Review> getPublishedProjectIdeaReview(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "reviewId") long reviewId) {

		Review review = reviewRepository.findReviewById(reviewId);
		if (review == null) {
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/publishedProjectIdeas/{projectIdeaId}/reviews/{reviewId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Review> deletePublishedProjectIdeaReview(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "reviewId") long reviewId) {

		Review review = reviewRepository.findReviewById(reviewId);
		if (review == null) {
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
		}
	}

}
