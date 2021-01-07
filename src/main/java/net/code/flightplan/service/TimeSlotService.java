package net.code.flightplan.service;
import net.code.flightplan.entity.*;

import net.code.flightplan.repo.*;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotService {
	@Autowired
	TimeSlotRepo repoc;

	public ArrayList<String> getTimeslotsByAdvisor(int advisor_id) {
		ArrayList<String> ts = repoc.getTimeslotsByAdvisor(advisor_id);
		return ts;
	}

	public void deleteByAdvisor_id(int advisor_id) {
		repoc.deleteByAdvisor_id(advisor_id);
	}
	
	public void deleteStudenttimeslots(int advisor_id) {
		repoc.deleteStudenttimeslots(advisor_id);
	}

	public void save(TimeSlot advisor) {
		repoc.save(advisor);
	}

	public void delete(int id) {
		repoc.deleteById(id);
	}

	public boolean existsByAdvisorId(int i) {
		if (repoc.existsByAdvisor_id(i) == 1) {
			return true;
		}
		return false;
	}

	public ArrayList<String> getTimeslotsforStudents(int advisor_id) {
		ArrayList<String> ts = repoc.getTimeslotsforStudents(advisor_id);
		return ts;
	}
	
	
	public ArrayList<String> getSelectedTimeslots(int advisor_id) {
		ArrayList<String> ts = repoc.getSelectedTimeslots(advisor_id);
		return ts;
	}
	
	public ArrayList<String> getNotSelectedTimeslots(int advisor_id) {
		ArrayList<String> ts = repoc.getNotSelectedTimeslots(advisor_id);
		return ts;
	}
	
	public void alterTS(String timeslots, int adv_id) {
		repoc.alterTS(timeslots, adv_id);
	}
	
	public void alterTS1(int adv_id, String ts1) {
		repoc.alterTS1(adv_id, ts1);
	}
	
	public void saveStudent(int cwid, int adv_id, String timeslots) {
		repoc.saveStudent(cwid, adv_id, timeslots);
	}

	public void deleteTimeslot(int adv_id, String ts) {
		repoc.deleteTimeslot(adv_id, ts);
		
	}
	
}
