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

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CosmosSpringBootApp.class)
public class RoomServiceTest extends TestCase {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	ResvService rs;
	@Autowired
	UserService userService;
	@Autowired
	RoomService roomService;

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws ParseException
	 */

	@Test
	public void testFindAvailableRooms() throws Exception {
		rs.reserve(userService.findOne(1), roomService.findOne(2), sdf.parse("2019-06-15"), sdf.parse("2019-06-17"));
		// 2 'sh' rooms at startup,one booked
		//List<Room> rooms=roomService.findAvailableRooms(sdf.parse("2019-06-15"), sdf.parse("2019-06-17"), "sh", 100, 600);
		assertEquals(1, roomService.findAvailableRooms(sdf.parse("2019-06-15"), sdf.parse("2019-06-17"), "sh", 100, 600)
				.size());
	}
}
