package com.siti.timesheet.model;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="TS_TIMESHEET")
@Data
public class Timesheet {
	
	/**
     * Attribute ID: A008
     * Key: id
     * Description: Unique identifier for the Timesheet.
     * Generation: Auto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private int id;

    /**
     * Attribute ID: A009
     * Key: projectName
     * Description: User's project Name. Required field.
     * Constraints: Not null, Max length 255
     */
	/* @NotNull */
	/* @Size(max = 255) */
    @Column(name = "projectName", nullable = false, length = 255)
    @JsonProperty("projectName")
    private String projectName;
    
    /**
     * Attribute ID: A010
     * Key: taskName
     * Description: User's task Name. Required field.
     * Constraints: Not null, Max length 255
     */
	/* @NotNull */
	/* @Size(max = 255) */
    @Column(name = "taskName", nullable = false, length = 255)
    @JsonProperty("taskName")
    private String taskName;

    /**
     * Attribute ID: A011
     * Key: assignUser
     * Description: Assign User. Required field.
     * Constraints: Not null, Max length 255
     */
	/* @NotNull */
	/* @Size(max = 255) */
    @Column(name = "assignUser", nullable = false, length = 255)
    @JsonProperty("assignUser")
    private String assignUser;
    
    /**
     * Attribute ID: A012
     * Key: taskStartDt
     * Description: Timestamp of when this record was created.
     * Type: Timestamp
     */
	/* @Temporal(TemporalType.TIMESTAMP) */
    @Column(name = "taskStartDt")
    @JsonProperty("taskStartDt")
    private Date taskStartDt;
    
    /**
     * Attribute ID: A013
     * Key: taskEndDt
     * Description: Timestamp of when this record was created.
     * Type: Timestamp
     */
	/* @Temporal(TemporalType.TIMESTAMP) */
    @Column(name = "taskEndDt")
    @JsonProperty("taskEndDt")
    private Date taskEndDt;
    
    /**
     * Attribute ID: A014
     * Key: taskStatus
     * Description: Task Status of the user who created this record.
     * Constraints: Optional, Max length 255
     */
    @Column(name = "taskStatus", length = 255)
    @JsonProperty("taskStatus")
    private String taskStatus;
    
    /**
     * Attribute ID: A015
     * Key: userId
     * Description: Unique identifier for the User Assigned.
     * Generation: Auto
     */
    @Column(name = "userId")
    @JsonProperty("userId")
    private int userId;
    
    /**
     * Attribute ID: A015
     * Key: userId
     * Description: Unique identifier for the User Assigned Task status ID.
     * Generation: Auto
     */
    @Column(name = "statusId")
    @JsonProperty("statusId")
    private int statusId;
    
    /**
     * Attribute ID: A014
     * Key: category
     * Description: category of the user who created this record.
     * Constraints: Optional, Max length 255
     */
    @Column(name = "category", length = 255)
    @JsonProperty("category")
    private String category;

    /**
     * Attribute ID: A016
     * Key: createUser
     * Description: Username of the user who created this record.
     * Constraints: Optional, Max length 255
     */
    @Column(name = "createUser", length = 255)
    @JsonProperty("createUser")
    private String createUser;

    /**
     * Attribute ID: A017
     * Key: createDate
     * Description: Timestamp of when this record was created.
     * Type: Timestamp
     */
	/* @Temporal(TemporalType.TIMESTAMP) */
    @Column(name = "createDate")
    @JsonProperty("createDate")
    @CreatedDate
    private Date createDate;

    /**
     * Attribute ID: A018
     * Key: modifyUser
     * Description: Username of the user who last modified this record.
     * Constraints: Optional, Max length 255
     */
    @Column(name = "modifyUser", length = 255)
    @JsonProperty("modifyUser")
    private String modifyUser;

    /**
     * Attribute ID: A019
     * Key: modifyDate
     * Description: Timestamp of the last modification of this record.
     * Type: Timestamp
     */
	/* @Temporal(TemporalType.TIMESTAMP) */
    @Column(name = "modifyDate")
    @JsonProperty("modifyDate")
    private Date modifyDate;

}
