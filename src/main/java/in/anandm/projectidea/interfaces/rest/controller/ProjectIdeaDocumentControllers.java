/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.ProjectIdeaDocument;
import in.anandm.projectidea.domain.repository.IProjectIdeaDocumentRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

	@RequestMapping(value = "/{projectIdeaId}/documents", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<List<ProjectIdeaDocument>> addProjectIdeaDocument(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			MultipartHttpServletRequest request) {

		Map<String, MultipartFile> fileMap = request.getFileMap();

		List<ProjectIdeaDocument> documents = new ArrayList<ProjectIdeaDocument>();

		for (Entry<String, MultipartFile> fileEntry : fileMap.entrySet()) {
			ProjectIdeaDocument document = new ProjectIdeaDocument(
					projectIdeaId, fileEntry.getValue().getOriginalFilename(),
					"", fileEntry.getValue().getSize());
			projectIdeaDocumentRepository.saveProjectIdeaDocument(document);
			documents.add(document);
		}

		return new ResponseEntity<List<ProjectIdeaDocument>>(documents,
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

	@RequestMapping(value = "/{projectIdeaId}/documents/{documentId}/content", method = RequestMethod.GET)
	public void getProjectIdeaDocumentContent(
			@PathVariable(value = "projectIdeaId") Long projectIdeaId,
			@PathVariable(value = "documentId") Long documentId,HttpServletResponse response) throws FileNotFoundException {
		ProjectIdeaDocument projectIdeaDocument = projectIdeaDocumentRepository
				.findProjectIdeaDocument(documentId);

		
		
		
		
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
