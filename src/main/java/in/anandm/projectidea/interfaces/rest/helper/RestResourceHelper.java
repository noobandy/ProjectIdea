/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.helper;

import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.projectidea.Status;
import in.anandm.projectidea.domain.model.user.User;
import in.anandm.projectidea.domain.model.user.UserRepository;
import in.anandm.projectidea.infrastructure.persistence.jpa.BaseRepository;
import in.anandm.projectidea.interfaces.rest.resource.TagCount;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anandm
 * 
 */
@Component
public class RestResourceHelper extends BaseRepository<ProjectIdea, Long> {

	@Autowired
	private UserRepository userRepository;

	public List<TagCount> getTagCount(Status status) {
		Query query = em().createNativeQuery(
				"SELECT " + "  t.tag AS tag," + "  COUNT(pidt.tags) AS count "
						+ "FROM" + "  tag t "
						+ "  LEFT JOIN project_idea_tags pidt "
						+ "    ON t.tag = pidt.tags "
						+ "  LEFT JOIN project_idea pid "
						+ "    ON pidt.project_idea = pid.id "
						+ "WHERE pid.status =:status " + "GROUP BY t.tag ",
				"TagCount");

		query.setParameter("status", status.toString());
		return mapResultSet(query.getResultList());
	}

	public List<TagCount> getTagCountOfUser(String author,
			Status status) {

		User user = userRepository.findUserByUserName(author);
		if (user != null) {
			Query query = em().createNativeQuery(
					"SELECT " + "  t.tag AS tag,"
							+ "  COUNT(pidt.tags) AS count " + "FROM"
							+ "  tag t "
							+ "  LEFT JOIN project_idea_tags pidt "
							+ "    ON t.tag = pidt.tags "
							+ "  LEFT JOIN project_idea pid "
							+ "    ON pidt.project_idea = pid.id "
							+ "WHERE pid.status =:status "
							+ "  AND pid.author =:authorId "
							+ "GROUP BY t.tag ", "TagCount");
			query.setParameter("status", status.toString());
			query.setParameter("authorId", user.getId());
			return mapResultSet(query.getResultList());
		} else {
			return Collections.EMPTY_LIST;
		}

	}

	private static List<TagCount> mapResultSet(List<Object> objects) {
		List<TagCount> tagCounts = new ArrayList<TagCount>();

		for (Object result : objects) {
			Object[] row = (Object[]) result;
			tagCounts.add(new TagCount((String) row[0], ((BigInteger) row[1])
					.intValue()));
		}
		return tagCounts;
	}
}
