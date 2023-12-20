package com.siti.timesheet.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.siti.timesheet.model.Timesheet;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel="timesheet", path="timesheet")
public interface TimesheetRepository extends JpaRepository<Timesheet, Integer>{
	
	/*
	 * @Query("SELECT t FROM Timesheet t WHERE (:taskName IS NULL OR :taskName = '' OR t.taskName = :taskName)"
	 * )
	 */
	Page<Timesheet> findByTaskNameContaining(@Param("taskName") String taskName, Pageable pageable);
	
	

}
