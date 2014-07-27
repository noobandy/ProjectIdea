/**
 * 
 */
package in.anandm.projectidea.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author anandm
 *
 */
@Entity
@Table(name="act_user_authority")
public class UserAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long userId;
	private Long authorityId;

	UserAuthority() {
		super();
	}

	public UserAuthority(Long userId, Long authorityId) {
		super();
		this.userId = userId;
		this.authorityId = authorityId;
	}

}
