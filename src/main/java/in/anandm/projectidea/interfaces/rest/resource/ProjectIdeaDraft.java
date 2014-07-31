/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anandm
 * 
 */
public class ProjectIdeaDraft extends ProjectIdeaSummary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> tags = new ArrayList<String>();

	/**
	 * 
	 */
	public ProjectIdeaDraft() {
		super();

	}

	/**
	 * @param id
	 * @param title
	 * @param description
	 */
	public ProjectIdeaDraft(Long id, String title, String description) {
		super(id, title, description);

	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
