package quiz.cosmos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import quiz.cosmos.constants.RoomStatus;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "roomid")
	private Room room;
	
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "resvfromdt")
	private Date resvFromDt;
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "resvtodt")
	private Date resvToDt;
	private RoomStatus status;

	public Reservation() {
	}

	public Reservation(User user, Room room, Date fromDt, Date toDt, RoomStatus status) {
		this.user=user;
		this.room=room;
		this.resvFromDt = fromDt;
		this.resvToDt = toDt;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getResvFromDt() {
		return resvFromDt;
	}

	public void setResvFromDt(Date resvFromDt) {
		this.resvFromDt = resvFromDt;
	}

	public Date getResvToDt() {
		return resvToDt;
	}

	public void setResvToDt(Date resvToDt) {
		this.resvToDt = resvToDt;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
