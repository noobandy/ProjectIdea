/**
 * 
 */
package in.anandm.projectidea.domain.model.authority;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author anandm
 * 
 */
@Entity
@Table(name = "act_authority")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	private AuthorityConstants authority;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredAt;

	/**
	 * 
	 */
	Authority() {
		super();

	}

	public Authority(AuthorityConstants authority) {
		super();
		this.authority = authority;
	}

	public boolean hasExpired() {
		return expiredAt != null && expiredAt.before(new Date());
	}

	public void expireNow() {
		expiredAt = new Date();
	}

	public void expireAt(Date expiryDate) {
		expiredAt = expiryDate;
	}

	public void expireNever() {
		expiredAt = null;
	}

	public Long getId() {
		return id;
	}

	public AuthorityConstants getAuthority() {
		return authority;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (authority != other.authority)
			return false;
		return true;
	}

}
