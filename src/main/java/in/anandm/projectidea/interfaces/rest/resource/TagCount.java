/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.resource;

import java.io.Serializable;

/**
 * @author anandm
 * 
 */
public class TagCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// tag name
	private String tag;
	// count
	private Integer count;

	/**
	 * 
	 */
	public TagCount() {
		super();

	}

	/**
	 * @param tag
	 * @param count
	 */
	public TagCount(String tag, Integer count) {
		super();
		this.tag = tag;
		this.count = count;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "TagCount [tag=" + tag + ", count=" + count + "]";
	}

}
