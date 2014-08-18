/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author anandm
 * 
 */
@Controller
@RequestMapping("/api")
public class DraftedProjectIdeaController {

	@RequestMapping(value = "/draftedProjectIdeas", method = RequestMethod.GET)
	public void getDraftedProjectIdeas(
			@RequestParam(value = "page", required = true) int pageNumber,
			@RequestParam(value = "recordsPerPage", required = true) int pageSize,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "author", required = false) String author) {

	}

	@RequestMapping(value = "/draftedProjectIdeas", method = RequestMethod.POST)
	public void addDraftedProjectIdeas() {

	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}", method = RequestMethod.GET)
	public void getDraftedProjectIdea(
			@PathVariable(value = "id") long projectIdeaId) {

	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}", method = RequestMethod.PUT)
	public void updateDraftedProjectIdea(
			@PathVariable(value = "id") long projectIdeaId) {

	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}", method = RequestMethod.DELETE)
	public void deleteDraftedProjectIdea(
			@PathVariable(value = "id") long projectIdeaId) {

	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}/attachments", method = RequestMethod.GET)
	public void getDraftedProjectIdeaAttachments(
			@PathVariable(value = "id") long projectIdeaId) {

	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}/attachments", method = RequestMethod.POST)
	public void addDraftedProjectIdeaAttachment(
			@PathVariable(value = "id") long projectIdeaId) {

	}

	@RequestMapping(value = "/draftedProjectIdeas/{projectIdeaId}/attachments/{attachmentId}", method = RequestMethod.GET)
	public void getDraftedProjectIdeaAttachment(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "attachmentId") long attachmentId) {

	}

	@RequestMapping(value = "/draftedProjectIdeas/{projectIdeaId}/attachments/{attachmentId}", method = RequestMethod.PUT)
	public void updateDraftedProjectIdeaAttachment(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "attachmentId") long attachmentId) {

	}

	@RequestMapping(value = "/draftedProjectIdeas/{projectIdeaId}/attachments/{attachmentId}", method = RequestMethod.DELETE)
	public void deleteDraftedProjectIdeaAttachment(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "attachmentId") long attachmentId) {

	}

}
