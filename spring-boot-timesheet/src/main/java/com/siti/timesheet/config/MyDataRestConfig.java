package com.siti.timesheet.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.siti.timesheet.model.Status;
import com.siti.timesheet.model.Timesheet;
import com.siti.timesheet.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{
	
	private EntityManager entityManager;

	@Autowired
	public void setEntityManager(EntityManager theEntityManager) {
	    this.entityManager = theEntityManager;
	}


	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		// TODO Auto-generated method stub
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
		
		// NOTE disable HTTP methods for Product: PUT, POST and DELETE
		config.getExposureConfiguration()
				.forDomainType(User.class)
				.withItemExposure((metData, httpMethods) -> httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		config.getExposureConfiguration()
				.forDomainType(Timesheet.class)
				.withItemExposure((metData, httpMethods) -> httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
				
		config.getExposureConfiguration()
				.forDomainType(Status.class)
				.withItemExposure((metData, httpMethods) -> httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
		// NOTE call an internal helper method
		exposeIds(config);
		
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		// TODO Auto-generated method stub
		
		// NOTE expose entity ids
		
		// NOTE get list of all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		// NOTE create an array of entity types
		List<Class> entityClasses = new ArrayList<>();
		
		// NOTE get the entity types for the entities
		for (EntityType tempEntityType: entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// NOTE expose the entity ids for the array of entity/domain types
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}
	
	

}
