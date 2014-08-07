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
@Table(name = "act_group")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String groupName;

	private boolean denyGroup;

	Group() {
		super();
	}

	public Group(String groupName) {
		super();
		this.groupName = groupName;
	}

	public void denyGroup() {
		this.denyGroup = true;
	}

	public Long getId() {
		return id;
	}

	public String getGroupName() {
		return groupName;
	}

	public boolean isDenyGroup() {
		return denyGroup;
	}

	
}
