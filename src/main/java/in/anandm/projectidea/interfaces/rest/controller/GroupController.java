/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.group.Group;
import in.anandm.projectidea.domain.model.group.IGroupRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public ResponseEntity<List<Group>> getGroups() {
		List<Group> groups = grupRepository.findAllGroups();
		return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
	}
}
