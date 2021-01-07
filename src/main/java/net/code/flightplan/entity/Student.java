package net.code.flightplan.entity;

import java.util.ArrayList;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "student")
public class Student {

	@Id
	@Column(name = "auth_user_id")
	private int auth_user_id;
	
	@Column(name = "advisor_id")
	private int advisor_id;
	
	@Column(name = "timeslot")
	private String timeslot;
	
	@Column(name = "agreed")
	private int agreed;
	
	




	public Student() {}

	

	public Student(int auth_user_id, int advisor_id, String timeslot, int agreed) {
		super();
		this.auth_user_id = auth_user_id;
		this.advisor_id = advisor_id;
		this.timeslot = timeslot;
		this.agreed = agreed;
	
	}



	public int getStudent_id() {
		return auth_user_id;
	}

	public void setStudent_id(int auth_user_id) {
		this.auth_user_id = auth_user_id;
	}

	public int getAdvisor_id() {
		return advisor_id;
	}

	public void setAdvisor_id(int advisor_id) {
		this.advisor_id = advisor_id;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}


	public int getAgreed() {
		return agreed;
	}



	public void setAgreed(int agreed) {
		this.agreed = agreed;
	}

	
	
}
