/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.Authority;
import in.anandm.projectidea.domain.model.Page;
import in.anandm.projectidea.domain.repository.IAuthorityRepository;
import in.anandm.projectidea.domain.service.IAuthService;

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
@RequestMapping(value = "/authority")
public class AuthorityController {

	@Autowired
	private IAuthorityRepository authorityRepository;

	@Autowired
	private IAuthService authService;

	@RequestMapping(value = "/authorities", method = RequestMethod.GET)
	public ResponseEntity<Page<Authority>> getAuthorities(
			@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage) {
		Page<Authority> page = authorityRepository.page(pageNumber,
				itemsPerPage);
		return new ResponseEntity<Page<Authority>>(page, HttpStatus.OK);
	}

	// bootstrap

	@RequestMapping(value = "/bootstrap", method = RequestMethod.POST)
	public ResponseEntity<Integer> bootstrapAuthorities() {
		Integer count = authService.initOrUpdate();
		return new ResponseEntity<Integer>(count, HttpStatus.CREATED);
	}
}
