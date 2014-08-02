/**
 * 
 */
package in.anandm.projectidea.domain.model;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Anand
 * 
 */
@Entity
@Table(name = "act_user")
@JsonIgnoreProperties(value={
		"password"
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String completeName;
	private String emailId;
	private String username;
	private String password;
	private boolean enabled;
	private Date credentialExpiredAt;

	@Embedded
	private UserProfilePic profilePic;

	User() {
		super();

	}

	public User(String completeName, String emailId, String username,
			String password) {
		super();
		this.completeName = completeName;
		this.emailId = emailId;
		this.username = username;
		this.password = password;
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = true;
	}

	public void expireCredentialAt(Date expiryDate) {
		credentialExpiredAt = expiryDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean credentialsExpired() {
		return credentialExpiredAt != null
				&& credentialExpiredAt.before(new Date());
	}

	public boolean changePassword(String oldPassword, String newPassword) {
		if (password.equals(oldPassword)) {
			password = newPassword;
			return true;
		} else {
			return false;
		}
	}

	//getters
	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getCompleteName() {
		return completeName;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getUsername() {
		return username;
	}

	public Date getCredentialExpiredAt() {
		return credentialExpiredAt;
	}

	public UserProfilePic getProfilePic() {
		return profilePic;
	}

	
}
