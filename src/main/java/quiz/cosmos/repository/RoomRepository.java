package quiz.cosmos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import quiz.cosmos.model.Room;

@Component
public interface RoomRepository extends JpaRepository<Room, Integer> {
	@Query(value = "select distinct a from Reservation b right join b.room a where (b.room is null or status='cancelled' or :fromDt > b.resvToDt " + 
			" or :toDt < b.resvFromDt) and a.city=:city and a.price between :lowprice and :highprice")
	public List<Room> findAvailableRooms(@Param("fromDt") Date fromDt, @Param("toDt") Date toDt,
			@Param("city") String city, @Param("lowprice") int low, @Param("highprice") int high);
}
