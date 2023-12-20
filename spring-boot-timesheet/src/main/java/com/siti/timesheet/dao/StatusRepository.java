package com.siti.timesheet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.siti.timesheet.model.Status;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel="status", path="status")
public interface StatusRepository extends JpaRepository<Status, Integer>{

}
