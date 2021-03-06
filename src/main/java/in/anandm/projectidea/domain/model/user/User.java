/**
 * 
 */
package in.anandm.projectidea.domain.model.user;

import in.anandm.projectidea.domain.model.authority.Authority;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Anand
 * 
 */
@Entity
@Table(name = "act_user")
@JsonIgnoreProperties(value = { "password" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String completeName;
	private String emailId;
	private String username;
	private String password;
	private boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)
	private Date credentialExpiredAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	@Embedded
	private UserProfilePic profilePic;

	@ManyToMany
	@JoinTable(name = "act_user_authority", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authorities = new HashSet<Authority>();

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

	public void grantAuthority(Authority authority) {
		authorities.add(authority);
	}

	public void revokeAuthority(Authority authority) {
		authorities.remove(authority);
	}

	// getters
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

	public Date getLastModified() {
		return lastModified;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}
}
