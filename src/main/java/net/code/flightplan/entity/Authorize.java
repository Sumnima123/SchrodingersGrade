package net.code.flightplan.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "authorize")
public class Authorize {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authorize_id")
	private int authorize_id;
	
	@Column(name = "advisor_id")
	private int advisor_id;
	
	@Column(name = "advisor_name")
	private String advisor_name;
	
	
	@Column(name = "student_id")
	private int student_id;
	
	@Column(name = "student_name")
	private String student_name;
	
	@Column(name = "semester")
	private String semester;
	
	@Column(name = "class_crn")
	private String class_crn;
	
	@Column(name = "authorized_date")
	private String authorized_date;
	
	 private String advisor_comment;
	
	public String getAdvisor_comment() {
		return advisor_comment;
	}

	public void setAdvisor_comment(String advisor_comment) {
		this.advisor_comment = advisor_comment;
	}

	public Authorize() {
		
	}
	
	public Authorize(int authorize_id, int advisor_id, String advisor_name, int student_id, String student_name,
			String semester, String class_crn, String authorized_date, String advisor_comment) {
		super();
		this.authorize_id = authorize_id;
		this.advisor_id = advisor_id;
		this.advisor_name = advisor_name;
		this.student_id = student_id;
		this.student_name = student_name;
		this.semester = semester;
		this.class_crn = class_crn;
		this.authorized_date = authorized_date;
		this.setAdvisor_comment(advisor_comment);
	}

	public int getAuthorize_id() {
		return authorize_id;
	}

	public void setAuthorize_id(int authorize_id) {
		this.authorize_id = authorize_id;
	}

	public int getAdvisor_id() {
		return advisor_id;
	}

	public void setAdvisor_id(int advisor_id) {
		this.advisor_id = advisor_id;
	}

	public String getAdvisor_name() {
		return advisor_name;
	}

	public void setAdvisor_name(String advisor_name) {
		this.advisor_name = advisor_name;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getClass_crn() {
		return class_crn;
	}

	public void setClass_crn(String class_crn) {
		this.class_crn = class_crn;
	}

	public String getAuthorized_date() {
		return authorized_date;
	}

	public void setAuthorized_date(String authorized_date) {
		this.authorized_date = authorized_date;
	}

	

	
	
	

	
}
