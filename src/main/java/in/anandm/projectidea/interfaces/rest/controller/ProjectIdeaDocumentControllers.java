/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.ProjectIdeaDocument;
import in.anandm.projectidea.domain.repository.IProjectIdeaDocumentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author anandm
 * 
 */
@Controller
@RequestMapping("/projectIdea")
public class ProjectIdeaDocumentControllers {

	@Autowired
	private IProjectIdeaDocumentRepository projectIdeaDocumentRepository;

	@RequestMapping(value = "/{projectIdeaId}/documents", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<List<ProjectIdeaDocument>> getProjectIdeaDocuments(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId) {
		List<ProjectIdeaDocument> projectIdeaDocuments = projectIdeaDocumentRepository
				.findProjectIdeaDocumentsOfProjectIdea(projectIdeaId);

		return new ResponseEntity<List<ProjectIdeaDocument>>(
				projectIdeaDocuments, HttpStatus.OK);

	}

	@RequestMapping(value = "/{projectIdeaId}/documents", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<ProjectIdeaDocument> addProjectIdeaDocument(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			@RequestBody MultipartFile fileData) {

		ProjectIdeaDocument document = new ProjectIdeaDocument(projectIdeaId,
				fileData.getOriginalFilename(), "", fileData.getSize());
		projectIdeaDocumentRepository.saveProjectIdeaDocument(document);

		return new ResponseEntity<ProjectIdeaDocument>(document,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{projectIdeaId}/documents/{documentId}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<ProjectIdeaDocument> getProjectIdeaDocument(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			@PathVariable(value = "documentId") Long documentId) {
		ProjectIdeaDocument projectIdeaDocument = projectIdeaDocumentRepository
				.findProjectIdeaDocument(documentId);

		if (projectIdeaDocument != null) {
			return new ResponseEntity<ProjectIdeaDocument>(projectIdeaDocument,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<ProjectIdeaDocument>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{projectIdeaId}/documents/{documentId}", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<ProjectIdeaDocument> updateProjectIdeaDocument(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			@PathVariable(value = "documentId") Long documentId,
			@RequestBody ProjectIdeaDocument projectIdeaDocument) {

		projectIdeaDocumentRepository
				.saveProjectIdeaDocument(projectIdeaDocument);

		return new ResponseEntity<ProjectIdeaDocument>(projectIdeaDocument,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/{projectIdeaId}/documents/{documentId}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseEntity<Long> deleteProjectIdeaDocument(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			@PathVariable(value = "documentId") Long documentId) {

		projectIdeaDocumentRepository.removeProjectIdeaDocument(documentId);

		return new ResponseEntity<Long>(documentId, HttpStatus.OK);
	}
}
