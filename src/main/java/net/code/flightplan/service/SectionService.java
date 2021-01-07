package net.code.flightplan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.code.flightplan.repo.*;

@Service
public class SectionService 
{
	@Autowired 
	private SectionRepo repo;
	//Method for the SectionRepo where we get a list of strings to send to Schedule.html
	public List<String> getSectionByCourse(String term){
		List<String> courses = (List<String>) repo.getSectionByCourse(term);
        return courses;
	}
}
