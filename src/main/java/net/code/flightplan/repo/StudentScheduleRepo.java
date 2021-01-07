package net.code.flightplan.repo;

import net.code.flightplan.entity.*;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StudentScheduleRepo extends JpaRepository<StudentSchedule, Integer> {
	/**
	 * 
	 * @return schedule information
	 */
	@Modifying
	@Query(value = "SELECT * FROM student_schedule WHERE cwid = ?1", nativeQuery = true)
	List<StudentSchedule> getByUser(String student_cwid);
	
	@Query(value = "SELECT * FROM student_schedule WHERE cwid = ?1", nativeQuery = true)
	ArrayList<StudentSchedule> getArrayByUser(String student_cwid);

	// These queries will help form an object to save to the repo
	@Query(value = "SELECT subjectcode FROM section WHERE crn = ?1", nativeQuery = true)
	String getSubjectByCrn(int term);

	
	@Query(value = "SELECT title FROM course WHERE concat(subject, ' ', number) = (SELECT concat(subjectcode, ' ', number) FROM section WHERE crn = ?1)", nativeQuery = true)
	String getTitleBySubject(int term);

	@Query(value = "SELECT start FROM section WHERE crn = ?1", nativeQuery = true)
	String getStartTimeByCrn(int term);

	@Query(value = "SELECT end FROM section WHERE crn = ?1", nativeQuery = true)
	String getEndTimeByCrn(int term);

	@Query(value = "SELECT day FROM section WHERE crn = ?1", nativeQuery = true)
	String getDowByCrn(int term);
	
	//New queries for the repo
	@Query(value = "SELECT crn FROM section WHERE subject = ?1", nativeQuery = true)
	List<Integer> getCrnsBySubject(String subject);
	
	
	@Query(value = "SELECT CONCAT(student_schedule.crn, ' ', student_schedule.title, ' [', student_schedule.subject, ' ', student_schedule.number, ']') FROM student_schedule WHERE cwid = ?1" , nativeQuery = true)
	public ArrayList<String> getListofClasses(int cwid);
	
	@Query(value = "SELECT semester FROM student_schedule WHERE cwid = ?1" , nativeQuery = true)
	public ArrayList<String> getListofSemester(int cwid);
	
	
	@Query(value = "SELECT semester FROM student_schedule WHERE cwid = ?1 AND crn=?2", nativeQuery = true)
    String getSemester(int std_id, int crn);
	
	@Query(value = "SELECT number FROM section WHERE crn = ?1", nativeQuery = true)
    String getNumberByCrn(int term);
	
	@Query(value = "SELECT day FROM section WHERE crn = ?1", nativeQuery = true)
    String getDayByCrn(int term);
	
	@Modifying 
	@Query(value = "DELETE FROM student_schedule where crn = ?1 AND cwid = ?2", nativeQuery = true)
	void deleteSectionById(int crn, String cwid);
	
	@Query(value = "SELECT semester FROM section WHERE crn = ?1", nativeQuery = true)
    String getSemesterByCrn(int term);
	
	
}

//	@Query(value = "")