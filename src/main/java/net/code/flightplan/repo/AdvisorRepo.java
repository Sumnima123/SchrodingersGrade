package net.code.flightplan.repo;

import java.util.ArrayList;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.code.flightplan.entity.Advisor;

@Repository
public interface AdvisorRepo extends JpaRepository<Advisor, Integer>{

	

	
	
	
}
