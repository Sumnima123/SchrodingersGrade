package net.code.flightplan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Subject
{
    @Id
	public String subject;

	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subject(String subject) {
		super();
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	} 
}