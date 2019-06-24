package quiz.cosmos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import quiz.cosmos.model.Room;
import quiz.cosmos.repository.RoomRepository;

@Component
public class RoomService {

	@Autowired
	RoomRepository roomRepository;

	public List<Room> findAvailableRooms(Date fromDt, Date toDt, String city, int low, int high) {
		return roomRepository.findAvailableRooms(fromDt, toDt, city, low, high);
	}

	public void save(List<Room> rooms) {
		roomRepository.save(rooms);
	}
	
	public Room findOne(int id) {
		return roomRepository.findOne(id);
	}
}
