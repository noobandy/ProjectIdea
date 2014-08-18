/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.resource;

import in.anandm.projectidea.domain.model.user.User;

import java.io.Serializable;
import java.util.List;

/**
 * @author anandm
 * 
 */
public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String completeName;
	private String username;
	private String profilePicPath;

	private List<TagCount> tagCounts;

	/**
	 * 
	 */
	public UserProfile() {
		super();

	}

	/**
	 * @param tagCounts
	 */
	public UserProfile(User user, List<TagCount> tagCounts) {
		super();
		this.completeName = user.getCompleteName();
		this.username = user.getUsername();
		this.profilePicPath = user.getProfilePic() != null ? user
				.getProfilePic().getProfilePicPath() : "";
		this.tagCounts = tagCounts;

	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	public List<TagCount> getTagCounts() {
		return tagCounts;
	}

	public void setTagCounts(List<TagCount> tagCounts) {
		this.tagCounts = tagCounts;
	}

	@Override
	public String toString() {
		return "UserProfile [completeName=" + completeName + ", username="
				+ username + ", profilePicPath=" + profilePicPath
				+ ", tagCounts=" + tagCounts + "]";
	}

}
