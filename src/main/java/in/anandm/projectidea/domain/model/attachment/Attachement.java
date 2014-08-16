/**
 * 
 */
package in.anandm.projectidea.domain.model.attachment;

import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author anandm
 *
 */
@Entity
public class Attachement {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String path;
	private long sizeInBytes;
	private String type;
	
	@ManyToOne
	private ProjectIdea projectIdea;
	
	
	
	/**
	 * 
	 */
	Attachement() {
		super();
		
	}



	/**
	 * @param path
	 * @param sizeInBytes
	 * @param type
	 * @param projectIdea
	 */
	Attachement(String path, long sizeInBytes, String type,
			ProjectIdea projectIdea) {
		super();
		this.path = path;
		this.sizeInBytes = sizeInBytes;
		this.type = type;
		this.projectIdea = projectIdea;
	}
}
