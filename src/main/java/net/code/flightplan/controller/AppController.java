package net.code.flightplan.controller;

import net.code.flightplan.service.*;

import net.code.flightplan.entity.*;
import org.springframework.security.core.userdetails.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.NonUniqueResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.code.flightplan.model.User;
import net.code.flightplan.model.UsersDetails;

@Controller
public class AppController {

	@Autowired
	UserService userService;

	@Autowired
	AdvisorService advService;

	@Autowired
	StudentService studentservice;

	@Autowired
	TimeSlotService timeslotservice;

	@Autowired
	UserService userservice;
	
	@Autowired
	private CourseInfoService courseInfo;
	
	@Autowired 
	private StudentScheduleService service;
	
	@Autowired 
	private SectionService section_service;

	@Autowired
	private AuthorizeService history;
	
	int CWID;
	
	public void getCWID() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userservice.getUserbyEmail(email);
		CWID = user.getId();
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/student")		
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userservice.getUserbyEmail(email);

		int cwid = user.getId();	
		ArrayList<String> ss = service.getListofClasses(cwid);
		model.addAttribute("listofclasses", ss);	

		model.addAttribute("cwid", cwid);
		String firstname = user.getName();
		String lastname = user.getLastName();
		model.addAttribute("name", firstname + " " + lastname);

		return "home";	
	}

	@RequestMapping("/")
	public String viewHomePage(Model model) {

		return "login";
	}
	
	@RequestMapping("/advisor")
	public String timeSlot(Model model) throws NullPointerException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userservice.getUserbyEmail(email);
		int cwid = user.getId();

		// Get timeslots saved by the advisor
		ArrayList<String> ss = timeslotservice.getTimeslotsByAdvisor(cwid);
		JSONArray jsonArray2 = new JSONArray(ss);
		String load = jsonArray2.toString();

		Advisor adv = advService.get(cwid);

		String firstname = user.getName();
		String lastname = user.getLastName();

		
		try
        {
		String zoom = adv.getZoom();
		String[] zooms = zoom.split("`");	
		model.addAttribute("zoomID", zooms[0]);
		model.addAttribute("zoomPass", zooms[1]);		
        } 
        catch(NullPointerException e) { } 
		
		try
        {
		String dl = adv.getDeadline();
		String[] deadline = dl.split(",");		
		model.addAttribute("Start", deadline[0]);
		model.addAttribute("End", deadline[1]);
        } 
        catch(NullPointerException e) { } 
		
		model.addAttribute("timeslots", load);
		model.addAttribute("name", firstname + " " + lastname);
		

		return "TimeSlot";
	}

	@RequestMapping("/aptadvisor")
	public String advisorAppointment(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userservice.getUserbyEmail(email);
		String firstname = user.getName();
		String lastname = user.getLastName();
		model.addAttribute("name", firstname + " " + lastname);
		return "advisorAppointment";
	}
	
	@RequestMapping("/apt")
	public String studentAppointment(Model model) {

		return "studentAppointment";
	}
	
	@RequestMapping("/advisorcalendar")
	public String advisorcalendar(@RequestParam(value = "stdid", required = false) String stdid, Model model) {
	
		model.addAttribute("student_id", stdid);
		
		int cwid1= Integer.parseInt(stdid);
		User std = userservice.get(cwid1);
		String student_firstname = std.getName();
		String student_lastname = std.getLastName();
		model.addAttribute("student_name", student_firstname + " " + student_lastname);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userservice.getUserbyEmail(email);
		String firstname = user.getName();
		String lastname = user.getLastName();
		model.addAttribute("name", firstname + " " + lastname);
		return "advisorCalendar";
	}
	
	@RequestMapping("/search")
	public String studentSearch(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userservice.getUserbyEmail(email);
		int adv_id = user.getId();
		Advisor adv = advService.get(adv_id);

		String firstname = user.getName();
		String lastname = user.getLastName();
		model.addAttribute("name", firstname + " " + lastname);
		
		return "search";
	}

	

	@Autowired
	AuthorizeService authorizeService;
	
	@RequestMapping(value = "/authorize")
	public String authorize(@RequestParam(value = "id", required = false) String id, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userservice.getUserbyEmail(email);
		int adv_id = user.getId();

		String firstname = user.getName();
		String lastname = user.getLastName();
		System.out.println(id);
		
		model.addAttribute("student_id", id);
		int cwid= Integer.parseInt(id);
		Student student =studentservice.get(cwid);
		model.addAttribute("timeslot", student.getTimeslot());
		User std = userservice.get(cwid);
		String student_firstname = std.getName();
		String student_lastname = std.getLastName();
		model.addAttribute("student_name", student_firstname + " " + student_lastname);
		model.addAttribute("adv_id", adv_id);
		model.addAttribute("name", firstname + " " + lastname);
		
		
		ArrayList<String> ss = service.getListofClasses(cwid);
		model.addAttribute("listofclasses", ss);
		

		try
        {
		ArrayList<String> sem_list = service.getListofSemester(cwid);
		String sem= sem_list.get(0);
		System.out.println(sem);
		model.addAttribute("semester", sem);
        }
		catch(IndexOutOfBoundsException e) { } 
		
		
		
		try
        {
			
		ArrayList<String> sem_list1 = service.getListofSemester(cwid);
		System.out.println(sem_list1);
		String sem1= sem_list1.get(0);
		Authorize auth1 =authorizeService.getAuthorize(cwid, sem1);
		if (authorizeService.existsByStudent_id(adv_id, cwid, sem1)) {
			model.addAttribute("date", auth1.getAuthorized_date());
			System.out.println(auth1.getAdvisor_name());
			model.addAttribute("advname", auth1.getAdvisor_name());
		
			model.addAttribute("check", true);
		}
			else {
				model.addAttribute("check", false);
			}
        }
		catch(NoSuchElementException e) { } 
		catch(IndexOutOfBoundsException e) { } 
		
		try
        {
		if (studentservice.isAgreed(cwid)) {
			model.addAttribute("checkS", true);
		
		}
		else {
				model.addAttribute("checkS", false);
				
		}
        }
		catch(NoSuchElementException e) { } 
		
		
		return "authorize";
	}


	@RequestMapping(value = "/titleAutocomplete") 
	@ResponseBody 
    public List<String> autoName(@RequestParam(value = "term", required = false, defaultValue = "")String term)
	{
     
		List<String> designation = courseInfo.getByTitle(term);
		//System.out.println(designation);
        return designation;
        
        //Exception
    }

	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = {"application/JSON"}) 
	@ResponseBody 
    public List<StudentSchedule> getUserSchedule() throws JsonProcessingException
	{
		getCWID();
		String student_cwid= String.valueOf(CWID);
		List<StudentSchedule> designation = service.getUserSchedule(student_cwid);
		
        return designation;  

    }

	
	@RequestMapping(value = "/advevents/{stdID}", method = RequestMethod.GET, produces = {"application/JSON"}) 
	@ResponseBody 
    public List<StudentSchedule> getStudentSchedule(@PathVariable String stdID) throws JsonProcessingException
	{
		getCWID();
		System.out.println(stdID);
		String student_cwid= String.valueOf(stdID);
		List<StudentSchedule> designation = service.getUserSchedule(student_cwid);
		
        return designation;  

    }

	@GetMapping("/calendar") 
    public String editCalendar(Model model)
    { 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userservice.getUserbyEmail(email);
		int cwid = user.getId();
		String firstname = user.getName();
		String lastname = user.getLastName();
		model.addAttribute("name", firstname + " " + lastname);
		
		model.addAttribute("std_id", cwid);
		
		Student student =studentservice.get(cwid);
		int adv_id = student.getAdvisor_id();
		if (authorizeService.existsByStudentidAdv(adv_id, cwid)) {
			model.addAttribute("check", true);
			}
		else {
			model.addAttribute("check", false);
		}
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
		
	
		try
        {
		if (studentservice.isAgreed(cwid)) {
			model.addAttribute("checkS", true);
			System.out.println(studentservice.isAgreed(cwid));
		}
		else {
				model.addAttribute("checkS", false);
				System.out.println(studentservice.isAgreed(cwid));
		}
        }
		catch(NoSuchElementException e) { } 
       
		
		
		return "calendar"; 
    }
	

	
	
	
	
	
	
	
	
	

	List<String> all_sections = new ArrayList<String>();
	@RequestMapping(value = "/postsubject", method = RequestMethod.POST)
	public String postSections(@RequestBody String subject) 
	{
		//System.out.print(subject.substring(12, 21));
		all_sections = section_service.getSectionByCourse(subject.substring(12, 21));
		
		return "calendar"; 
	}
	
	//This might just be a helper method for postSections()
	@ResponseBody
	@RequestMapping(value = "/getsubject", method = RequestMethod.GET)
	public List<String> getSections(Model model){
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
	List<String> my_sections = all_sections;
	System.out.print(all_sections);
	//my_sections.toString();
	return my_sections;
	}
	//Helper method to return a string for dow in the Schedule table
	//Don't delete this; trust me, it works...
	public String dowHelper(String days) {
     	String [] week = {"S","M","T","W","R","F","S"}; //position 0 --> 6; Sunday through Saturday
		String dow = ""; //empty string to be filled
		//pointers 
		int j = 1;
		int f = 0;
		//loop
		for(int i  = 0; i < week.length; i++){
		  if(week[i].equals(days.substring(f,j)) && j < days.length()){
		      dow += Integer.toString(i) + ",";
		      //System.out.println("String " + Integer.toString(i) +" was added!");
		      j++;f++;//increment pointers
		  }
		  else if(week[i].equals(days.substring(f,j)) && j == days.length()){
		      dow += Integer.toString(i);
		      //System.out.println("String " + Integer.toString(i) +" was added!");
		  }
		}
	    
		dow = ("["+dow+"]");
		//System.out.println(dow + " is your final output");
		return dow;
	}
	
	
	String mytitle= "";
	@RequestMapping(value = "/accepted", method = RequestMethod.POST)
    public String addSchedule(@RequestBody String course, Model model) throws JsonProcessingException 
	{
		//vars
		ArrayList<StudentSchedule> myArray = new ArrayList<StudentSchedule>();
			myArray=service.getArrayByUser(String.valueOf(CWID));
		int crn = Integer.parseInt(course.substring(0,5));	
		StudentSchedule sec = new StudentSchedule();
		int flag = 0;
		
		getCWID();
		System.out.println(crn);
		System.out.println(CWID);
		
				
				sec.setsubject(service.getSubjectByCrn(crn));
				sec.setTitle(service.getTitleBySubject(crn));
				sec.setStart(service.getStartTimeByCrn(crn));
				sec.setEnd(service.getEndTimeByCrn(crn));
				sec.setNumber(service.getNumberByCrn(crn));
				//set the dow using helper method dowHelper!
				String dow = service.getDowByCrn(crn);
				sec.setDow(dowHelper(dow));//Setting the dow
				sec.setCwid(String.valueOf(CWID));
				sec.setNumber(service.getNumberByCrn(crn));
				sec.setDay(service.getDayByCrn(crn));
				sec.setcrn(crn);
				sec.setSemester(service.getSemesterByCrn(crn));
		
		for(int j = 0; j < myArray.size(); j++) {
			int inner_flag;
			int local_flag = dayComparison(sec.getDow(), myArray.get(j).getDow());
			if(local_flag == 1) {
				System.out.println("\n" +  local_flag + " is the dayComparison flag");
				inner_flag = collision(sec.getStart(),sec.getEnd(), myArray.get(j).getStart(), myArray.get(j).getEnd());
				if(inner_flag == 1)
				{
					flag = 1;
					break;
				}
			}
			System.out.println("The final flag result is: " + flag);
		}
		
		if(flag == 0) 
		{
		
			service.save(sec);
			//System.out.println(myArray.get(0).getStart() + " HELLO DARKNESS MY OLD FRIEND");
		}
		else 
		{
			System.out.println("There is a collision with " + sec.getTitle() + " and some other course...");
			mytitle = sec.getTitle();
			model.addAttribute("all_sec", mytitle);
		}
		return "calendar";
	}
	
	
	
	
	@RequestMapping(value = "/acceptedadvisor/{stdID}", method = RequestMethod.POST)
    public String addSchedulebyAdvisor(@RequestBody String course, @PathVariable String stdID, Model model) throws JsonProcessingException 
	{
		
		ArrayList<StudentSchedule> myArray = new ArrayList<StudentSchedule>();
			myArray=service.getArrayByUser(stdID);
		int crn = Integer.parseInt(course.substring(0,5));	
		StudentSchedule sec = new StudentSchedule();
		int flag = 0;
		
		getCWID();
		System.out.println(crn);
		System.out.println(stdID);
		
				
		sec.setsubject(service.getSubjectByCrn(crn));
		sec.setTitle(service.getTitleBySubject(crn));
		sec.setStart(service.getStartTimeByCrn(crn));
		sec.setEnd(service.getEndTimeByCrn(crn));
		sec.setNumber(service.getNumberByCrn(crn));
		//set the dow using helper method dowHelper!
		String dow = service.getDowByCrn(crn);
		sec.setDow(dowHelper(dow));//Setting the dow
		sec.setCwid(String.valueOf(stdID));
		sec.setNumber(service.getNumberByCrn(crn));
		sec.setDay(service.getDayByCrn(crn));
		sec.setSemester(service.getSemesterByCrn(crn));
		sec.setcrn(crn);
		
		for(int j = 0; j < myArray.size(); j++) {
			int inner_flag;
			int local_flag = dayComparison(sec.getDow(), myArray.get(j).getDow());
			if(local_flag == 1) {
				System.out.println("\n" +  local_flag + " is the dayComparison flag");
				inner_flag = collision(sec.getStart(),sec.getEnd(), myArray.get(j).getStart(), myArray.get(j).getEnd());
				if(inner_flag == 1)
				{
					flag = 1;
					break;
				}
			}
			System.out.println("The final flag result is: " + flag);
		}
		
		if(flag == 0) 
		{
		
			service.save(sec);
			//System.out.println(myArray.get(0).getStart() + " HELLO DARKNESS MY OLD FRIEND");
		}
		else 
		{
			System.out.println("There is a collision with " + sec.getTitle() + " and some other course...");
			mytitle = sec.getTitle();
			model.addAttribute("all_sec", mytitle);
		}
		return "calendar";
	}
	
	
	
	

	@RequestMapping(value = "/deleted", method = RequestMethod.POST)
    public String deleteSchedule(@RequestBody String input) throws JsonProcessingException {
	
		int crn = Integer.parseInt(input.substring(0,5));
		System.out.println("The crn will be " + crn);
		service.deleteSectionById(crn, String.valueOf(CWID));
		 
		return "calendar";
	}
	
	
	
	@RequestMapping(value = "/deletedadvisor/{stdID}", method = RequestMethod.POST)
    public String deleteSchedulebyAdvisor(@RequestBody String input, @PathVariable String stdID) throws JsonProcessingException {
	
	
		int crn = Integer.parseInt(input.substring(0,5));
		System.out.println("The crn will be " + crn);
		service.deleteSectionById(crn, stdID);
		 
		return "calendar";
	}
	
	
	
	//Helper method made to detect collisions in times
    public int collision(String startN, String endN, String startO, String endO){
        int flag;
            //new String
            String newStartDate = startN; //1
            String newEndDate = endN; //2
            
            //old string
            String oldStartDate = startO;
            String oldEndDate = endO;
            
            //new Time
            String[] array1 = newStartDate.split(" "); //1
            String[] array2 = newEndDate.split(" "); //2
            
            String newStartTime = array1[1]; //1
            String newEndTime = array2[1];  //2
            
            //old Time
            String[] array3 = oldStartDate.split(" "); //1
            String[] array4 = oldEndDate.split(" "); //2
            
            String oldStartTime = array3[1]; //1
            String oldEndTime = array4[1];  //2
            //Set the LocalTimes 
            LocalTime newStart = LocalTime.parse(newStartTime);
            LocalTime newEnd = LocalTime.parse(newEndTime);
            LocalTime oldStart = LocalTime.parse(oldStartTime);
            LocalTime oldEnd = LocalTime.parse(oldEndTime);
            //Start inbetween
            if(oldStart.compareTo(newStart) <= 0 && oldEnd.compareTo(newStart) == 1){
                System.out.println(oldStart + "\n" + newStart + "\n" + oldEnd);
                flag = 1;
            }
            else if(oldStart.compareTo(newEnd) <= 0 && oldEnd.compareTo(newEnd) >= 0){
                System.out.println(oldStart + "\n" + newEnd + "\n" + oldEnd);
                flag = 1;
            }
            else if (newStart.compareTo(oldEnd) <= 0 && newEnd.compareTo(oldEnd) >= 0){
                System.out.println("hello");
                System.out.println(oldStart.compareTo(newStart) + " " + oldEnd.compareTo(newStart) + " " +oldStart.compareTo(newEnd)+ " " +oldEnd.compareTo(newEnd) );
                flag = 1;
            }
            else{
                flag = 0;
            }
        return flag;
    }
	//makes a day comparison 
	public int dayComparison(String day1, String day2){
        //getting the strings ready 
        day1 = day1.substring(1,day1.length() - 1);
        day2 = day2.substring(1,day2.length() - 1);
        day1 = day1.replaceAll(",","");
        day2 = day2.replaceAll(",","");
        System.out.println(day1 + " " +day2);
        
        int flag = 0;
        for(int i = 0; i < day1.length(); i++){
            String str = Character.toString(day1.charAt(i));
            if(day2.contains(str)){
                flag = 1; 
            }
            
        }
        return flag;
    }
	
	/**
	@RequestMapping(value = "/section_send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Send Section", response = Account.class)
	public AccountDto registration(@Valid @RequestBody RegistrationDto registration, HttpServletResponse response) {
	    Account account = new Account(registration.getEmail(), registration.getPassword());
	    return new AccountDto(account);
	}*/
	public String passConflict(String Title)
	{
		return Title;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getconflictmessage", method = RequestMethod.GET)
	public String returnConflict(String Title)
	{
		
		//String myTitle = Title;
		//System.out.println(model.getAttribute(conflicttitle));
		//System.out.println(myTitle + "iis the title on GET");
		String titleret = mytitle;
		mytitle= "";
	 	return titleret;	
	 	
	}


	
	
	@GetMapping("/advisinghistory") 
    public String viewAdvising(Model model)
    { 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userservice.getUserbyEmail(email);
		
		String firstname = user.getName();
		String lastname = user.getLastName();
		model.addAttribute("name", firstname + " " + lastname);
		return "advisinghistory"; 
    }
	
	@RequestMapping(value = "/nameAutocomplete") 
	@ResponseBody 
    public List<String> autoStudent(@RequestParam(value = "term", required = false, defaultValue = "")String term)
	{
     
		List<String> students = history.getByStudent(term);
		System.out.println(students + " controller");
        return students;
        
        //Exception
    }
	
	String globalName = " ";
	@RequestMapping(value = "/postStudent", method = RequestMethod.POST)
	public String postStudent(@RequestBody String student) 
	{
		System.out.print(student);
		String edited = student.substring(0,student.length()-16);
		edited = edited.replace("+", " ");
		System.out.print(edited);
		//all_sections = section_service.getSectionByCourse(subject.substring(12, 21));
		globalName = edited;
		return "advisinghistory";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAuthorized", method = RequestMethod.GET)
	public List<Authorize> getAuthorized(Model model){
		List<Authorize> hist = history.getHistory(globalName);
		return hist;
	}
 
    
}
