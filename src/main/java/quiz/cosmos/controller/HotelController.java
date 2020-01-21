package quiz.cosmos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import quiz.cosmos.constants.ResponseStatus;
import quiz.cosmos.model.Reservation;
import quiz.cosmos.model.ResultBean;
import quiz.cosmos.model.Room;
import quiz.cosmos.model.User;
import quiz.cosmos.service.ResvService;
import quiz.cosmos.service.RoomService;
import quiz.cosmos.service.UserService;
import quiz.cosmos.util.Util;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	@Autowired
	ResvService resvService;
	@Autowired
	RoomService roomService;
	@Autowired
	UserService userService;

	@GetMapping("/register/{name}/{phoneNo}")
	public ResultBean<User> registerUser(@PathVariable("name") String name, @PathVariable("phoneNo") String phoneNo) {
		try {
			return Util.buildResultBean(userService.saveUser(new User(name, phoneNo)), null, ResponseStatus.success);
		} catch (Exception e) {
			logger.error("exception occured");
			return Util.buildResultBean(null, e.getMessage(), ResponseStatus.failed);
		}
	}

	@GetMapping(value = "/rooms", produces = "application/json")
	public ResultBean<List<Room>> listAvailableRooms(@RequestParam Map<String, String> paramsMap)
			throws ParseException {
		Date from = sdf.parse(paramsMap.get("fromDate"));
		Date to = sdf.parse(paramsMap.get("toDate"));
		List<Room> rooms = roomService.findAvailableRooms(from, to, paramsMap.get("city").toString(),
				Integer.parseInt(paramsMap.get("low")), Integer.parseInt(paramsMap.get("high")));
		return Util.buildResultBean(rooms, (rooms == null || rooms.isEmpty()) ? "No rooms found." : null,
				ResponseStatus.success);
	}

	@PostMapping("/reserve")
	public ResultBean<Reservation> bookRoom(@RequestParam Map<String, String> paramsMap) throws Exception {
		Date from = sdf.parse(paramsMap.get("fromDate"));
		Date to = sdf.parse(paramsMap.get("toDate"));
		try {
			Reservation resv = resvService.reserve(userService.findOne(Integer.parseInt(paramsMap.get("userId"))),
					roomService.findOne(Integer.parseInt(paramsMap.get("roomId"))), from, to);
			return Util.buildResultBean(resv, null, ResponseStatus.success);
		} catch (Exception e) {
			return Util.buildResultBean(null, e.getMessage(), ResponseStatus.failed);
		}
	}

	@GetMapping("/myreservations/{userId}")
	public ResultBean<List<Reservation>> listMyReservations(@PathVariable("userId") int userId) {
		return Util.buildResultBean(resvService.findMyReservations(userId), null, ResponseStatus.success);
	}

	@GetMapping("/cancelreservation/{resId}")
	public ResultBean<Reservation> cancelReservation(@PathVariable("resId") int resId) {
		resvService.cancelReservationById(resId);
		return Util.buildResultBean(resvService.findById(resId), null, ResponseStatus.success);
	}

	// assistant methods
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name) {
		return "Hello SpringbootApp " + name;
	}

	@RequestMapping("/showusers")
	public List<User> showUsers() {
		//logger.info("to show users");
		return userService.findAll();
	}
}
