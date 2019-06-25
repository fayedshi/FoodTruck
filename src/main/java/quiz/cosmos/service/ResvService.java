package quiz.cosmos.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import quiz.cosmos.constants.ResvStatus;
import quiz.cosmos.model.Reservation;
import quiz.cosmos.model.Room;
import quiz.cosmos.model.User;
import quiz.cosmos.repository.ResvRepository;

@Component
public class ResvService implements InitializingBean {

	@Autowired
	ResvRepository resvRepository;
	@Autowired
	RoomService roomService;
	@Autowired
	UserService userService;

	public List<Reservation> findMyReservations(int userId) {
		return resvRepository.findByUserId(userId);
	}

	// by reservation id
	public Reservation findById(int id) {
		return resvRepository.findOne(id);
	}

	@Transactional
	public Reservation reserve(User user, Room room, Date fromDt, Date toDt) throws Exception {
		// check if the room to book was already booked by himself or others
		List<Reservation> resvs= resvRepository.findReservationByConditions(room.getId(), fromDt, toDt);
		try {
			// prevent duplicate reservation
			synchronized (this) {
				if (resvs != null && !resvs.isEmpty())
					throw new Exception("Sorry, but room is not available under current criteria.");
				return resvRepository.save(new Reservation(user, room, fromDt, toDt, ResvStatus.booked));
			}
			// catch exception for sql
		} catch (SQLException e) {
			throw e;
		}
	}

	@Transactional
	public void cancelReservationById(int id) {
		resvRepository.cancelReservationById(id);
	}

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
