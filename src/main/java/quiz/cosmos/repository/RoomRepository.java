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

	@Query(value = "select distinct a.*  from room a left join reservation b on a.id=b.roomid where (b.roomid is null or status='cancelled' or :fromdt > b.resvtodt " + 
			" or :todt < b.resvFromdt) and a.city=:city and a.price between :lowprice and :highprice",nativeQuery=true)
	public List<Room> findAvailableRooms(@Param("fromdt") Date fromDt, @Param("todt") Date toDt,
			@Param("city") String city, @Param("lowprice") int low, @Param("highprice") int high);
}
