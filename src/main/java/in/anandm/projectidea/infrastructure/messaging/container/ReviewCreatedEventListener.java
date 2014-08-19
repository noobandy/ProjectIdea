package in.anandm.projectidea.infrastructure.messaging.container;

import in.anandm.projectidea.domain.model.events.ReviewCretedEvent;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaRepository;
import in.anandm.projectidea.domain.model.review.Review;
import in.anandm.projectidea.domain.model.review.ReviewRepository;
import in.anandm.projectidea.domain.model.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class ReviewCreatedEventListener implements
		ApplicationListener<ReviewCretedEvent> {

	private ReviewRepository reviewRepository;
	private ProjectIdeaRepository projectIdeaRepository;
	private UserRepository userRepository;
	
	
	@Autowired
	/**
	 * @param reviewRepository
	 * @param projectIdeaRepository
	 * @param userRepository
	 */
	public ReviewCreatedEventListener(ReviewRepository reviewRepository,
			ProjectIdeaRepository projectIdeaRepository,
			UserRepository userRepository) {
		super();
		this.reviewRepository = reviewRepository;
		this.projectIdeaRepository = projectIdeaRepository;
		this.userRepository = userRepository;
	}


	@Override
	public void onApplicationEvent(ReviewCretedEvent event) {
		Review review = event.getReview();
		
		ProjectIdea projectIdea = review.getProjectIdea();
		
	}

}
