/**
 * 
 */
package in.anandm.projectidea.application.impl;

import in.anandm.projectidea.application.UserGroupManagementService;
import in.anandm.projectidea.domain.model.authority.Authority;
import in.anandm.projectidea.domain.model.authority.AuthorityConstants;
import in.anandm.projectidea.domain.model.authority.AuthorityRepository;
import in.anandm.projectidea.domain.model.group.Group;
import in.anandm.projectidea.domain.model.group.GroupUser;
import in.anandm.projectidea.domain.model.group.IGroupRepository;
import in.anandm.projectidea.domain.model.group.IGroupUserRepository;
import in.anandm.projectidea.domain.model.user.User;
import in.anandm.projectidea.domain.model.user.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Anand
 * 
 */
@Service
public class UserGroupManagementServiceImpl implements
		UserGroupManagementService {

	private UserRepository userRepository;
	private IGroupRepository groupRepository;
	private IGroupUserRepository groupUserRepository;
	private AuthorityRepository authorityRepository;

	/**
	 * 
	 */
	UserGroupManagementServiceImpl() {
		super();

	}

	@Autowired
	public UserGroupManagementServiceImpl(UserRepository userRepository,
			IGroupRepository groupRepository,
			IGroupUserRepository groupUserRepository,
			AuthorityRepository authorityRepository) {
		super();
		this.userRepository = userRepository;
		this.groupRepository = groupRepository;
		this.groupUserRepository = groupUserRepository;
		this.authorityRepository = authorityRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * addUserInGroup(java.lang.String, java.lang.String)
	 */
	@Transactional
	@Override
	@PreAuthorize("hasRole('ADMIN')")
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
	@Transactional
	@Override
	@PreAuthorize("hasRole('ADMIN')")
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
	@Transactional
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void grantAuthorityToUser(String username,
			AuthorityConstants authorityName) {
		User user = userRepository.findUserByUserName(username);
		Authority authority = authorityRepository
				.findAuthorityByName(authorityName);

		user.grantAuthority(authority);
		userRepository.saveUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * revokeAuthorityOfUser(java.lang.String,
	 * in.anandm.projectidea.domain.model.AuthorityConstants)
	 */
	@Transactional
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void revokeAuthorityOfUser(String username,
			AuthorityConstants authorityName) {
		User user = userRepository.findUserByUserName(username);
		Authority authority = authorityRepository
				.findAuthorityByName(authorityName);

		user.revokeAuthority(authority);

		userRepository.saveUser(user);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * grantAuthorityToGroup(java.lang.String,
	 * in.anandm.projectidea.domain.model.AuthorityConstants)
	 */
	@Transactional
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void grantAuthorityToGroup(String groupName,
			AuthorityConstants authorityName) {

		Group group = groupRepository.findGroupByName(groupName);
		Authority authority = authorityRepository
				.findAuthorityByName(authorityName);

		group.grantAuthority(authority);

		groupRepository.saveGroup(group);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * revokeAuthorityOfGroup(java.lang.String,
	 * in.anandm.projectidea.domain.model.AuthorityConstants)
	 */
	@Transactional
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void revokeAuthorityOfGroup(String groupName,
			AuthorityConstants authorityName) {

		Group group = groupRepository.findGroupByName(groupName);
		Authority authority = authorityRepository
				.findAuthorityByName(authorityName);

		group.revokeAuthority(authority);

		groupRepository.saveGroup(group);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.anandm.projectidea.domain.service.UserGroupManagementService#
	 * getGrantedAuthoritiesOfUser(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public Set<AuthorityConstants> getGrantedAuthoritiesOfUser(String username) {

		Set<AuthorityConstants> grantedAuthorities = new HashSet<AuthorityConstants>();

		Set<AuthorityConstants> deniedAuthorities = new HashSet<AuthorityConstants>();

		User user = userRepository.findUserByUserName(username);

		// non expired authorities granted to user
		for (Authority authority : user.getAuthorities()) {
			if (!authority.hasExpired()) {
				grantedAuthorities.add(authority.getAuthority());
			}
		}

		List<GroupUser> groupUsers = groupUserRepository
				.findGroupUsersOfUser(user.getId());

		for (GroupUser groupUser : groupUsers) {
			Group group = groupRepository.findgroupById(groupUser.getGroupId());
			if (group.isDenyGroup()) {
				for (Authority authority : group.getAuthorities()) {
					// authorities denied to user via group
					deniedAuthorities.add(authority.getAuthority());
				}
			} else {
				for (Authority authority : group.getAuthorities()) {
					if (!authority.hasExpired()) {
						// non expired authorities granted to user via group
						grantedAuthorities.add(authority.getAuthority());
					}
				}
			}
		}

		for (AuthorityConstants constants : deniedAuthorities) {
			// denied takes precedence over granted
			grantedAuthorities.remove(constants);
		}

		return grantedAuthorities;
	}

}
