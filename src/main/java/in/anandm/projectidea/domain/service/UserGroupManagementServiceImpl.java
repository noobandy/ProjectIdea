/**
 * 
 */
package in.anandm.projectidea.domain.service;

import in.anandm.projectidea.domain.model.authority.Authority;
import in.anandm.projectidea.domain.model.authority.AuthorityConstants;
import in.anandm.projectidea.domain.model.authority.AuthorityRepository;
import in.anandm.projectidea.domain.model.group.Group;
import in.anandm.projectidea.domain.model.group.GroupAuthority;
import in.anandm.projectidea.domain.model.group.GroupUser;
import in.anandm.projectidea.domain.model.group.IGroupAuthorityRepository;
import in.anandm.projectidea.domain.model.group.IGroupRepository;
import in.anandm.projectidea.domain.model.group.IGroupUserRepository;
import in.anandm.projectidea.domain.model.user.UserAuthorityRepository;
import in.anandm.projectidea.domain.model.user.UserRepository;
import in.anandm.projectidea.domain.model.user.User;
import in.anandm.projectidea.domain.model.user.UserAuthority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anand
 *
 */
@Service
public class UserGroupManagementServiceImpl implements
		IUserGroupManagementService {

	private UserRepository userRepository;
	private IGroupRepository groupRepository;
	private IGroupUserRepository groupUserRepository;
	private AuthorityRepository authorityRepository;
	private UserAuthorityRepository userAuthorityRepository;
	private IGroupAuthorityRepository groupAuthorityRepository;

	

	@Autowired
	public UserGroupManagementServiceImpl(UserRepository userRepository,
			IGroupRepository groupRepository,
			IGroupUserRepository groupUserRepository,
			AuthorityRepository authorityRepository,
			UserAuthorityRepository userAuthorityRepository,
			IGroupAuthorityRepository groupAuthorityRepository) {
		super();
		this.userRepository = userRepository;
		this.groupRepository = groupRepository;
		this.groupUserRepository = groupUserRepository;
		this.authorityRepository = authorityRepository;
		this.userAuthorityRepository = userAuthorityRepository;
		this.groupAuthorityRepository = groupAuthorityRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * addUserInGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public void addUserInGroup(String username, String groupName) {

		User user = userRepository.findUserByUserName(username);

		Group group = groupRepository.findGroupByName(groupName);

		GroupUser groupUser = new GroupUser(group.getId(), user.getId());

		groupUserRepository.saveGroupUser(groupUser);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * removeUserFormGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeUserFormGroup(String username, String groupName) {
		User user = userRepository.findUserByUserName(username);

		Group group = groupRepository.findGroupByName(groupName);

		groupUserRepository.removeGroupUser(group.getId(), user.getId());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * grantAuthorityToUser(java.lang.String,
	 * in.anandm.projectidea.domain.model.AuthorityConstants)
	 */
	@Override
	public void grantAuthorityToUser(String username,
			AuthorityConstants authorityName) {
		User user = userRepository.findUserByUserName(username);
		Authority authority = authorityRepository
				.findAuthorityByName(authorityName);

		UserAuthority userAuthority = new UserAuthority(user.getId(),
				authority.getId());

		userAuthorityRepository.saveUserAuthority(userAuthority);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * revokeAuthorityOfUser(java.lang.String,
	 * in.anandm.projectidea.domain.model.AuthorityConstants)
	 */
	@Override
	public void revokeAuthorityOfUser(String username,
			AuthorityConstants authorityName) {
		User user = userRepository.findUserByUserName(username);
		Authority authority = authorityRepository
				.findAuthorityByName(authorityName);

		userAuthorityRepository.removeUserAuthority(user.getId(),
				authority.getId());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * grantAuthorityToGroup(java.lang.String,
	 * in.anandm.projectidea.domain.model.AuthorityConstants)
	 */
	@Override
	public void grantAuthorityToGroup(String groupName,
			AuthorityConstants authorityName) {

		Group group = groupRepository.findGroupByName(groupName);
		Authority authority = authorityRepository
				.findAuthorityByName(authorityName);

		GroupAuthority groupAuthority = new GroupAuthority(group.getId(),
				authority.getId());

		groupAuthorityRepository.saveGroupAuthority(groupAuthority);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * revokeAuthorityOfGroup(java.lang.String,
	 * in.anandm.projectidea.domain.model.AuthorityConstants)
	 */
	@Override
	public void revokeAuthorityOfGroup(String groupName,
			AuthorityConstants authorityName) {

		Group group = groupRepository.findGroupByName(groupName);
		Authority authority = authorityRepository
				.findAuthorityByName(authorityName);

		groupAuthorityRepository.removeGroupAuthority(group.getId(),
				authority.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * getGrantedAuthoritiesOfUser(java.lang.String)
	 */
	@Override
	public List<AuthorityConstants> getGrantedAuthoritiesOfUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
