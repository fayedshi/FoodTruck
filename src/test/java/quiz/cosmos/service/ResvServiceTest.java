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
import quiz.cosmos.model.Reservation;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = CosmosSpringBootApp.class)
public class ResvServiceTest extends TestCase {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired 
	ResvService resvService;
	@Autowired 
	UserService userService;
	@Autowired 
	RoomService roomService;

	@Test
	public void testReserve() throws ParseException {
		resvService.reserve(userService.findOne(1), roomService.findOne(1), sdf.parse("2019-04-23"), sdf.parse("2019-04-29"));
		assertEquals(1, resvService.findMyReservations(1).get(0).getRoom().getId());
	}
	
	// user tries to book a room reserved by others
	@Test
	public void testReserveFailure() throws ParseException {
		resvService.reserve(userService.findOne(1), roomService.findOne(1), sdf.parse("2019-04-23"), sdf.parse("2019-04-29"));
		Reservation rv= resvService.reserve(userService.findOne(2), roomService.findOne(1), sdf.parse("2019-04-25"), sdf.parse("2019-04-30"));
		assertEquals(rv,null);
	}
	
	// fail on second booking, succeed in third
	@Test
	public void testReserveInNewPeriod() throws ParseException {
		resvService.reserve(userService.findOne(2), roomService.findOne(1), sdf.parse("2019-04-23"), sdf.parse("2019-04-29"));
		resvService.reserve(userService.findOne(2), roomService.findOne(1), sdf.parse("2019-04-25"), sdf.parse("2019-04-30"));
		// try  reserving on different period
		resvService.reserve(userService.findOne(2), roomService.findOne(1), sdf.parse("2019-05-01"), sdf.parse("2019-05-05"));
		assertEquals(2,resvService.findMyReservations(2).size());
	}
	
	// cancelled reservation status shown as "cancelled"
	@Test
	public void testCancelReservation() throws ParseException {
		Reservation rv = resvService.reserve(userService.findOne(2), roomService.findOne(1), sdf.parse("2019-04-25"), sdf.parse("2019-04-30"));
		resvService.cancelReservationById(rv.getId());
		assertEquals("cancelled", resvService.findMyReservations(2).get(0).getStatus());
	}
}
