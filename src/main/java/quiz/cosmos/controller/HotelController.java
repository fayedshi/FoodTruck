package quiz.cosmos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import quiz.cosmos.model.Reservation;
import quiz.cosmos.model.Room;
import quiz.cosmos.model.User;
import quiz.cosmos.service.ResvService;
import quiz.cosmos.service.RoomService;
import quiz.cosmos.service.UserService;

@RestController
public class HotelController implements InitializingBean {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	ResvService resvService;
	@Autowired
	RoomService roomService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/register/{name}/{phoneNo}")
	public User registerUser(@PathVariable("name") String name, @PathVariable("phoneNo") String phoneNo) {
		return userService.saveUser(new User(name, phoneNo));
	}

	@RequestMapping(value = "/rooms", method = RequestMethod.GET, produces = "application/json")
	public List<Room> listAvailableRooms(@RequestParam Map<String, String> paramsMap) throws ParseException {
		Date from = sdf.parse(paramsMap.get("fromDate"));
		Date to = sdf.parse(paramsMap.get("toDate"));
		return roomService.findAvailableRooms(from, to, paramsMap.get("city").toString(),
				Integer.parseInt(paramsMap.get("low")), Integer.parseInt(paramsMap.get("high")));
	}

	@RequestMapping(value = "/reserve/{userId}/{roomId}/{fromDate}/{toDate}")
	public Reservation bookRoom(@PathVariable("userId") int userId, @PathVariable("roomId") int roomId,
			@PathVariable("fromDate") String fromDt, @PathVariable("toDate") String toDt) throws ParseException {
		Date from = sdf.parse(fromDt);
		Date to = sdf.parse(toDt);
		return resvService.reserve(userService.findOne(userId), roomService.findOne(roomId), from, to);
	}

	@RequestMapping(value = "/reserve", method = RequestMethod.GET, produces = "application/json")
	public Reservation bookRoom(@RequestParam Map<String, String> paramsMap) throws ParseException {
		Date from = sdf.parse(paramsMap.get("fromDate"));
		Date to = sdf.parse(paramsMap.get("toDate"));
		return resvService.reserve(userService.findOne(Integer.parseInt(paramsMap.get("userId"))),
				roomService.findOne(Integer.parseInt(paramsMap.get("roomId"))), from, to);
	}

	@RequestMapping(value = "/myreservations/{userId}")
	public List<Reservation> listMyReservations(@PathVariable("userId") int userId) {
		return resvService.findMyReservations(userId);
	}

	@RequestMapping(value = "/cancelreservation/{resId}")
	public Reservation cancelReservation(@PathVariable("resId") int resId) {
		resvService.cancelReservationById(resId);
		return resvService.findById(resId);
	}

	@RequestMapping(value = "/hello/{name}")
	public String hello(@PathVariable("name") String name) {
		return "Hello SpringbootApp " + name;
	}

	@RequestMapping(value = "/showusers")
	public List<User> showUsers() {
		return userService.findAll();
	}

	// Mock base data
	@Override
	public void afterPropertiesSet() throws Exception {
		List<User> users = Arrays.asList(new User("Lee", "143-5643"), new User("John", "110-9643"),
				new User("Gu", "9903-0619"));
		userService.save(users);
		List<Room> rooms = Arrays.asList(new Room(1, "8302", "sh", 200), new Room(2, "8106", "sh", 400),
				new Room(3, "202", "SX", 230), new Room(2, "8604", "SZ", 250));
		roomService.save(rooms);
		System.out.println("***************here****************");
	}

}
