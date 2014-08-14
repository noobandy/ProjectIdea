/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import java.io.Serializable;

/**
 * @author anandm
 * 
 */
public class ChatMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private String author;
	private long sentAt;

	/**
	 * 
	 */
	public ChatMessage() {

		this("", "", System.currentTimeMillis());

	}

	/**
	 * @param message
	 * @param author
	 * @param sentAt
	 */
	public ChatMessage(String message, String author, long sentAt) {
		super();
		this.message = message;
		this.author = author;
		this.sentAt = sentAt;
	}

	public String getMessage() {
		return message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getSentAt() {
		return sentAt;
	}

	public void setSentAt(long sentAt) {
		this.sentAt = sentAt;
	}

}
