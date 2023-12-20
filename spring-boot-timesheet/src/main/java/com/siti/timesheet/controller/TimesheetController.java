package com.siti.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siti.timesheet.dto.TimesheetDto;
import com.siti.timesheet.dto.TimesheetResponse;
import com.siti.timesheet.model.Timesheet;
import com.siti.timesheet.service.TimesheetService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/1.0/timesheet")
public class TimesheetController {
	
	@Autowired
	TimesheetService TimesheetService;
	
	@PostMapping("/create")
	public TimesheetResponse createTimesheet(@RequestBody TimesheetDto timesheet) {
		
		TimesheetResponse timesheetResponse = TimesheetService.createTimesheet(timesheet);
		
		return timesheetResponse;
		
	}
	
	@DeleteMapping("/delete/{Id}")
	public TimesheetResponse deleteTimesheet(@PathVariable Integer Id) {
		
		TimesheetResponse timesheetResponse = TimesheetService.deleteTimesheet(Id);
		
		return timesheetResponse;
		
	}
	
	@PostMapping("/edit/{Id}")
	public TimesheetResponse updateTimesheet(@PathVariable Integer Id, @RequestBody TimesheetDto timesheet) {
		
		TimesheetResponse timesheetResponse = TimesheetService.updateTimesheet(Id ,timesheet);
		
		return timesheetResponse;
	}
	
}
