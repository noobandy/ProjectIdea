/**
 * 
 */
package in.anandm.projectidea.domain.service;

import in.anandm.projectidea.domain.model.authority.AuthorityConstants;
import in.anandm.projectidea.domain.model.user.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
	

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		in.anandm.projectidea.domain.model.user.User foundUser = userRepository.findUserByUserName(username);

		if (foundUser == null) {
			throw new UsernameNotFoundException(username);
		}

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (AuthorityConstants grantedAuthority : getGrantedAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(grantedAuthority
					.toString()));
		}

		UserDetails userDetails = new User(username, foundUser.getPassword(),
				authorities);

		return userDetails;
	}

	
	private List<AuthorityConstants> getGrantedAuthorities() {

		return Arrays.asList(AuthorityConstants.values());
	}
}
