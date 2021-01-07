package net.code.flightplan.repo;

import java.util.ArrayList;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.code.flightplan.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

	@Transactional
	@Modifying
	@Query(value = "UPDATE student SET timeslot = NULL WHERE advisor_id = ?1 AND timeslot =?2", nativeQuery = true)
	public void deleteTimeslot(int adv_id, String ts);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE student SET agreed = 1 WHERE auth_user_id = ?1", nativeQuery = true)
	public void agree(int std_id);

	@Query(value = "SELECT EXISTS(SELECT * FROM student WHERE auth_user_id = ?1 AND agreed = 1)", nativeQuery = true)
	public int isAgreed(int std_id);
	
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE student SET agreed = 0 WHERE auth_user_id= ?1", nativeQuery = true)
	public void disagree(int std_id);
	
	
	
}
