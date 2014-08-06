/**
 * 
 */
package in.anandm.projectidea.domain.service;

import in.anandm.projectidea.domain.model.ProjectIdeaDocument;
import in.anandm.projectidea.domain.repository.IProjectIdeaDocumentRepository;

import java.io.InputStream;

import javax.jcr.Repository;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author anandm
 * 
 */
public class ProjectIdeaDocumentService implements IProjectIdeaDocumentService {

	private IProjectIdeaDocumentRepository projectIdeaDocumentRepository;

	private Repository repository;

	/**
	 * @param projectIdeaDocumentRepository
	 * @param repository
	 */
	@Autowired
	public ProjectIdeaDocumentService(
			IProjectIdeaDocumentRepository projectIdeaDocumentRepository,
			Repository repository) {
		super();
		this.projectIdeaDocumentRepository = projectIdeaDocumentRepository;
		this.repository = repository;
	}

	@Override
	public ProjectIdeaDocument addDocument(long projectIdeaId, String title,
			long sizeInBytes, InputStream content) {

		
		//ProjectIdeaDocument projectIdeaDocument = new ProjectIdeaDocument(projectIdeaId, title, documentPath, sizeInBytes);
		return null;
	}

	@Override
	public void removeDocument(long id) {
		
	}

	@Override
	public InputStream getDocumentContent(long id) {

		return null;
	}

}
