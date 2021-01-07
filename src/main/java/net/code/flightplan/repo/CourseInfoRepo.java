
package net.code.flightplan.repo;

import net.code.flightplan.entity.*;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CourseInfoRepo extends JpaRepository<CourseInfo, String> 
{

	@Modifying
    @Query(value = "SELECT concat(subject, ' ', number) as 'subNum' FROM course WHERE concat(subject, ' ', number) LIKE :term%", nativeQuery = true)
    List<String> getByTitle(String term);

}
