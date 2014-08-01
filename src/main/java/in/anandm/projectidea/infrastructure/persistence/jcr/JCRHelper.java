/**
 * 
 */
package in.anandm.projectidea.infrastructure.persistence.jcr;

import in.anandm.projectidea.domain.shared.ApplicationException;

import java.io.InputStream;

import javax.jcr.Binary;
import javax.jcr.Credentials;
import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.nodetype.NodeType;

/**
 * @author anandm
 * 
 */
public class JCRHelper {

	private Repository repository;
	private Credentials credentials;

	/**
	 * @param repository
	 */
	public JCRHelper(Repository repository) {
		super();
		this.repository = repository;
		this.credentials = new SimpleCredentials("admin", "admin".toCharArray());
	}

	public void saveProfilePic(String username, InputStream inputStream) {
		Session session = null;
		try {
			session = repository.login(credentials);
			Node rootNode = session.getRootNode();
			Node userHomeFolder = null;
			if (rootNode.hasNode(username)) {
				userHomeFolder = rootNode.getNode(username);
			} else {
				userHomeFolder = rootNode.addNode(username, NodeType.NT_FOLDER);
			}

			Node userProfilePicFile = null;
			if (userHomeFolder.hasNode(username)) {
				userProfilePicFile = userHomeFolder.getNode(username);
			} else {
				userProfilePicFile = userHomeFolder.addNode(username,
						NodeType.NT_FILE);
			}

			Node userProfilePicData = null;

			if (userProfilePicFile.hasNode(username + ".jpg")) {
				userProfilePicData = userProfilePicFile.getNode(username
						+ ".jpg");
			} else {
				userProfilePicData = userProfilePicFile.addNode(username
						+ ".jpg", NodeType.NT_RESOURCE);
			}

			Binary value = session.getValueFactory().createBinary(inputStream);

			userProfilePicData.setProperty(Property.JCR_DATA, value);

			session.save();

		} catch (LoginException e) {

			new ApplicationException(
					"Failed to authenticate with content repository", e);
		} catch (RepositoryException e) {

			new ApplicationException("failed to add file", e);
		} finally {
			if (session != null && session.isLive()) {
				session.logout();
			}
		}
	}

	public void saveProjectIdeaDocument(String projectIdeaTitle,
			String fileName, InputStream inputStream) {

	}

	private boolean nodeExists(String absPath) {
		return false;
	}

}
