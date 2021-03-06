/**
 * 
 */
package in.anandm.projectidea.domain.model.projectidea;

import in.anandm.projectidea.domain.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;

/**
 * @author anandm
 * 
 */
@Embeddable
public class Specifications {

	private String title;
	private String description;
	private Long timeInMilliseconds;
	private String timeInWords;

	@ManyToMany
	private Set<Tag> tags = new HashSet<Tag>();

	/**
	 * 
	 */
	Specifications() {
		super();

	}

	/**
	 * @param title
	 * @param description
	 * @param timeInMilliseconds
	 * @param timeInWords
	 * @param author
	 * @param tags
	 */
	private Specifications(String title, String description,
			Long timeInMilliseconds, String timeInWords, Set<Tag> tags) {
		super();
		this.title = title;
		this.description = description;
		this.timeInMilliseconds = timeInMilliseconds;
		this.timeInWords = timeInWords;
		this.tags = tags;
	}

	public static Builder draft() {
		return new Builder();
	}

	public Builder update() {
		Tag[] tagArray = new Tag[this.tags.size()];
		EstimatedTime estimatedTime = EstimatedTime.valueOf(timeInMilliseconds);
		return new Builder().title(title).description(description)
				.tag(this.tags.toArray(tagArray))
				.years(estimatedTime.getYears())
				.months(estimatedTime.getMonths())
				.days(estimatedTime.getDays());
	}

	public static final class Builder {
		private String title;
		private String description;
		private Long timeInMilliseconds;
		private String timeInWords;

		private Set<Tag> tags = new HashSet<Tag>();

		private transient EstimatedTime estimatedTime = new EstimatedTime(0, 0,
				0);

		/**
		 * 
		 */
		public Builder() {
			super();

		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder years(int estimatedYears) {
			estimatedTime = new EstimatedTime(estimatedYears,
					estimatedTime.getMonths(), estimatedTime.getDays());
			return this;
		}

		public Builder months(int estimatedMonths) {
			estimatedTime = new EstimatedTime(estimatedTime.getYears(),
					estimatedMonths, estimatedTime.getDays());
			return this;
		}

		public Builder days(int estimatedDays) {
			estimatedTime = new EstimatedTime(estimatedTime.getYears(),
					estimatedTime.getMonths(), estimatedDays);
			return this;
		}

		public Builder tag(Tag[] tags) {
			for (Tag tag : tags) {
				this.tags.add(tag);
			}

			return this;
		}

		public Specifications build() {
			this.timeInMilliseconds = estimatedTime.timeInMilliseconds();
			this.timeInWords = estimatedTime.timeInWords();
			return new Specifications(title, description, timeInMilliseconds,
					timeInWords, tags);
		}
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Long getTimeInMilliseconds() {
		return timeInMilliseconds;
	}

	public String getTimeInWords() {
		return timeInWords;
	}

	public Set<Tag> getTags() {
		return tags;
	}
	
	
}
