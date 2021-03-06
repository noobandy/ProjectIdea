/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.application.UserGroupManagementService;
import in.anandm.projectidea.application.util.PaginationUtility;
import in.anandm.projectidea.domain.model.authority.Authority;
import in.anandm.projectidea.domain.model.authority.AuthorityConstants;
import in.anandm.projectidea.domain.model.user.User;
import in.anandm.projectidea.domain.model.user.UserQuery;
import in.anandm.projectidea.domain.model.user.UserRepository;
import in.anandm.projectidea.domain.shared.QueryResult;
import in.anandm.projectidea.interfaces.rest.dto.UpdatePasswordCommand;
import in.anandm.projectidea.interfaces.rest.helper.RestResourceHelper;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api")
public class UserController {

	@Autowired
	private RestResourceHelper resourceHelper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserGroupManagementService userGroupManagementService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<QueryResult<User>> getUsers(
			@RequestParam(value = "page", required = true) int pageNumber,
			@RequestParam(value = "recordsPerPage", required = true) int pageSize,
			@RequestParam(value = "searchText", required = false) String searchText) {

		UserQuery query = new UserQuery();
		query.search(searchText);
		query.start(PaginationUtility.start(pageNumber, pageSize));
		query.maxResult(pageSize);
		QueryResult<User> result = userRepository.query(query);

		return new ResponseEntity<QueryResult<User>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<User> getUser(
			@PathVariable(value = "username") String username) {

		User user = userRepository.findUserByUserName(username);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<User> updateUser(
			@PathVariable(value = "username") String username) {

		User user = userRepository.findUserByUserName(username);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/users/{username}/authorities", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<Set<Authority>> getUserAuthorities(
			@PathVariable(value = "username") String username) {
		User user = userRepository.findUserByUserName(username);
		if (user != null) {
			return new ResponseEntity<Set<Authority>>(user.getAuthorities(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<Set<Authority>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/users/{username}/authorities/{authority}", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<String> grantUserAuthority(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "authority") String authority) {
		userGroupManagementService.grantAuthorityToUser(username, AuthorityConstants.valueOf(authority));
		return new ResponseEntity<String>("Granted", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users/{username}/authorities/{authority}", method = RequestMethod.DELETE)
	public @ResponseBody
	ResponseEntity<String> revokeUserAuthority(
			@PathVariable(value = "username") String username,
			@PathVariable(value = "authority") String authority) {
		userGroupManagementService.revokeAuthorityOfUser(username, AuthorityConstants.valueOf(authority));
		return new ResponseEntity<String>("Revoked", HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/users/{username}/updatePassword", method = RequestMethod.PUT)
	public @ResponseBody
	ResponseEntity<String> updatePassword(
			@PathVariable(value = "username") String username,
			@RequestBody UpdatePasswordCommand command) {

		User user = userRepository.findUserByUserName(username);
		if (user != null) {
			if (user.getPassword().equals(command.getOldPassword())) {
				if (command.getNewPassword().equals(
						command.getConfirmPassword())) {
					user.changePassword(command.getOldPassword(),
							command.getNewPassword());

					userRepository.saveUser(user);
				} else {
					return new ResponseEntity<String>("Old password is wrong",
							HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<String>("Password did'nt match",
						HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<String>("No such user found",
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Password updated", HttpStatus.OK);
	}
}
