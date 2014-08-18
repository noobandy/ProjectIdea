/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.resource;

import in.anandm.projectidea.domain.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Anand
 *
 */
public class ProjectIdeaCommand {

	private String author;
	private String title;
	private String description;
	private Integer years;
	private Integer months;
	private Integer days;

	private Set<Tag> tags = new HashSet<Tag>();

	private Set<String> otherTags = new HashSet<String>();

	public ProjectIdeaCommand() {
		super();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<String> getOtherTags() {
		return otherTags;
	}

	public void setOtherTags(Set<String> otherTags) {
		this.otherTags = otherTags;
	}

	@Override
	public String toString() {
		return "ProjectIdeaCommand [author=" + author + ", title=" + title
				+ ", description=" + description + ", years=" + years
				+ ", months=" + months + ", days=" + days + ", tags=" + tags
				+ ", otherTags=" + otherTags + "]";
	}

}
