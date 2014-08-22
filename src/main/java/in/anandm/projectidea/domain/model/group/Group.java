/**
 * 
 */
package in.anandm.projectidea.domain.model.group;

import in.anandm.projectidea.domain.model.authority.Authority;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@ManyToMany
	@JoinTable(name = "act_group_authority", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authorities = new HashSet<Authority>();

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

	public void grantAuthority(Authority authority) {
		authorities.add(authority);
	}

	public void revokeAuthority(Authority authority) {
		authorities.remove(authority);
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

	public Set<Authority> getAuthorities() {
		return authorities;
	}

}
