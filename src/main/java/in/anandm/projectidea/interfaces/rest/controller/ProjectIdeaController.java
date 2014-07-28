/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.ProjectIdea;
import in.anandm.projectidea.domain.model.ProjectIdeaStatus;
import in.anandm.projectidea.domain.repository.IProjectIdeaRepository;
import in.anandm.projectidea.interfaces.rest.helper.RestResourceHelper;
import in.anandm.projectidea.interfaces.rest.resource.Page;
import in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaSummary;
import in.anandm.projectidea.interfaces.rest.resource.TagCount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author anandm
 * 
 */

@Controller
@RequestMapping("/projectIdea")
public class ProjectIdeaController {

	@Autowired
	private RestResourceHelper restResourceHelper;

	@Autowired
	private IProjectIdeaRepository projectIdeaRepository;

	@RequestMapping(value = "/publishedProjectIdeas", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Page<ProjectIdeaSummary>> getPublishedProjectIdeas(
			@RequestParam(value = "tag") String tag,
			@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage) {

		Page<ProjectIdeaSummary> page = new Page<ProjectIdeaSummary>(tag,
				pageNumber, itemsPerPage);

		page.setStatus(ProjectIdeaStatus.PUBLISHED);

		return new ResponseEntity<Page<ProjectIdeaSummary>>(
				restResourceHelper.getPage(page), HttpStatus.OK);
	}

	@RequestMapping(value = "/draftedProjectIdeasOfUser", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Page<ProjectIdeaSummary>> getDraftedProjectIdeasOfUser(
			@RequestParam(value = "tag") String tag,
			@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage) {

		Page<ProjectIdeaSummary> page = new Page<ProjectIdeaSummary>(tag,
				pageNumber, itemsPerPage);

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		page.setAuthor(userDetails.getUsername());

		page.setStatus(ProjectIdeaStatus.DRAFTED);

		return new ResponseEntity<Page<ProjectIdeaSummary>>(
				restResourceHelper.getPage(page), HttpStatus.OK);
	}

	@RequestMapping(value = "/publishedProjectIdeasOfUser", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Page<ProjectIdeaSummary>> getPublishedProjectIdeasOfUser(
			@RequestParam(value = "tag") String tag,
			@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage) {
		Page<ProjectIdeaSummary> page = new Page<ProjectIdeaSummary>(tag,
				pageNumber, itemsPerPage);

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		page.setAuthor(userDetails.getUsername());

		page.setStatus(ProjectIdeaStatus.PUBLISHED);

		return new ResponseEntity<Page<ProjectIdeaSummary>>(
				restResourceHelper.getPage(page), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<ProjectIdea> getProjectIdea(
			@PathVariable(value = "id") Long id) {
		ProjectIdea foundProjectIdea = projectIdeaRepository
				.findProjectIdeaById(id);

		if (foundProjectIdea != null) {
			return new ResponseEntity<ProjectIdea>(foundProjectIdea,
					HttpStatus.OK);
		} else {
			return new ResponseEntity<ProjectIdea>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<ProjectIdea> updateProjectIdea(
			@PathVariable(value = "id") Long id,
			@RequestBody ProjectIdea projectIdea) {
		projectIdeaRepository.saveProjectIdea(projectIdea);
		return new ResponseEntity<ProjectIdea>(projectIdea, HttpStatus.OK);
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<ProjectIdea> draftNewProjectIdea(
			@RequestBody ProjectIdea projectIdea) {
		projectIdeaRepository.saveProjectIdea(projectIdea);
		return new ResponseEntity<ProjectIdea>(projectIdea, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/publishedProjectIdeaTagCount")
	public @ResponseBody
	ResponseEntity<List<TagCount>> getPublishedProjectIdeaTagcount() {
		return null;
	}

	@RequestMapping(value = "/draftedProjectIdeaTagCountOfUser")
	public @ResponseBody
	ResponseEntity<List<TagCount>> getDraftedProjectIdeaTagcountOfUser() {
		return null;
	}

	@RequestMapping(value = "/publishedProjectIdeaTagCountOfUser")
	public @ResponseBody
	ResponseEntity<List<TagCount>> getPublishedProjectIdeaTagcountOfUser() {
		return null;
	}

}
