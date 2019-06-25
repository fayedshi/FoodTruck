package quiz.cosmos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import quiz.cosmos.constants.ResvStatus;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	@Column(name = "resv_from_dt")
	private Date resvFromDt;
	@Column(name = "resv_to_dt")
	private Date resvToDt;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ResvStatus status;

	public Reservation() {
	}

	public Reservation(User user, Room room, Date fromDt, Date toDt, ResvStatus status) {
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

	public ResvStatus getStatus() {
		return status;
	}

	public void setStatus(ResvStatus status) {
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
