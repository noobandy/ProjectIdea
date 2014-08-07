/**
 * 
 */
package in.anandm.projectidea.interfaces.rest.controller;

import in.anandm.projectidea.domain.model.ProjectIdeaStatus;
import in.anandm.projectidea.domain.model.User;
import in.anandm.projectidea.domain.repository.IUserRepository;
import in.anandm.projectidea.interfaces.rest.helper.RestResourceHelper;
import in.anandm.projectidea.interfaces.rest.helper.UserHelper;
import in.anandm.projectidea.interfaces.rest.resource.Page;
import in.anandm.projectidea.interfaces.rest.resource.TagCount;
import in.anandm.projectidea.interfaces.rest.resource.UpdatePasswordCommand;
import in.anandm.projectidea.interfaces.rest.resource.UserProfile;

import java.util.List;

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
@RequestMapping("/user")
public class UserController {

	@Autowired
	private RestResourceHelper resourceHelper;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserHelper userHelper;


	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<Page<User>> getUsers(@RequestParam(value = "page") Integer pageNumber,
			@RequestParam(value = "itemsPerPage") Integer itemsPerPage){

		Page<User> page = new Page<User>(page, itemsPerPage);
		return null;
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseEntity<UserProfile> getUser(
			@PathVariable(value = "username") String username) {

		User user = userRepository.findUserByUserName(username);
		if (user != null) {
			List<TagCount> tagCounts = resourceHelper.getTagCountOfUser(
					username, ProjectIdeaStatus.PUBLISHED);

			UserProfile userProfile = new UserProfile(user, tagCounts);

			return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserProfile>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{username}/profilePic", method = RequestMethod.GET)
	public void getProfilePic() {

	}

	@RequestMapping(value = "/{username}/profilePic", method = RequestMethod.PUT)
	public void updateProfilePic() {

	}

	@RequestMapping(value = "/{username}/updatePassword", method = RequestMethod.PUT)
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
