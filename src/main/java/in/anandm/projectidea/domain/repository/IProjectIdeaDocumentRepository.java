/**
 * 
 */
package in.anandm.projectidea.domain.repository;

import in.anandm.projectidea.domain.model.ProjectIdeaDocument;

import java.util.List;

/**
 * @author anandm
 * 
 */
public interface IProjectIdeaDocumentRepository {

	List<ProjectIdeaDocument> findProjectIdeaDocumentsOfProjectIdea(
			Long projectIdeaId);

	void saveProjectIdeaDocument(ProjectIdeaDocument projectIdeaDocument);

	void removeProjectIdeaDocument(Long id);

	ProjectIdeaDocument findProjectIdeaDocument(Long id);
}
