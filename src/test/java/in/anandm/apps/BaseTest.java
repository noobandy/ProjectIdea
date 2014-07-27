/**
 * 
 */
package in.anandm.apps;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"classpath:infrastructure-config/root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {

	@BeforeClass
	public static void setUp(){
		System.setProperty("spring.profiles.active", "dev");
	}

}
