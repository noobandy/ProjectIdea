/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.resource;

import in.anandm.projectidea.domain.model.ProjectIdeaStatus;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author anandm
 * 
 */
public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// optional tag filter
	private String tag;

	private ProjectIdeaStatus status;

	private String author;

	// start of page
	private Integer page;

	// no. of records per page
	private Integer itemsPerPage;

	private Integer totlaItems;

	private ArrayList<T> data;

	/**
	 * @param tag
	 * @param page
	 * @param itemsPerPage
	 */
	public Page(String tag, Integer page, Integer itemsPerPage) {
		super();
		this.tag = tag;
		this.page = page;
		this.itemsPerPage = itemsPerPage;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public ProjectIdeaStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectIdeaStatus status) {
		this.status = status;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
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

	public ArrayList<T> getData() {
		return data;
	}

	public void setData(ArrayList<T> data) {
		this.data = data;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "PaginationData [tag=" + tag + ", status=" + status
				+ ", author=" + author + ", page=" + page + ", itemsPerPage="
				+ itemsPerPage + ", totlaItems=" + totlaItems + ", data="
				+ data + "]";
	}

}
