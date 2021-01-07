package net.code.flightplan.entity;

import java.util.ArrayList;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "timeslot")
public class TimeSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "advisor_id")
	private int advisor_id;

	@Column(name = "timeslots")
	private String timeslots;
	
	@Column(name ="available")
	private int available;

	@Column(name = "auth_user_id")
	private int auth_user_id;

	

	public TimeSlot() {
		// TODO Auto-generated constructor stub
	}

	public TimeSlot(int id, int advisor_id, String timeslots, int available, int auth_user_id) {
		super();
		this.id = id;
		this.timeslots = timeslots;
		this.advisor_id = advisor_id;
		this.available = available;
		this.auth_user_id = auth_user_id;
	}

	public int getStudent_id() {
		return auth_user_id;
	}

	public void setStudent_id(int auth_user_id) {
		this.auth_user_id = auth_user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdvisor_id() {
		return advisor_id;
	}

	public void setAdvisor_id(int advisor_id) {
		this.advisor_id = advisor_id;
	}

	public String getTimeslots() {
		return timeslots;
	}

	public void setTimeslots(String timeslots) {
		this.timeslots = timeslots;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
}
