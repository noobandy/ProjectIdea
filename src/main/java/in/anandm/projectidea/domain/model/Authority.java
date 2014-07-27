/**
 * 
 */
package in.anandm.projectidea.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author anandm
 *
 */
@Entity
@Table(name="act_authority")
public class Authority{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private AuthorityConstants authority;
	
	private Long expiredAt;
	
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

	public Long getId() {
		return id;
	}


	

}
