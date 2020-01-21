package quiz.cosmos.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import quiz.cosmos.constants.ResvStatus;
import quiz.cosmos.model.Reservation;
import quiz.cosmos.model.Room;
import quiz.cosmos.model.User;
import quiz.cosmos.repository.ResvRepository;

@Component
@Transactional
public class ResvService{

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
	
	// add logging with AOP
	public Reservation reserve(User user, Room room, Date fromDt, Date toDt) throws Exception {
		try {
			// prevent duplicate reservation
			synchronized (this) {
				// check if the room to book was already booked by himself or by others
				List<Reservation> resvs = resvRepository.findReservationByConditions(room.getId(), fromDt, toDt);
				if (resvs != null && !resvs.isEmpty())
					throw new Exception("Sorry, but room is not available under current criteria.");
				return resvRepository.save(new Reservation(user, room, fromDt, toDt, ResvStatus.booked));
			}
			// catch exception for sql
		} catch (SQLException e) {
			throw e;
		}
	}

	public void cancelReservationById(int id) {
		resvRepository.cancelReservationById(id);
	}

}
