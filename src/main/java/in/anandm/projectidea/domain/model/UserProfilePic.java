/**
 * 
 */
package in.anandm.projectidea.domain.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author anandm
 * 
 */
@Embeddable
public class UserProfilePic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String profilePicPath;

	private transient byte[] data;

	/**
	 * 
	 */
	UserProfilePic() {
		super();

	}

	/**
	 * @param profilePicPath
	 * @param data
	 */
	public UserProfilePic(String profilePicPath, byte[] data) {
		super();
		this.profilePicPath = profilePicPath;
		this.data = data;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public byte[] getData() {
		return data;
	}

}
