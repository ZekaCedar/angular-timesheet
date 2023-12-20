package com.siti.timesheet.service;

import com.siti.timesheet.dto.TimesheetDto;
import com.siti.timesheet.dto.TimesheetResponse;
import com.siti.timesheet.model.Timesheet;

public interface TimesheetService {
	
	TimesheetResponse createTimesheet(TimesheetDto timesheet);
	
	TimesheetResponse deleteTimesheet(Integer id);
	
	TimesheetResponse updateTimesheet(Integer id, TimesheetDto timesheet);

}
