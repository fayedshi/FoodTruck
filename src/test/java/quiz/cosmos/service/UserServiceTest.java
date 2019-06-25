package quiz.cosmos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;
import quiz.cosmos.CosmosSpringBootApp;
import quiz.cosmos.model.User;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = CosmosSpringBootApp.class)
public class UserServiceTest extends TestCase {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired 
	UserService userService;


	@Test
	public void testRegisterUser() throws Exception {
		User u= userService.saveUser(new User("Fred","158-9999"));
		assertEquals("Fred", userService.findOne(u.getId()).getName());
	}
}
