package in.anandm.projectidea.interfaces.rest.validator;

import java.util.List;

import in.anandm.projectidea.domain.model.review.Review;
import in.anandm.projectidea.domain.model.review.ReviewQuery;
import in.anandm.projectidea.domain.model.review.ReviewRepository;
import in.anandm.projectidea.interfaces.rest.controller.ReviewCommand;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class ReviewCommandValidator extends BaseValidator {

	private ReviewRepository reviewRepository;


	public ReviewCommandValidator(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {

		return ReviewCommand.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "starts", "ReviewCommand.starts.required");
		ValidationUtils.rejectIfEmpty(errors, "remarks", "ReviewCommand.remarks.required");
		ValidationUtils.rejectIfEmpty(errors, "author", "ReviewCommand.author.required");
		ValidationUtils.rejectIfEmpty(errors, "projectIdeaId", "ReviewCommand.projectIdeaId.required");

		if(!errors.hasFieldErrors()){
			ReviewCommand command = (ReviewCommand) target;
			ReviewQuery query = new ReviewQuery();
			query.author(command.getAuthor());
			query.projectIdea(command.getProjectIdeaId());


			List<Review> reviews = reviewRepository.list(query);

			if(!reviews.isEmpty()){
				assert reviews.size() == 1 : "more than one reviews by an author for a project idea";
				errors.reject("ReviewCommand.invalid");
			}
		}
	}


}
