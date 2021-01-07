package net.code.flightplan.service;
import net.code.flightplan.entity.Advisor;
import net.code.flightplan.entity.Student;
import net.code.flightplan.repo.*;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvisorService {
	@Autowired
	AdvisorRepo repoA;


	public void save(Advisor advisor) {
		repoA.save(advisor);
	}
	
	public Advisor get(int advisor_id) {
		return repoA.findById(advisor_id).get();
	}
	
	


}