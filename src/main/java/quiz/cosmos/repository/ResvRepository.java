package quiz.cosmos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import quiz.cosmos.model.Reservation;

@Component
public interface ResvRepository extends JpaRepository<Reservation, Integer> {
	public List<Reservation> findByUserId(int userId);

	// reversed period against roomrepository 
	@Query(value = "from Reservation a join a.room b where a.status='booked' and :toDt >= a.resvFromDt and :fromDt <= a.resvToDt and b.id=:roomId")
	public List<Reservation> findReservationByConditions(@Param("roomId") int roomId,@Param("fromDt") Date fromDt, @Param("toDt")Date toDt);
	
	@Modifying
	@Query(value = "update Reservation set status='cancelled' where id=:id")
	public void cancelReservationById(@Param("id") int reservationId);

	
}
