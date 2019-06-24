package quiz.cosmos.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import quiz.cosmos.model.Reservation;

@Component
public interface ResvRepository extends JpaRepository<Reservation, Integer> {
	public List<Reservation> findByUserId(int userId);

	@Query(value = "select * from reservation where status='booked' and (:fromDt between resvFromdt and resvtodt " + 
			" or :toDt between resvFromdt and resvtodt ) and roomid=:roomId",nativeQuery=true)
	public Reservation findReservationByConditions(@Param("roomId") int roomId,@Param("fromDt") Date fromDt, @Param("toDt")Date toDt);
	
	@Modifying
	@Transactional
	@Query(value = "update Reservation set status='cancelled' where id=:id")
	public void cancelReservationById(@Param("id") int reservationId);

	
}
