package net.code.flightplan.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Section 
{
	@Id
	private int crn;
	private String title; 
	private String day; 
	private LocalDateTime start;
	private LocalDateTime end;
	private String instructor;
	public Section() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Section(int crn, String title, String day, LocalDateTime start, LocalDateTime end, String instructor) {
		super();
		this.crn = crn;
		this.title = title;
		this.day = day;
		this.start = start;
		this.end = end;
		this.instructor = instructor;
	}
	public int getCrn() {
		return crn;
	}
	public void setCrn(int crn) {
		this.crn = crn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	} 
	
	
}
