/**
 * 
 */
package in.anandm.projectidea.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Anand
 *
 */

@Entity
public class ProjectIdeaDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long projectIdeaId;

	private String documentTitle;
	private String documentPath;
	private Long sizeInBytes;

	ProjectIdeaDocument() {
		super();
	}

	public ProjectIdeaDocument(Long projectIdeaId, String documentTitle,
			String documentPath, Long sizeInBytes) {
		super();
		this.projectIdeaId = projectIdeaId;
		this.documentTitle = documentTitle;
		this.documentPath = documentPath;
		this.sizeInBytes = sizeInBytes;
	}

}
