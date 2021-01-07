package net.code.flightplan.controller;

import java.sql.Types;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.persistence.NonUniqueResultException;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import net.code.flightplan.entity.TimeSlot;
import net.code.flightplan.entity.TimeSlots;
import net.code.flightplan.model.User;
import net.code.flightplan.entity.Advisor;
import net.code.flightplan.entity.Authorize;
import net.code.flightplan.entity.Student;
import net.code.flightplan.service.AdvisorService;
import net.code.flightplan.service.AuthorizeService;
import net.code.flightplan.service.StudentService;
import net.code.flightplan.service.TimeSlotService;
import net.code.flightplan.service.UserService;


@RestController
public class RestWebController {

	TimeSlots timeslots = new TimeSlots();
	int cwid;

	@Autowired
	TimeSlotService timeslotservice;
	
	@Autowired
	StudentService studentservice;
	
	@Autowired
	AdvisorService advService;
	
	@Autowired
	UserService userservice;

	@Autowired
	AuthorizeService authorizeService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/timeslotdb")
	public void postTimeSlots(@RequestBody TimeSlots timeslots1) {
		getCWID();
		ArrayList<String> sss = timeslots1.getTimeslots1();
		timeslots.setTimeslots1(sss);
		// check if there is timeslots saved by the advisor, if true remove them
		if (timeslotservice.existsByAdvisorId(cwid)) {
			timeslotservice.deleteByAdvisor_id(cwid);
		}
		for (String strTemp : sss) {
			TimeSlot adv = new TimeSlot();
			adv.setAdvisor_id(cwid);
			adv.setTimeslots(strTemp);
			adv.setAvailable(1);
		
			timeslotservice.save(adv);
			
		}
		timeslotservice.deleteStudenttimeslots(cwid);
	}

	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/timeslotadd")
	public void addTimeSlots(@RequestBody TimeSlots timeslots2) {
		getCWID();
		ArrayList<String> sss = timeslots2.getTimeslots1();
		timeslots.setTimeslots1(sss);
		
		for (String strTemp : sss) {
			TimeSlot adv = new TimeSlot();
			adv.setAdvisor_id(cwid);
			adv.setTimeslots(strTemp);
			adv.setAvailable(1);
			timeslotservice.save(adv);
			
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/deletetimeslot")
	public void deleteTimeSlots(@RequestBody TimeSlots timeslots3) {
		getCWID();
		ArrayList<String> sss = timeslots3.getTimeslots1();
		timeslots.setTimeslots1(sss);
		
		for (String strTemp : sss) {
			System.out.println(strTemp+" "+ cwid);
			timeslotservice.deleteTimeslot(cwid, strTemp);	
			studentservice.deleteTimeslot(cwid, strTemp);
		}
	}			
	
	@GetMapping(value = "/getavailabletimeslots")
	public String getResourceforStudents() {
		getCWID();
		Student student = studentservice.get(cwid);
		int advisor_id = student.getAdvisor_id();
		ArrayList<String> ss = timeslotservice.getTimeslotsforStudents(advisor_id);
		JSONArray jsonArray2 = new JSONArray(ss);
		String load = jsonArray2.toString();
		return load;
	}
	
	@GetMapping(value = "/getselectedstudents")
	public String getSelectedStudents() {
		getCWID();
		ArrayList<String> ss = timeslotservice.getSelectedTimeslots(cwid);
		JSONArray jsonArray2 = new JSONArray(ss);
		String load = jsonArray2.toString();

	
		return load;
	}
	
	@GetMapping(value = "/getnotselectedstudents")
	public String getNotSelectedStudents() {
		getCWID();
		ArrayList<String> ss = timeslotservice.getNotSelectedTimeslots(cwid);
		JSONArray jsonArray2 = new JSONArray(ss);
		String load = jsonArray2.toString();
	System.out.println(load);
	
		return load;
	}
	
	
	
	@GetMapping(value = "/getstudenttimeslot")
	public String getStudentTimeslot() {
		getCWID();
		Student student =studentservice.get(cwid);
		return student.getTimeslot();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/studenttimeslot")
	public void postStudentTS(@RequestBody String timeslots) {
		getCWID();
		Student std =studentservice.get(cwid);
		std.setTimeslot(timeslots);
		studentservice.save(std);
		
		int adv_id = std.getAdvisor_id();
		timeslotservice.saveStudent(cwid, adv_id, timeslots);
		timeslotservice.alterTS(timeslots, adv_id);		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/studenttimeslot1")
	public void postStudentTS1(@RequestBody String timeslots) {	
		getCWID();
		Student student =studentservice.get(cwid);
		int adv_id = student.getAdvisor_id();
		timeslotservice.alterTS(timeslots, adv_id);		
		timeslotservice.saveStudent(cwid, adv_id, timeslots);		
		String ts1 = student.getTimeslot();
		timeslotservice.alterTS1(adv_id, ts1);
		student.setTimeslot(timeslots);
		studentservice.save(student);
	}
	
	
	
	
	
	@GetMapping(value = "/getname")
	public String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();	
		User user = userservice.getUserbyEmail(email);
		String firstname = user.getName();
		String lastname = user.getLastName();		
		return firstname+ " " + lastname;
	}
	
	@GetMapping(value = "/getAdvisorName")
	public String getAdvisorName() {
		getCWID();
		Student student =studentservice.get(cwid);
		int adv_id = student.getAdvisor_id();
		User user = userservice.get(adv_id);
		String firstname = user.getName();
		String lastname = user.getLastName();

		return firstname+ " " + lastname;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/zoom")
	public void postDeadline(@RequestBody String deadline) {
		getCWID();
		Advisor adv =advService.get(cwid);
		adv.setZoom(deadline);
		advService.save(adv);	
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/gg")
	public void gg(@RequestBody String gg) {
		System.out.println(gg);			
	}
	
	
	@GetMapping(value = "/getzoom1")
	public String getZoom1() {
		getCWID();
		Student student =studentservice.get(cwid);
		int adv_id = student.getAdvisor_id();
		Advisor adv =advService.get(adv_id);
		return adv.getZoom();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deadline")
	public void postZoom(@RequestBody String zoom) {
		getCWID();
		Advisor adv =advService.get(cwid);
		adv.setDeadline(zoom);
		advService.save(adv);	
		
	}
	
	
	@GetMapping(value = "/getdeadline1")
	public String getDeadline1() {
		getCWID();
		Student student =studentservice.get(cwid);
		int adv_id = student.getAdvisor_id();
		Advisor adv =advService.get(adv_id);
		return adv.getDeadline();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/agreement")
	public void agreement(@RequestBody String agree) {
		getCWID();
		
		String[] parts = agree.split(">");
		
		int adv_id = Integer.parseInt(parts[0]); 
		String adv_name = parts[1]; 
		int std_id = Integer.parseInt(parts[2]); 
		String std_name = parts[3];
		String advised_date = parts[4];
		String semester= parts[5];
		String comment= parts[6];
		String classes= parts[7];
		
		System.out.println(classes);
		
		try
        {
		if (authorizeService.existsByStudent_id(adv_id, std_id, semester)) {
		authorizeService.deleteByStudent_id(adv_id, std_id, semester);
		}
		
		Authorize auth = new Authorize();
		auth.setAdvisor_id(cwid);
		auth.setAdvisor_name(adv_name);
		auth.setStudent_id(std_id);
		auth.setStudent_name(std_name);
		auth.setClass_crn(classes);
		auth.setSemester(semester);
		auth.setAdvisor_comment(comment);
		auth.setAuthorized_date(advised_date);
		authorizeService.save(auth);
        }
		catch(NonUniqueResultException e) { } 
		
				
		}
	
	@GetMapping(value = "/getagreement")
	public String getAgreement() {
		getCWID();
		Student student =studentservice.get(cwid);
		int adv_id = student.getAdvisor_id();
		Advisor adv =advService.get(adv_id);
		return adv.getDeadline();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/disapprove")
	public void disapprove(@RequestBody String std_id) {
		getCWID();
		authorizeService.deleteByStudentidAdv(cwid, Integer.parseInt(std_id));
		System.out.println(std_id);	
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/studentapprove")
	public void studentapprove() {
		getCWID();
		studentservice.agree(cwid);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/studentcancel")
	public void studentcancel() {
		getCWID();
		studentservice.disagree(cwid);

	}
	
	
	public void getCWID() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();	
		User user = userservice.getUserbyEmail(email);
		cwid = user.getId();		
	}
}
