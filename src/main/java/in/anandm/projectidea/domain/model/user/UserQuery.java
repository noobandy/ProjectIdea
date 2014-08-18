/**
 * 
 */
package in.anandm.projectidea.domain.model.user;


/**
 * @author anandm
 * 
 */
public class UserQuery {

	private Integer start;
	private Integer maxResults;
	private String searchText;

	/**
	 * 
	 */
	public UserQuery() {
		super();

	}

	public UserQuery start(int start) {
		this.start = start;

		return this;
	}

	public UserQuery maxResult(int maxResult) {
		this.maxResults = maxResult;

		return this;
	}

	public UserQuery search(String searchText) {
		this.searchText = searchText;

		return this;
	}

}
