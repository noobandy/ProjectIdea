/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.application.ProjectIdeaService;
import in.anandm.projectidea.application.util.PaginationUtility;
import in.anandm.projectidea.application.util.SecurityUtility;
import in.anandm.projectidea.domain.model.attachment.Attachment;
import in.anandm.projectidea.domain.model.attachment.AttachmentRepository;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdea;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaQuery;
import in.anandm.projectidea.domain.model.projectidea.ProjectIdeaRepository;
import in.anandm.projectidea.domain.model.projectidea.Specifications;
import in.anandm.projectidea.domain.model.projectidea.Status;
import in.anandm.projectidea.domain.model.tag.Tag;
import in.anandm.projectidea.domain.model.tag.TagRepository;
import in.anandm.projectidea.domain.shared.QueryResult;
import in.anandm.projectidea.interfaces.rest.dto.ProjectIdeaCommand;
import in.anandm.projectidea.interfaces.rest.dto.TagCount;
import in.anandm.projectidea.interfaces.rest.helper.RestResourceHelper;
import in.anandm.projectidea.interfaces.rest.validator.ProjectIdeaCommandValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

/**
 * @author anandm
 * 
 */
@Controller
@RequestMapping("/api")
public class DraftedProjectIdeaController {

	@Autowired
	private ProjectIdeaRepository projectIdeaRepository;

	@Autowired
	private ProjectIdeaCommandValidator projectIdeaCommandValidator;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private ProjectIdeaService projectIdeaService;

	@Autowired
	private AttachmentRepository attachmentRepository;

	@Autowired
	private RestResourceHelper restResourceHelper;

	@RequestMapping(value = "/draftedProjectIdeas/tagCounts", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<TagCount>> getDraftedProjectIdeasTagCounts(
			@RequestParam(value = "author", required = true) String author) {

		List<TagCount> counts = restResourceHelper.getTagCountOfUser(author,
				Status.DRAFTED);

		return new ResponseEntity<List<TagCount>>(counts, HttpStatus.OK);
	}

	@RequestMapping(value = "/draftedProjectIdeas", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<QueryResult<ProjectIdea>> getDraftedProjectIdeas(
			@RequestParam(value = "page", required = true) int pageNumber,
			@RequestParam(value = "recordsPerPage", required = true) int pageSize,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "author", required = false) String author) {

		ProjectIdeaQuery query = new ProjectIdeaQuery();
		query.author(author);
		query.tag(tag);
		query.status(Status.DRAFTED);
		query.start(PaginationUtility.start(pageNumber, pageSize));
		query.maxResult(pageSize);

		QueryResult<ProjectIdea> result = projectIdeaRepository.query(query);

		return new ResponseEntity<QueryResult<ProjectIdea>>(result,
				HttpStatus.OK);

	}

	@RequestMapping(value = "/draftedProjectIdeas", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ProjectIdea> addDraftedProjectIdeas(
			@RequestBody ProjectIdeaCommand command, BindingResult errors) {

		projectIdeaCommandValidator.validate(command, errors);

		if (errors.hasErrors()) {
			return new ResponseEntity<ProjectIdea>(HttpStatus.BAD_REQUEST);
		} else {
			Set<Tag> tags = command.getTags();
			Tag[] tagArray = new Tag[tags.size()];
			tagArray = tags.toArray(tagArray);
			Specifications specifications = Specifications.draft()
					.title(command.getTitle())
					.description(command.getDescription())
					.years(command.getYears()).months(command.getMonths())
					.days(command.getDays()).tag(tagArray).build();

			String author = command.getAuthor();

			ProjectIdea projectIdea = projectIdeaService.draftProjectidea(
					author, specifications);

			return new ResponseEntity<ProjectIdea>(projectIdea,
					HttpStatus.CREATED);
		}

	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ProjectIdea> getDraftedProjectIdea(
			@PathVariable(value = "id") long projectIdeaId) {

		ProjectIdea projectIdea = projectIdeaRepository
				.findProjectIdeaById(projectIdeaId);
		if (projectIdea == null) {
			return new ResponseEntity<ProjectIdea>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ProjectIdea>(projectIdea, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}/publish", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ProjectIdea> publishDraftedProjectIdea(
			@PathVariable(value = "id") long projectIdeaId) {

		ProjectIdea idea = projectIdeaRepository.findProjectIdeaById(projectIdeaId);
		if(idea == null){
			return new ResponseEntity<ProjectIdea>(HttpStatus.NOT_FOUND);
		}else{
			projectIdeaService.publishProjectIdea(SecurityUtility.authenticatedUser(), idea.getId());
			ProjectIdea projectIdea = projectIdeaRepository.findProjectIdeaById(projectIdeaId);
			return new ResponseEntity<ProjectIdea>(projectIdea, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ProjectIdea> updateDraftedProjectIdea(
			@PathVariable(value = "id") long projectIdeaId,
			@RequestBody ProjectIdeaCommand command, BindingResult errors) {
		projectIdeaCommandValidator.validate(command, errors);

		if (errors.hasErrors()) {
			return new ResponseEntity<ProjectIdea>(HttpStatus.BAD_REQUEST);
		} else {
			Set<Tag> tags = command.getTags();
			Tag[] tagArray = new Tag[tags.size()];
			tagArray = tags.toArray(tagArray);
			Specifications specifications = Specifications.draft()
					.title(command.getTitle())
					.description(command.getDescription())
					.years(command.getYears()).months(command.getMonths())
					.days(command.getDays()).tag(tagArray).build();

			String author = command.getAuthor();

			ProjectIdea projectIdea = projectIdeaService
					.updateDraftedProjectIdea(author, projectIdeaId,
							specifications);

			return new ResponseEntity<ProjectIdea>(projectIdea, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ProjectIdea> deleteDraftedProjectIdea(
			@PathVariable(value = "id") long projectIdeaId) {

		ProjectIdea projectIdea = projectIdeaRepository
				.findProjectIdeaById(projectIdeaId);

		if (projectIdea == null) {
			return new ResponseEntity<ProjectIdea>(HttpStatus.NOT_FOUND);
		} else {
			// delete here
			return new ResponseEntity<ProjectIdea>(HttpStatus.NO_CONTENT);
		}

	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}/attachments", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Attachment>> getDraftedProjectIdeaAttachments(
			@PathVariable(value = "id") long projectIdeaId) {

		List<Attachment> attachments = attachmentRepository
				.findAttachmentsOfProjectIdea(projectIdeaId);
		return new ResponseEntity<List<Attachment>>(attachments, HttpStatus.OK);
	}

	@RequestMapping(value = "/draftedProjectIdeas/{id}/attachments", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<Attachment>> addDraftedProjectIdeaAttachment(
			@PathVariable(value = "id") long projectIdeaId,
			MultipartRequest multipartRequest) {

		List<Attachment> attachments = new ArrayList<Attachment>();

		Map<String, MultipartFile> map = multipartRequest.getFileMap();
		for (Entry<String, MultipartFile> multiPartFileEntry : map.entrySet()) {
			MultipartFile file = multiPartFileEntry.getValue();
			Attachment attachment = projectIdeaService.addAttachment(SecurityUtility.authenticatedUser(),
					projectIdeaId, file);
			attachments.add(attachment);
		}

		return new ResponseEntity<List<Attachment>>(attachments,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/draftedProjectIdeas/{projectIdeaId}/attachments/{attachmentId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Attachment> getDraftedProjectIdeaAttachment(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "attachmentId") long attachmentId) {

		Attachment attachment = attachmentRepository
				.findAttachment(attachmentId);
		if (attachment == null) {
			return new ResponseEntity<Attachment>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Attachment>(attachment, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/draftedProjectIdeas/{projectIdeaId}/attachments/{attachmentId}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Attachment> deleteDraftedProjectIdeaAttachment(
			@PathVariable(value = "projectIdeaId") long projectIdeaId,
			@PathVariable(value = "attachmentId") long attachmentId) {
		Attachment attachment = attachmentRepository
				.findAttachment(attachmentId);
		if (attachment == null) {
			return new ResponseEntity<Attachment>(HttpStatus.NOT_FOUND);
		} else {
			attachmentRepository.removeAttachment(attachment.getId());
			// delete here
			return new ResponseEntity<Attachment>(HttpStatus.NO_CONTENT);
		}

	}

}
