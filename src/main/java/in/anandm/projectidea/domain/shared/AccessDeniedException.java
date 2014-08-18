/**
 * 
 */
package in.anandm.projectidea.domain.shared;

/**
 * @author anandm
 * 
 */
public class AccessDeniedException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AccessDeniedException() {
		super();

	}

	/**
	 * @param message
	 * @param cause
	 */
	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 */
	public AccessDeniedException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public AccessDeniedException(Throwable cause) {
		super(cause);

	}

}
