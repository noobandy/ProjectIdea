/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.validator;

import in.anandm.projectidea.interfaces.rest.dto.ProjectIdeaCommand;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * @author Anand
 *
 */
public class ProjectIdeaCommandValidator extends BaseValidator {

	@Override
	public boolean supports(Class<?> clazz) {

		return ProjectIdeaCommand.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "title",
				"ProjectIdeaCommand.title.required");
		ValidationUtils.rejectIfEmpty(errors, "description",
				"ProjectIdeaCommand.description.required");

		ProjectIdeaCommand command = (ProjectIdeaCommand) target;

		int years = command.getYears() == null ? 0 : command.getYears();
		int months = command.getMonths() == null ? 0 : command.getMonths();
		int days = command.getDays() == null ? 0 : command.getDays();

		if (years <= 0 && months <= 0 && days <= 0) {
			errors.reject("ProjectIdeaCommand.estimatedTime.invalid");
		}

	}

}
