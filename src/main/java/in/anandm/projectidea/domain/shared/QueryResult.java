/**
 * 
 */
package in.anandm.projectidea.domain.shared;

import java.util.List;

/**
 * @author anandm
 * 
 */
public class QueryResult<T> {

	private List<T> items;
	private long count;

	/**
	 * @param items
	 * @param count
	 */
	public QueryResult(List<T> items, long count) {
		super();
		this.items = items;
		this.count = count;
	}

	public List<T> getItems() {
		return items;
	}

	public long getCount() {
		return count;
	}
}
