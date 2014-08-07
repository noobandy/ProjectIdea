/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.Group;
import in.anandm.projectidea.domain.model.Page;
import in.anandm.projectidea.domain.repository.IGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author anandm
 *
 */
@Controller
@RequestMapping(value = "/group")
public class GroupController {

	@Autowired
	private IGroupRepository grupRepository;

	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public ResponseEntity<Page<Group>> getGroups(
			@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage) {
		Page<Group> page = grupRepository.page(pageNumber, itemsPerPage);
		return new ResponseEntity<Page<Group>>(page, HttpStatus.OK);
	}
}
