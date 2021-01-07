package net.code.flightplan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class CourseInfo {
	// Variables

	private String subject;
	private String title;
	private int number;
	private float creditMax;
	private float creditMin;

	// Constructor


	// Subject
	@Id
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	// Title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// CreditMax
	public float getCreditMax() {
		return creditMax;
	}

	public void setCreditMax(float creditMax) {
		this.creditMax = creditMax;
	}
	
	// CreditMin
		public float getCreditMin() {
			return creditMin;
		}

		public void setCreditMin(float creditMin) {
			this.creditMin = creditMin;
		}


// Number
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
