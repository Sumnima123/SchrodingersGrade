package net.code.flightplan.service;
import net.code.flightplan.entity.Advisor;
import net.code.flightplan.entity.Authorize;
import net.code.flightplan.entity.Student;
import net.code.flightplan.repo.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService {
	@Autowired
	AuthorizeRepo repoAuth;


	public void save(Authorize advisor) {
		repoAuth.save(advisor);
	}
	
	public void deleteByStudentidAdv(int adv_id, int std_id) {
		repoAuth.deleteByStudentidAdv(adv_id, std_id);
	}
	
	public boolean existsByStudentidAdv(int adv_id, int std_id) {
		if (repoAuth.existsByStudentidAdv(adv_id, std_id) == 1) {
			return true;
		}
		return false;
	}
	
	public void deleteByStudent_id(int adv_id, int std_id, String semester) {
		repoAuth.deleteByStudent_id(adv_id, std_id, semester);
	}
	
	public boolean existsByStudent_id(int adv_id, int std_id, String semester) {
		if (repoAuth.existsByStudent_id(adv_id, std_id, semester) == 1) {
			return true;
		}
		return false;
	}

	public Authorize get(int student_id) {
		return repoAuth.findById(student_id).get();
	}
	
	public Authorize getAuthorize(int student_id, String semester) {
		return repoAuth.getAuthorize(student_id, semester);
	}

	public List<String> getByStudent(String term){
		List<String> students = repoAuth.getByStudent(term);
		//System.out.print(cwids);
		return students;
	}
	
	public List<Authorize> getHistory(String term) {
		List<Authorize> hist = repoAuth.getHistory(term);
		return hist;
	}
	
}
