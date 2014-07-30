/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.resource;

import java.io.Serializable;

/**
 * @author anandm
 * 
 */
public class ProjectIdeaSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String description;

	
	/**
	 * 
	 */
	public ProjectIdeaSummary() {
		super();

	}

	
	/**
	 * @param id
	 * @param title
	 * @param description
	 */
	public ProjectIdeaSummary(Long id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProjectIdea [id=" + id + ", title=" + title + ", description="
				+ description + "]";
	}
}
