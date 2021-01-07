package net.code.flightplan.service;
import net.code.flightplan.entity.Student;
import net.code.flightplan.repo.*;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	StudentRepo repoS;
	
	public void save(Student student) {
		repoS.save(student);
	}

	public Student get(int auth_user_id) {
		return repoS.findById(auth_user_id).get();
	}
	
	public void deleteTimeslot(int adv_id, String ts) {
		repoS.deleteTimeslot(adv_id, ts);
		
	}

	public void agree(int std_id) {
		repoS.agree(std_id);
	}
	
	public boolean isAgreed(int std_id) {
		if (repoS.isAgreed(std_id) == 1) {
			return true;
		}
		return false;
	}
	
	public void disagree(int std_id) {
		repoS.disagree(std_id);
	}
	
}
