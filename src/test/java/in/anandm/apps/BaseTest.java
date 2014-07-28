/**
 * 
 */
package in.anandm.apps;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"classpath:infrastructure-config/root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {

	@Autowired
	private static EntityManagerFactory entityManagerFactory;

	@BeforeClass
	public static void setUp(){
		System.setProperty("spring.profiles.active", "dev");


	}

	public static void setTestData(){
		//create two groups adminGroup and userGroup

		//create two users admin and user 
		//add admin to adminGroup and user to userGroup

		//create 100 project ideas of user (user) 
		//first 30 tag java, spring, hibernate
		//next 30 java,spring,hibernate,angularJs
		//next 20 spring security,jpa,java
		//next 20 java

		// publish 20 of them
		//first 5 then wait 20 
		//next 5


		Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);

		session.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {


			}
		});


	}
}
