package net.code.flightplan.entity;

import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "studentSchedule")
public class StudentSchedule 
{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
	private String subject;
	private String title;
	private String dow;
	private String cwid;
	private int crn; 
	public String Day; 
	private String semester;
	
	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String start;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private String end;

	private String number;
	
	
	//DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

   //Constructors 
	public StudentSchedule() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentSchedule(int id, String subject, String title, String start, String end, String dow, String cwid, int crn, String number, String Day, String semester) {
		this.id = id;
		this.subject = subject;
		this.title = title;
		this.start = start;
		this.end = end;
		this.dow = dow;
		this.cwid = cwid;
		this.crn = crn;
		this.number = number;
		this.Day= Day;
		this.semester= semester;
	}

	
	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	// Subject
	public String getsubject() {
		return subject;
	}

	public void setsubject(String subject) {
		this.subject = subject;
	}

	// Title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// Start time
	public String getStart() {

		return start;
	}

	public void setStart(String start) {
		this.start = start;

	}

	// End time
	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	// Day of Week
	public String getDow() {
		return dow;
	}

	public void setDow(String dow) {
		this.dow = dow;
	}
	
	public void setCwid(String cwid) {
		this.cwid = cwid;
	}
	
	public String getCwid() {
		return cwid;
	}

	public int getcrn() {
		return crn;
	}

	public void setcrn(int crn) {
		this.crn = crn;
	}

	public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
        
    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }
	 
}