package net.code.flightplan.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "advisor")
public class Advisor {

	@Id
	@Column(name = "advisor_id")
	private int advisor_id;
	
	@Column(name = "zoom")
	private String zoom;
	
	@Column(name = "deadline")
	private String deadline;
	
	public Advisor() {}

	
	
	public Advisor(int advisor_id, String zoom, String deadline) {
		super();
		this.advisor_id = advisor_id;
		this.zoom = zoom;
		this.deadline = deadline;
	}



	


	public int getAdvisor_id() {
		return advisor_id;
	}

	public void setAdvisor_id(int advisor_id) {
		this.advisor_id = advisor_id;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	
}