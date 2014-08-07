/**
 * 
 */
package in.anandm.projectidea.domain.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author anandm
 * 
 */
public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer pageNumber;

	// no. of records per page
	private Integer itemsPerPage;

	private Integer totlaItems;

	private List<T> data;

	
	public Page() {
		super();
	}

	public Page(Integer pageNumber, Integer itemsPerPage, Integer totlaItems,
			List<T> data) {
		super();
		this.pageNumber = pageNumber;
		this.itemsPerPage = itemsPerPage;
		this.totlaItems = totlaItems;
		this.data = data;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public Integer getTotlaItems() {
		return totlaItems;
	}

	public void setTotlaItems(Integer totlaItems) {
		this.totlaItems = totlaItems;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Page [pageNumber=" + pageNumber + ", itemsPerPage="
				+ itemsPerPage + ", totlaItems=" + totlaItems + ", data="
				+ data + "]";
	}

	
}
