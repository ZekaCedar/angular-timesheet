package com.siti.timesheet.service;

import java.util.Optional;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.siti.timesheet.dao.TimesheetRepository;
import com.siti.timesheet.dto.TimesheetDto;
import com.siti.timesheet.dto.TimesheetResponse;
import com.siti.timesheet.exception.TimesheetException;
import com.siti.timesheet.model.Timesheet;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class TimesheetServiceImpl implements TimesheetService{
	
	@Autowired
	TimesheetRepository TimesheetRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private static final Logger log = LoggerFactory.getLogger(TimesheetServiceImpl.class);
	
	@PostConstruct
	private void configureModelMapper() {
		// Configure the model mapper for TimesheetDto to Timesheet
		modelMapper.createTypeMap(TimesheetDto.class, Timesheet.class).addMappings(mapper -> {
			mapper.skip(TimesheetDto::getId, Timesheet::setId);
			mapper.skip(TimesheetDto::getCreateUser, Timesheet::setCreateUser);
			mapper.skip(TimesheetDto::getCreateDate, Timesheet::setCreateDate);
			mapper.skip(TimesheetDto::getModifyUser, Timesheet::setModifyUser);
			mapper.skip(TimesheetDto::getModifyDate, Timesheet::setModifyDate);
//				mapper.skip(TimesheetDto::getId,Timesheet::setId);
//				mapper.map(TimesheetDto::getStatusId, Timesheet::setStatusId);
//				mapper.map(TimesheetDto::getUserId, Timesheet::setUserId);
		});
	}
	

	/**
     * Creates a new timesheet based on the provided ITimesheet DTO.
     * This method maps the ITimesheet DTO to the Timesheet entity,
     * persists it to the database, and returns a response indicating the outcome.
     *
     * @param timesheetDto The DTO containing timesheet data.
     * @return ITimesheetResponse indicating the result of the operation.
     * @throws TimesheetException if there is an error during the process.
     */
	@Override
	@Transactional
	public TimesheetResponse createTimesheet(TimesheetDto timesheetDto) {
		
		try {
			// use ModelMapper to map from TimesheetDTO to Timesheet
			log.debug("Mapping timesheetDto to Timesheet entity");
			
			Timesheet timesheet = modelMapper.map(timesheetDto, Timesheet.class);
			
			// save to the database
			log.info("Saving timesheet to the database");
			TimesheetRepository.save(timesheet);
			
			// return a response
			log.info("Timesheet created successfully");
			return new TimesheetResponse("Timesheet successfully created");
		} catch (DataAccessException e ) {
            // Handle both DataAccessException and IOException here
            log.error("Exception occurred during creating timesheet", e);
            throw new TimesheetException("Error Message", e);
        }
		
		
	}
	
	/*
	 * This method deletes a timesheet from the database based on the provided ID.
	 * It logs the process of deletion and returns a response indicating the outcome.
	 * In case of any DataAccessException, it logs the error and throws a custom TimesheetException.
	 * 
	 * @param id The ID of the timesheet to be deleted.
	 * @return TimesheetResponse indicating the result of the operation.
	 */
	@Override
	@Transactional
	public TimesheetResponse deleteTimesheet(Integer id) {
		
		try {
			// delete from the database
			log.info("deleting timesheet from the database");
			TimesheetRepository.deleteById(id);
			
			// return a response
			log.info("Timesheet deleted successfully");
			return new TimesheetResponse("Timesheet successfully deleted");
		} catch (DataAccessException e) {
			// Handle both DataAccessException and IOException here
			log.error("Exception occurred during deleting timesheet", e);
			throw new TimesheetException("Error Message", e);
		}
	}

	@Override
	@Transactional
	public TimesheetResponse updateTimesheet(Integer Id, TimesheetDto timesheetDto) {
		String status = "";
		
		try {
			// find existing timesheet
			log.info("updating existing timesheet from the database with id of " + Id);
			Optional<Timesheet> Existingtimesheet = TimesheetRepository.findById(Id);

			if (Existingtimesheet.isPresent()) {
				Timesheet timesheet = Existingtimesheet.get();

				// Map from timesheetDto to timesheet
				modelMapper.map(timesheetDto, timesheet);

				// Save the updated timesheet
				log.info("updating existing timesheet from the database");
//				Timesheet updatedTimesheet = TimesheetRepository.save(timesheet);
				TimesheetRepository.save(timesheet);

				// return a response
				log.info("Timesheet updated successfully");
				status = "Timesheet successfully updated";
			}else {
				throw new Exception("Existingtimesheet is not present");
			}
			
			return new TimesheetResponse(status);
			
		} catch (Exception e) {
			// Handle both DataAccessException and IOException here
			log.error("Exception occurred during updating timesheet", e);
			throw new TimesheetException("Error Message", e);
		}
	}

}
