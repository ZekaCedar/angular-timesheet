package com.siti.timesheet.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="TS_USER")
@Data
public class User {
	
	/**
     * Attribute ID: A001
     * Key: id
     * Description: Unique identifier for the user.
     * Generation: Auto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private int id;

    /**
     * Attribute ID: A002
     * Key: userName
     * Description: User's username. Required field.
     * Constraints: Not null, Max length 255
     */
	/* @NotNull */
	/* @Size(max = 255) */
    @Column(name = "userName", nullable = false, length = 255)
    @JsonProperty("userName")
    private String userName;

    /**
     * Attribute ID: A003
     * Key: userEmail
     * Description: User's email address. Must be valid format.
     * Constraints: Not null, Email format, Max length 255
     */
	/* @NotNull */
	/* @Email */
	/* @Size(max = 255) */
    @Column(name = "userEmail", nullable = false, length = 255)
    @JsonProperty("userEmail")
    private String userEmail;

    /**
     * Attribute ID: A004
     * Key: createUser
     * Description: Username of the user who created this record.
     * Constraints: Optional, Max length 255
     */
    @Column(name = "createUser", length = 255)
    @JsonProperty("createUser")
    private String createUser;

    /**
     * Attribute ID: A005
     * Key: createDate
     * Description: Timestamp of when this record was created.
     * Type: Timestamp
     */
	/* @Temporal(TemporalType.TIMESTAMP) */
    @Column(name = "createDate")
    @JsonProperty("createDate")
    private Date createDate;

    /**
     * Attribute ID: A006
     * Key: modifyUser
     * Description: Username of the user who last modified this record.
     * Constraints: Optional, Max length 255
     */
    @Column(name = "modifyUser", length = 255)
    @JsonProperty("modifyUser")
    private String modifyUser;

    /**
     * Attribute ID: A007
     * Key: modifyDate
     * Description: Timestamp of the last modification of this record.
     * Type: Timestamp
     */
	/* @Temporal(TemporalType.TIMESTAMP) */
    @Column(name = "modify_date")
    @JsonProperty("modifyDate")
    private Date modifyDate;

}
