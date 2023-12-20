package com.siti.timesheet.dto;

import lombok.Data;

@Data
//@RequiredArgsConstructor(staticName = "of")
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TimesheetResponse {
	
	private String response;
	
	// Manual constructor
    public TimesheetResponse(String response) {
        this.response = response;
    }
}
