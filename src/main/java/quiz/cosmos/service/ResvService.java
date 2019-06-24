package quiz.cosmos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import quiz.cosmos.constants.RoomStatus;
import quiz.cosmos.model.Reservation;
import quiz.cosmos.model.Room;
import quiz.cosmos.model.User;
import quiz.cosmos.repository.ResvRepository;

@Component
public class ResvService {

	@Autowired
	ResvRepository resvRepository;

	public List<Reservation> findMyReservations(int userId) {
		return resvRepository.findByUserId(userId);
	}

	public Reservation findById(int id) {
		return resvRepository.findOne(id);
	}

	public Reservation reserve(User user, Room room, Date fromDt, Date toDt) {
		Reservation res = resvRepository.findReservationByConditions(room.getId(), fromDt, toDt);
		if (res != null)
			return null;
		return resvRepository.save(new Reservation(user, room, fromDt, toDt, RoomStatus.booked));
	}

	public void cancelReservationById(int id) {
		resvRepository.cancelReservationById(id);
	}

}
