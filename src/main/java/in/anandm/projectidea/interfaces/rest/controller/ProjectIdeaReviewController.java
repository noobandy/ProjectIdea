/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.ProjectIdeaReview;
import in.anandm.projectidea.domain.model.User;
import in.anandm.projectidea.domain.repository.IProjectIdeaReviewRepository;
import in.anandm.projectidea.domain.repository.IUserRepository;
import in.anandm.projectidea.interfaces.rest.helper.ProjectIdeaReviewHelper;
import in.anandm.projectidea.interfaces.rest.resource.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/projectIdea")
public class ProjectIdeaReviewController {

	@Autowired
	private ProjectIdeaReviewHelper projectIdeaReviewHelper;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IProjectIdeaReviewRepository projectIdeaReviewRepository;

	@RequestMapping(value = "/{projectIdeaId}/reviews", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Page<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview>> getProjectIdeaReviews(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage) {

		Page<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview> page = new Page<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview>(
				pageNumber, itemsPerPage);

		page = projectIdeaReviewHelper.getProjectIdeaReviewPage(page,
				projectIdeaId);

		return new ResponseEntity<Page<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview>>(
				page, HttpStatus.OK);

	}

	@RequestMapping(value = "/{projectIdeaId}/reviews", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview> addProjectIdeaReview(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			@RequestBody in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview addReviewCommand) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		User user = userRepository
				.findUserByUserName(userDetails.getUsername());

		ProjectIdeaReview review = new ProjectIdeaReview(
				addReviewCommand.getProjectIdeaId(),
				addReviewCommand.getStars(), addReviewCommand.getRemark(), user);

		projectIdeaReviewRepository.saveProjectIdeaReview(review);

		addReviewCommand.setId(review.getId());
		addReviewCommand.setAuthor(review.getUser().getUsername());

		return new ResponseEntity<in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaReview>(
				addReviewCommand, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{projectIdeaId}/reviews/{reviewId}", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<ProjectIdeaReview> updateProjectIdeaReview(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			@PathVariable(value = "reviewId") Long reviewId,
			@RequestBody ProjectIdeaReview projectIdeaReview) {

		projectIdeaReviewRepository.saveProjectIdeaReview(projectIdeaReview);

		return new ResponseEntity<ProjectIdeaReview>(projectIdeaReview,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/{projectIdeaId}/reviews/{reviewId}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseEntity<Long> deleteProjectIdeaReview(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			@PathVariable(value = "reviewId") Long reviewId) {

		projectIdeaReviewRepository.removeProjectIdeaReviewById(reviewId);

		return new ResponseEntity<Long>(reviewId, HttpStatus.OK);
	}
}