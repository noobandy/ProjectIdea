/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.authority.Authority;
import in.anandm.projectidea.domain.model.authority.AuthorityRepository;
import in.anandm.projectidea.domain.service.IAuthorityService;

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
@RequestMapping(value = "/api")
public class AuthorityController {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private IAuthorityService authorityService;

	@RequestMapping(value = "/authorities", method = RequestMethod.GET)
	public ResponseEntity<List<Authority>> getAuthorities() {

		List<Authority> authorities = authorityRepository.findAllAuthority();
		return new ResponseEntity<List<Authority>>(authorities, HttpStatus.OK);
	}

	// bootstrap

	@RequestMapping(value = "/authorities/bootstrap", method = RequestMethod.POST)
	public ResponseEntity<Integer> bootstrapAuthorities() {
		Integer count = authorityService.initOrUpdate();
		return new ResponseEntity<Integer>(count, HttpStatus.CREATED);
	}
}
