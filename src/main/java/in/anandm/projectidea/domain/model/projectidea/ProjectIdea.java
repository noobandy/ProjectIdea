/**
 * 
 */
package in.anandm.projectidea.domain.model.projectidea;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author anandm
 * 
 */
@Entity(name = "Idea")
public class ProjectIdea {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Specifications specifications;
	private Status status;

	/**
	 * 
	 */
	ProjectIdea() {
		super();

	}

	/**
	 * @param specifications
	 */
	public ProjectIdea(Specifications specifications) {
		super();
		this.specifications = specifications;
		this.status = Status.DRAFTED;
	}

	public void publish() {
		status = Status.PUBLISHED;
	}

	public void archive() {
		status = Status.ARCHIVED;
	}

	public Specifications getSpecifications() {
		return specifications;
	}

}
