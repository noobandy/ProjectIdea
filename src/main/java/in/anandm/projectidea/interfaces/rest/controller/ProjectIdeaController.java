/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.ProjectIdea;
import in.anandm.projectidea.domain.model.ProjectIdeaStatus;
import in.anandm.projectidea.domain.model.Tag;
import in.anandm.projectidea.domain.model.User;
import in.anandm.projectidea.domain.repository.IProjectIdeaRepository;
import in.anandm.projectidea.domain.repository.IUserRepository;
import in.anandm.projectidea.interfaces.rest.helper.RestResourceHelper;
import in.anandm.projectidea.interfaces.rest.resource.EstimatedTime;
import in.anandm.projectidea.interfaces.rest.resource.Page;
import in.anandm.projectidea.interfaces.rest.resource.ProjectIdeaDraft;
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

	@Autowired
	private IUserRepository userRepository;

	@RequestMapping(value = "/publishedProjectIdeas", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Page<ProjectIdeaSummary>> getPublishedProjectIdeas(
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage) {

		Page<ProjectIdeaSummary> page = new Page<ProjectIdeaSummary>(
				pageNumber, itemsPerPage);

		page.setTag(tag);

		page.setStatus(ProjectIdeaStatus.PUBLISHED);

		return new ResponseEntity<Page<ProjectIdeaSummary>>(
				restResourceHelper.getPage(page), HttpStatus.OK);
	}

	@RequestMapping(value = "/draftedProjectIdeasOfUser", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Page<ProjectIdeaSummary>> getDraftedProjectIdeasOfUser(
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage) {

		Page<ProjectIdeaSummary> page = new Page<ProjectIdeaSummary>(
				pageNumber, itemsPerPage);

		page.setTag(tag);
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
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage) {
		Page<ProjectIdeaSummary> page = new Page<ProjectIdeaSummary>(
				pageNumber, itemsPerPage);

		page.setTag(tag);

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
	ResponseEntity<ProjectIdeaDraft> draftNewProjectIdea(
			@RequestBody ProjectIdeaDraft projectIdeaDraft) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String currentUser = userDetails.getUsername();
		User user = userRepository.findUserByUserName(currentUser);

		ProjectIdea projectIdea = ProjectIdea.draftNewIdea(user,
				projectIdeaDraft.getTitle());

		projectIdea.updateDescription(projectIdeaDraft.getDescription());

		for (String tag : projectIdeaDraft.getTags()) {
			projectIdea.addTag(Tag.valueOf(tag));
		}

		projectIdeaRepository.saveProjectIdea(projectIdea);
		//
		projectIdeaDraft.setId(projectIdea.getId());

		return new ResponseEntity<ProjectIdeaDraft>(projectIdeaDraft,
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}/updateEstimatedTime")
	public @ResponseBody
	ResponseEntity<EstimatedTime> updateTimeEstimate(
			@PathVariable(value = "id") Long id,
			@RequestBody EstimatedTime estimatedTime) {

		ProjectIdea projectIdea = projectIdeaRepository.findProjectIdeaById(id);
		
		projectIdea.updateEstimatedTime(estimatedTime);
		
		projectIdeaRepository.saveProjectIdea(projectIdea);
		
		return new ResponseEntity<EstimatedTime>(HttpStatus.OK);
	}

	@RequestMapping(value = "/publishedProjectIdeaTagCount")
	public @ResponseBody
	ResponseEntity<List<TagCount>> getPublishedProjectIdeaTagcount() {
		return new ResponseEntity<List<TagCount>>(
				restResourceHelper.getTagCount(ProjectIdeaStatus.PUBLISHED),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/draftedProjectIdeaTagCountOfUser")
	public @ResponseBody
	ResponseEntity<List<TagCount>> getDraftedProjectIdeaTagcountOfUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return new ResponseEntity<List<TagCount>>(
				restResourceHelper.getTagCountOfUser(userDetails.getUsername(),
						ProjectIdeaStatus.DRAFTED), HttpStatus.OK);
	}

	@RequestMapping(value = "/publishedProjectIdeaTagCountOfUser")
	public @ResponseBody
	ResponseEntity<List<TagCount>> getPublishedProjectIdeaTagcountOfUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return new ResponseEntity<List<TagCount>>(
				restResourceHelper.getTagCountOfUser(userDetails.getUsername(),
						ProjectIdeaStatus.PUBLISHED), HttpStatus.OK);
	}

}
