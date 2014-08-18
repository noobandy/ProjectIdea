/**
 * 
 */
package in.anandm.projectidea.application.util;

import org.dom4j.IllegalAddException;

/**
 * @author anandm
 * 
 */
public class ArgumentValidator {

	public static final void between(int start, int end, int value,
			String message) {
		if (value < start || value > end) {
			throw new IllegalArgumentException(message);
		}
	}

	public static final void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalAddException(message);
		}
	}
	
	
}
