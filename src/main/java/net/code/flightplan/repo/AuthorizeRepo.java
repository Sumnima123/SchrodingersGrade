package net.code.flightplan.repo;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import net.code.flightplan.entity.Authorize;


@Repository
public interface AuthorizeRepo extends JpaRepository<Authorize, Integer>{

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM authorize WHERE advisor_id = ?1 AND student_id = ?2 AND semester= ?3", nativeQuery = true)
	public void deleteByStudent_id(int adv_id, int std_id, String semester);

	@Query(value = "SELECT EXISTS(SELECT * FROM authorize WHERE advisor_id = ?1 AND student_id = ?2 AND semester= ?3)", nativeQuery = true)
	public int existsByStudent_id(int adv_id, int std_id, String semester);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM authorize WHERE advisor_id = ?1 AND student_id = ?2", nativeQuery = true)
	public void deleteByStudentidAdv(int adv_id, int std_id);
	
	@Query(value = "SELECT EXISTS(SELECT * FROM authorize WHERE advisor_id = ?1 AND student_id = ?2)", nativeQuery = true)
	public int existsByStudentidAdv(int adv_id, int std_id);
	
	@Query("SELECT u FROM Authorize u WHERE u.student_id = :student_id AND u.semester = :semester" )
	public Authorize getAuthorize(@Param("student_id") int student_id, @Param("semester") String semester);
	
	@Modifying
	@Query(value = "SELECT concat(auth_user.first_name, ' ', auth_user.last_name, ' ', '[', auth_user.auth_user_id, ']') FROM auth_user JOIN auth_user_role ON auth_user.auth_user_id = auth_user_role.auth_user_id WHERE auth_user_role.auth_role_id = 2 AND concat(auth_user.first_name, ' ', auth_user.last_name, ' ', '[', auth_user.auth_user_id, ']') LIKE :term%", nativeQuery = true)
	List<String> getByStudent(String term);
	
	@Query(value = "SELECT * FROM authorize WHERE student_name = ?1", nativeQuery = true)
	List<Authorize> getHistory(String term);
	
}