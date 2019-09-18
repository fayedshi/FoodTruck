package quiz.cosmos.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import quiz.cosmos.model.Room;
import quiz.cosmos.model.User;
import quiz.cosmos.repository.ResvRepository;

@Component
public class InitService implements InitializingBean {

	@Autowired
	ResvRepository resvRepository;
	@Autowired
	RoomService roomService;
	@Autowired
	UserService userService;

	// Mock base data
	@Override
	public void afterPropertiesSet() {
		List<User> users = Arrays.asList(new User("Lee", "143-5643"), new User("John", "110-9643"),
				new User("Gu", "9903-0619"));
		userService.save(users);
		List<Room> rooms = Arrays.asList(new Room(1, "8302", "sh", 200), new Room(2, "8106", "sh", 400),
				new Room(3, "202", "SX", 230), new Room(2, "8604", "SZ", 250));
		roomService.save(rooms);
	}
}
