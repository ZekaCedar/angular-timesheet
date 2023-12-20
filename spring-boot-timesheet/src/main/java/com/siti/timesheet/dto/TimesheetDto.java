package com.siti.timesheet.dto;

import java.sql.Date;

import com.siti.timesheet.model.Timesheet;

import lombok.Data;

@Data
public class TimesheetDto {
	
	//private Timesheet timesheet;
	private int id;
	private String projectName;
	private String taskName;
	private String assignUser;
	private Date taskStartDt;
	private Date taskEndDt;
	private String taskStatus;
	private int userId;
	private int statusId;
	private String createUser;
	private Date createDate;
	private String modifyUser;
	private Date modifyDate;
}
