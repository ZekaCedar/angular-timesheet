package com.siti.timesheet.exception;

public class TimesheetException extends RuntimeException{
	
	/**
	 * Custom exception class for TimesheetService.
	 * Provides more context for service-related exceptions.
	 */
	public TimesheetException(String message, Throwable cause) {
        super(message, cause);
    }

}
