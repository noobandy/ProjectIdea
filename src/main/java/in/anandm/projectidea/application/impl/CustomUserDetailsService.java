/**
 * 
 */
package in.anandm.projectidea.application.impl;

import in.anandm.projectidea.application.UserGroupManagementService;
import in.anandm.projectidea.domain.model.authority.AuthorityConstants;
import in.anandm.projectidea.domain.model.user.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author anandm
 * 
 */

public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	private UserGroupManagementService userGroupManagementService;

	/**
	 * @param userRepository
	 * @param userGroupManagementService
	 */
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository,
			UserGroupManagementService userGroupManagementService) {
		super();
		this.userRepository = userRepository;
		this.userGroupManagementService = userGroupManagementService;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		in.anandm.projectidea.domain.model.user.User foundUser = userRepository
				.findUserByUserName(username);

		if (foundUser == null) {
			throw new UsernameNotFoundException(username);
		}

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		Set<AuthorityConstants> grantedAuthorities = userGroupManagementService
				.getGrantedAuthoritiesOfUser(username);

		for (AuthorityConstants grantedAuthority : grantedAuthorities) {
			authorities.add(new SimpleGrantedAuthority(grantedAuthority
					.toString()));
		}

		UserDetails userDetails = new User(username, foundUser.getPassword(),
				authorities);

		return userDetails;
	}

}
