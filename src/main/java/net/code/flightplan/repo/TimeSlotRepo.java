package net.code.flightplan.repo;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.code.flightplan.entity.*;

@Repository
public interface TimeSlotRepo extends JpaRepository<TimeSlot, Integer> {

	@Query(value = "SELECT timeslots FROM timeslot WHERE advisor_id = ?1", nativeQuery = true)
	public ArrayList<String> getTimeslotsByAdvisor(int advisor_id);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM timeslot WHERE advisor_id = ?1", nativeQuery = true)
	public void deleteByAdvisor_id(int advisor_id);

	@Query(value = "SELECT EXISTS(SELECT * FROM timeslot WHERE advisor_id = ?1)", nativeQuery = true)
	public int existsByAdvisor_id(int advisor_id);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM timeslot WHERE advisor_id = ?1 AND timeslots =?2", nativeQuery = true)
	public void deleteTimeslot(int adv_id, String ts);
	
	@Query(value = "SELECT timeslots FROM timeslot WHERE advisor_id = ?1 AND available =1" , nativeQuery = true)
	public ArrayList<String> getTimeslotsforStudents(int advisor_id);

	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE timeslot SET available=0 WHERE timeslots = ?1 AND advisor_id = ?2", nativeQuery = true)
	public void alterTS(String timeslots, int adv_id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE timeslot SET available=1, auth_user_id = NULL WHERE advisor_id = ?1 AND timeslots = ?2", nativeQuery = true)
	public void alterTS1(int adv_id, String ts1);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE timeslot SET auth_user_id=?1 WHERE advisor_id = ?2 AND timeslots = ?3", nativeQuery = true)
	public void saveStudent(int cwid, int adv_id, String timeslots);

	@Transactional
	@Modifying
	@Query(value = "UPDATE student SET timeslot = NULL WHERE advisor_id = ?1", nativeQuery = true)
	public void deleteStudenttimeslots(int advisor_id);
	
	
	@Query(value = "SELECT timeslot.auth_user_id, auth_user.first_name, auth_user.last_name, timeslot.timeslots, auth_user.email FROM timeslot INNER JOIN auth_user ON timeslot.auth_user_id = auth_user.auth_user_id WHERE timeslot.available = 0 AND timeslot.advisor_id=?1" , nativeQuery = true)
	public ArrayList<String> getSelectedTimeslots(int advisor_id);
	
	@Query(value = "SELECT student.auth_user_id, auth_user.first_name, auth_user.last_name, auth_user.email FROM student INNER JOIN auth_user ON student.auth_user_id = auth_user.auth_user_id WHERE (student.timeslot = 0 OR student.timeslot IS NULL) AND student.advisor_id=?1" , nativeQuery = true)
	public ArrayList<String> getNotSelectedTimeslots(int advisor_id);
	
}



