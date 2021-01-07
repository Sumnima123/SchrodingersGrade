package net.code.flightplan.service;

import net.code.flightplan.entity.*;
import net.code.flightplan.repo.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseInfoService {
	@Autowired
	private CourseInfoRepo repo;

	// Method from the CourseInfoRepo made by Adam 9/22/2020
	public List<String> getByTitle(String term) {
		List<String> courses = (List<String>) repo.getByTitle(term);
		return courses;
	}

	public List<CourseInfo> listAll() {
		return repo.findAll();
	}

	// Saves a course object to the repository
	public void save(CourseInfo course) {
		repo.save(course);
	}

	// finds the course by id and returns it
	public CourseInfo get(String subject) {
		return repo.findById(subject).get();
	}

	// Delete course
	public void delete(String subject) {
		repo.deleteById(subject);
	}
}
