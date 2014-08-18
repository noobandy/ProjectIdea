/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.dto;

import java.io.Serializable;

/**
 * @author anandm
 * 
 */
public class UpdatePasswordCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	/**
	 * 
	 */
	public UpdatePasswordCommand() {
		super();

	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
}
