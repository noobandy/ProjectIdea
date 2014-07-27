/**
 * 
 */
package in.anandm.projectidea.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author anandm
 *
 */
@Entity
@Table(name = "act_group_authority")
public class GroupAuthority {

	@Id
	@GeneratedValue
	private Long id;
	private Long groupId;
	private Long authorityId;

	GroupAuthority() {
		super();
	}

	public GroupAuthority(Long groupId, Long authorityId) {
		super();
		this.groupId = groupId;
		this.authorityId = authorityId;
	}


}
