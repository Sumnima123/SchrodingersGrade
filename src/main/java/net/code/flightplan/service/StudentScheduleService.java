package net.code.flightplan.service;

import net.code.flightplan.repo.*;
import net.code.flightplan.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sumni
 *
 */
@Service
public class StudentScheduleService 
{
	@Autowired
	StudentScheduleRepo repo;

	public List<StudentSchedule> getUserSchedule(String student_cwid) {
		List<StudentSchedule> courses = (List<StudentSchedule>) repo.getByUser(student_cwid);
		return courses;
	}
	
	public ArrayList<StudentSchedule> getArrayByUser(String student_cwid){
		ArrayList<StudentSchedule> schedule = (ArrayList<StudentSchedule>) repo.getArrayByUser(student_cwid);
		return schedule;
	};

	// Method to get the subject for the Section class
	public String getSubjectByCrn(int term) {
		String subject = repo.getSubjectByCrn(term);
		return subject;
	}

//	public String getConcatByCrn(int term) {
//		String con = (String) repo.getConcatByCrn(term);
//		return con;
//	}
	
	// Method to get the Title for the Section class. 
	public String getTitleBySubject(int term) {
		String title = (String) repo.getTitleBySubject(term);
		return title;
	}

	// Method to get the Start time for Section class
	public String getStartTimeByCrn(int term) {
		String start = (String) repo.getStartTimeByCrn(term);
		return start;
	}

	// Method to get the End time for the Section class
	public String getEndTimeByCrn(int term) {
		String end = (String) repo.getEndTimeByCrn(term);
		return end;
	}

	public String getDayByCrn(int term)
    {
        String day = (String) repo.getDayByCrn(term);
        return day;
    }
	
	// Method to get the Days of Week for the Section class
	public String getDowByCrn(int term) {
		String dow = (String) repo.getDowByCrn(term);
		return dow;
	}

	// Save and delete
	// When the Section object is ready, you can save it to the table through the
	// repo
	public void save(StudentSchedule section) 
	{
		repo.save(section);
	}

	// Uses JPA repo method to delete a section from the repository
	public void delete(StudentSchedule section) {
		repo.delete(section);
	}
	
	public ArrayList<String> getListofClasses(int cwid) {
		ArrayList<String> ts = repo.getListofClasses(cwid);
		return ts;
	}
	public ArrayList<String> getListofSemester(int cwid) {
		ArrayList<String> ts = repo.getListofSemester(cwid);
		return ts;
	}
	
	
	
	public String getNumberByCrn(int term)
    {
        String number = (String) repo.getNumberByCrn(term);
        return number;
    }
	
	@Transactional 
	public void deleteSectionById(int id, String cwid) {
		repo.deleteSectionById(id, cwid);
	}
	
	public String getSemesterByCrn(int term) 
    {
        String section = (String) repo.getSemesterByCrn(term);
        return section;
    }
	
	public String getSemester(int std_id, int crn) 
    {
        String section = (String) repo.getSemester(std_id, crn);
        return section;
    }
	

	
	
}