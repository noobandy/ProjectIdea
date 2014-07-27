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
 * @author Anand
 *
 */
@Entity
@Table(name="act_group_user")
public class GroupUser {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long groupId;
	private Long userId;
	
	GroupUser() {
		super();
	}
	
	public GroupUser(Long groupId, Long userId) {
		super();
		this.groupId = groupId;
		this.userId = userId;
	}
}
