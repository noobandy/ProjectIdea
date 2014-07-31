/**
 * 
 */
package in.anandm.projectidea.domain.model;

import java.io.Serializable;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

/**
 * @author Anand
 * 
 */
@Entity
@SqlResultSetMapping(name = "TagCount", columns = {
		@ColumnResult(name = "tag"), @ColumnResult(name = "count"), })
public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String tag;

	Tag() {
		super();
	}

	public static final Tag valueOf(String tag) {
		Tag tagObj = new Tag();
		tagObj.tag = tag;
		return tagObj;
	}

	public String getTag() {
		return tag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}
}
