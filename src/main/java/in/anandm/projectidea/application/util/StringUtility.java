/**
 * 
 */
package in.anandm.projectidea.application.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author anandm
 * 
 */
public class StringUtility {

	public static final boolean hasText(String str) {
		return org.springframework.util.StringUtils.hasText(str);
	}

	public static final int findNthIndexOf(String str, String pattern,
			int occurence) {
		int index = -1;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		while (m.find()) {
			if (--occurence == 0) {
				index = m.start();
				break;
			}
		}

		return index;
	}

	public static String toLikeString(String searchText) {
		StringBuilder builder = new StringBuilder("%");
		if (hasText(searchText)) {
			builder.append(searchText.trim());
		}
		builder.append('%');
		return builder.toString();
	}
}
