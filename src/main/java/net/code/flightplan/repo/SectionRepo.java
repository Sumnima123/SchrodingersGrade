package net.code.flightplan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.code.flightplan.entity.Section;

public interface SectionRepo extends JpaRepository<Section, String> 
{
	@Query(value = "SELECT CONCAT(section.crn,' ', course.title, ', ', section.day, ' ', DATE_FORMAT(start, '%h:%i%p'), ' to ', DATE_FORMAT(end, '%h:%i%p'), '<br> ', section.instructor, '<br> ', section.location)" + 
			"FROM section, course WHERE CONCAT(course.subject, ' ', course.number) = CONCAT(section.subjectcode, ' ', section.number) AND CONCAT(course.subject, ' ', course.number) = ?1 ORDER BY section.crn ASC;", nativeQuery = true)
	List<String> getSectionByCourse(String term);
}
