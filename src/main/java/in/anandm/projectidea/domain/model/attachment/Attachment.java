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
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String path;
	private long sizeInBytes;
	private String type;

	@ManyToOne
	private ProjectIdea projectIdea;

	/**
	 * 
	 */
	Attachment() {
		super();

	}

	/**
	 * @param path
	 * @param sizeInBytes
	 * @param type
	 * @param projectIdea
	 */
	public Attachment(String path, long sizeInBytes, String type,
			ProjectIdea projectIdea) {
		super();
		this.path = path;
		this.sizeInBytes = sizeInBytes;
		this.type = type;
		this.projectIdea = projectIdea;
	}

	public Long getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public String getType() {
		return type;
	}

	public ProjectIdea getProjectIdea() {
		return projectIdea;
	}

}
