/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

/**
 * @author anandm
 *
 */
public class ReviewCommand {

	private Long id;
	private int starts;
	private String remarks;
	private String author;
	/**
	 * 
	 */
	public ReviewCommand() {
		super();
		
	}
	/**
	 * @param id
	 * @param starts
	 * @param remarks
	 * @param author
	 */
	public ReviewCommand(Long id, int starts, String remarks, String author) {
		super();
		this.id = id;
		this.starts = starts;
		this.remarks = remarks;
		this.author = author;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStarts() {
		return starts;
	}
	public void setStarts(int starts) {
		this.starts = starts;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
