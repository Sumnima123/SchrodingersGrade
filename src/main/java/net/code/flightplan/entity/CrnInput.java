package net.code.flightplan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class CrnInput {

	private int crn;
	
	public CrnInput(int crn) {
		this.crn = crn;
	}
	
	public CrnInput() 
	{
	super();
	// TODO Auto-generated constructor stub
	}
	
	@Id
	public int getCrn() {
		return crn;
	}
	public void setCrn(int crn) {
		this.crn = crn;
	}
	
}