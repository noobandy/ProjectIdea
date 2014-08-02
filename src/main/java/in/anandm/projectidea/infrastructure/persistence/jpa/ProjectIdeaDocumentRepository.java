/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jpa;

import in.anandm.projectidea.domain.model.ProjectIdeaDocument;
import in.anandm.projectidea.domain.repository.IProjectIdeaDocumentRepository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

/**
 * @author anandm
 * 
 */
@Repository
public class ProjectIdeaDocumentRepository extends
		BaseRepository<ProjectIdeaDocument, Long> implements
		IProjectIdeaDocumentRepository {

	@Override
	@Transactional
	public void saveProjectIdeaDocument(ProjectIdeaDocument projectIdeaDocument) {
		save(projectIdeaDocument);
	}

	@Override
	@Transactional
	public void removeProjectIdeaDocument(Long id) {
		removeById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public ProjectIdeaDocument findProjectIdeaDocument(Long id) {

		return find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProjectIdeaDocument> findProjectIdeaDocumentsOfProjectIdea(
			Long projectIdeaId) {
		Search search = new Search(ProjectIdeaDocument.class);
		Filter projectIdeaFilter = Filter.equal("projectIdeaId", projectIdeaId);
		search.addFilterAnd(projectIdeaFilter);
		return search(search);
	}

}
