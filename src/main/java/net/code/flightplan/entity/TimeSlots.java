package net.code.flightplan.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;

public class TimeSlots {
	
    private  ArrayList<String> timeslots1;
     
    public TimeSlots() {}
    
	@JsonCreator
    public TimeSlots(ArrayList<String> timeslots1){
    	 this.timeslots1 = timeslots1;
    }

	public ArrayList<String> getTimeslots1() {
		return timeslots1;
	}
     
	public void setTimeslots1(ArrayList<String> timeslots1) {
		this.timeslots1 = timeslots1;
	}   
}

