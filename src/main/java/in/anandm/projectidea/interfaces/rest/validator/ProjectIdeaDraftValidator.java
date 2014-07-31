/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.validator;

import in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaDraft;

import org.springframework.validation.Errors;

/**
 * @author anandm
 *
 */
public class ProjectIdeaDraftValidator extends BaseValidator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return ProjectIdeaDraft.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
	}

	
}
