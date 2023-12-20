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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TS_STATUS")
@Data
public class Status {
	
	/**
     * Attribute ID: A019
     * Key: id
     * Description: Unique identifier for the status.
     * Generation: Auto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private int id;

    /**
     * Attribute ID: A020
     * Key: statusGrp
     * Description: Status Group. Required field.
     * Constraints: Not null, Max length 255
     */
	/* @NotNull */
	/* @Size(max = 255) */
    @Column(name = "statusGrp", nullable = false, length = 255)
    @JsonProperty("statusGrp")
    private String statusGrp;

    /**
     * Attribute ID: A021
     * Key: statusName
     * Description: Status Name. Must be valid format.
     * Constraints: Not null, Max length 255
     */
	/* @NotNull */
	/* @Size(max = 255) */
    @Column(name = "statusName", nullable = false, length = 255)
    @JsonProperty("statusName")
    private String statusName;

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
    @Column(name = "modifyDate")
    @JsonProperty("modifyDate")
    private Date modifyDate;

}
