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

	//tag name
	private String tag;
	//count 
	private int count;
	/**
	 * 
	 */
	public TagCount() {
		super();
		
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "TagCount [tag=" + tag + ", count=" + count + "]";
	}

	
	
	
}
