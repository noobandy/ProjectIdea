/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author anandm
 *
 */
public class BaseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
	}

}
