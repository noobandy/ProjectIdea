/**
 * 
 */
package in.anandm.projectidea.domain.model.projectidea;

import java.io.Serializable;

/**
 * @author anandm
 * 
 *         Don't use getters and setters
 */
public class EstimatedTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int MILLIS_IN_SECOND = 1000;
	private static final int SECONDS_IN_MINUTE = 60;
	private static final int MINUTES_IN_HOUR = 60;
	private static final int HOURS_IN_DAY = 24;
	private static final int DAYS_IN_MONTH = 30;
	private static final int MONTHS_IN_YEAR = 12;

	private static final long MILLISECONDS_IN_YEAR = (long) MILLIS_IN_SECOND
			* SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY
			* DAYS_IN_MONTH * MONTHS_IN_YEAR;

	private static final long MILLISECONDS_IN_MONTH = (long) MILLIS_IN_SECOND
			* SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY
			* DAYS_IN_MONTH;

	private static final long MILLISECONDS_IN_DAY = (long) MILLIS_IN_SECOND
			* SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY;

	private Integer years;
	private Integer months;
	private Integer days;

	private Long timeInMilliseconds;
	private String timeInWords;

	/**
	 * @param years
	 * @param months
	 * @param days
	 */
	public EstimatedTime(Integer years, Integer months, Integer days) {
		super();
		this.years = years;
		this.months = months;
		this.days = days;
		timeInMilliseconds();
		timeInWords();
	}

	/**
	 * 
	 */
	public EstimatedTime() {
		super();
	}

	public Integer getYears() {
		long tempTime = timeInMilliseconds;
		return (int) (tempTime / MILLISECONDS_IN_YEAR);
	}

	public Integer getMonths() {
		long tempTime = timeInMilliseconds;
		tempTime = tempTime % MILLISECONDS_IN_YEAR;
		return (int) (tempTime / MILLISECONDS_IN_MONTH);
	}

	public Integer getDays() {
		long tempTime = timeInMilliseconds;

		tempTime = tempTime % MILLISECONDS_IN_YEAR;

		tempTime = tempTime % MILLISECONDS_IN_MONTH;

		return (int) (tempTime / MILLISECONDS_IN_DAY);
	}

	public Long timeInMilliseconds() {

		if (timeInMilliseconds == null) {
			synchronized (this) {
				if (timeInMilliseconds == null) {
					Long time = 0L;

					if (years != null) {
						time = time + years * MILLISECONDS_IN_YEAR;
					}

					if (months != null) {
						time = time + months * MILLISECONDS_IN_MONTH;
					}

					if (days != null) {
						time = time + days * MILLISECONDS_IN_DAY;
					}

					timeInMilliseconds = time;
				}

			}
		}

		return timeInMilliseconds;
	}

	public String timeInWords() {
		if (timeInWords == null) {
			synchronized (this) {
				if (timeInWords == null) {
					StringBuilder builder = new StringBuilder();

					if (years != null) {
						builder.append(years).append(' ').append("Year")
								.append(' ');
					}

					if (months != null) {
						builder.append(months).append(' ').append("Month")
								.append(' ');
					}

					if (days != null) {
						builder.append(days).append(' ').append("Day");
					}

					timeInWords = builder.toString();
				}

			}
		}

		return timeInWords;
	}

	public static EstimatedTime valueOf(Long timeInMilliseconds) {
		EstimatedTime estimatedTime = new EstimatedTime();

		estimatedTime.timeInMilliseconds = timeInMilliseconds;

		estimatedTime.years = (int) (timeInMilliseconds / MILLISECONDS_IN_YEAR);

		timeInMilliseconds = timeInMilliseconds % MILLISECONDS_IN_YEAR;

		estimatedTime.months = (int) (timeInMilliseconds / MILLISECONDS_IN_MONTH);

		timeInMilliseconds = timeInMilliseconds % MILLISECONDS_IN_MONTH;

		estimatedTime.days = (int) (timeInMilliseconds / MILLISECONDS_IN_DAY);

		timeInMilliseconds = timeInMilliseconds % MILLISECONDS_IN_DAY;

		assert timeInMilliseconds == 0 : "Wrong calculation";

		return estimatedTime;
	}
}
