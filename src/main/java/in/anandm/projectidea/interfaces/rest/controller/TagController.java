/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.Tag;
import in.anandm.projectidea.domain.repository.ITagRepository;

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
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	private ITagRepository tagRepository;

	@RequestMapping(value = "/tags", method = RequestMethod.GET)
	public ResponseEntity<List<Tag>> getAllTags() {
		return new ResponseEntity<List<Tag>>(tagRepository.findAllTags(),
				HttpStatus.OK);
	}
	
}
