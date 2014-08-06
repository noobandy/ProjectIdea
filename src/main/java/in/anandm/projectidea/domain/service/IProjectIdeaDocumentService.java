/**
 * 
 */
package in.anandm.projectidea.domain.service;

import in.anandm.projectidea.domain.model.ProjectIdeaDocument;

import java.io.InputStream;

/**
 * @author anandm
 * 
 */
public interface IProjectIdeaDocumentService {

	ProjectIdeaDocument addDocument(long projectIdeaId, String title,
			long sizeInBytes, InputStream content);

	void removeDocument(long id);

	InputStream getDocumentContent(long id);
	
}
