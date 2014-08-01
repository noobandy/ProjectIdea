/**
 * 
 */
package in.anandm.apps;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

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

import org.apache.jackrabbit.core.TransientRepository;

/**
 * @author anandm
 * 
 */
public class RepositoryTest {

	/**
	 * @param args
	 * @throws RepositoryException
	 * @throws LoginException
	 * @throws IOException
	 */
	public static void main(String[] args) throws LoginException,
			RepositoryException, IOException {

		Repository repository = new TransientRepository();

		Credentials credentials = new SimpleCredentials("admin",
				"admin".toCharArray());

		Session session = repository.login(credentials);

		System.out.println("username : " + session.getUserID()
				+ " , Repository : "
				+ repository.getDescriptor(Repository.REP_NAME_DESC));

		// content

		Node root = session.getRootNode();

		// path is simple project idea(root for projectIdea
		// )/attachments(folder)/a.doc(file).

		Node projectIdeaNode = root.addNode("simple project idea");

		Node attachmentsFolderNode = projectIdeaNode.addNode("attachments",
				NodeType.NT_FOLDER);

		Node attachmentFileNode = attachmentsFolderNode.addNode("Test.txt",
				NodeType.NT_FILE);

		Node contentNode = attachmentFileNode.addNode(Node.JCR_CONTENT,
				NodeType.NT_RESOURCE);

		InputStream in = new FileInputStream(
				"C:\\Users\\anandm.MKCLINDIA\\Desktop\\Test.txt");

		Binary value = session.getValueFactory().createBinary(in);

		contentNode.setProperty(Property.JCR_DATA, value);
		contentNode.setProperty(Property.JCR_LAST_MODIFIED,
				Calendar.getInstance());

		session.save();

		Node createdAttachementNode = session.getNode("/simple project idea/attachments/Test.txt");
		
		Property contentProperty = createdAttachementNode.getNode(Node.JCR_CONTENT).getProperty(Property.JCR_DATA);

		Binary content = contentProperty.getBinary();

		InputStreamReader reader = new InputStreamReader(content.getStream());
		int c;
		while ((c = reader.read()) > 0) {
			System.out.print((char)c);
		}
		System.out.println();
		reader.close();

		session.logout();
	}
}
