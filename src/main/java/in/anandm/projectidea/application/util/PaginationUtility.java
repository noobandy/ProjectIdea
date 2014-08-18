/**
 * 
 */
package in.anandm.projectidea.application.util;

/**
 * @author anandm
 *
 */
public class PaginationUtility {

	public static final int start(int pageNumber,int recordsPerPage){
		return (pageNumber - 1) * recordsPerPage;
	}
}
