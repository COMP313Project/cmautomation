package com.cmautomation.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmautomation.spring.entity.Application;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Application> getApplications() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Application> theQuery = currentSession.createQuery("from Application", Application.class);

		// execute query and get result list
		List<Application> applications = theQuery.getResultList();

		// return the results
		return applications;
	}
	//saves a new entry into database
	@Override
	public void saveApplication(Application application) {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(application);
		
	}
	// gets the application from database and pre-populate the form
	@Override
	public Application getApplication(int appId) {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		Application theApplication=currentSession.get(Application.class, appId);
		
		return theApplication;
	}
	//deletes an application from database
	@Override
	public void deleteApplication(int appId) {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		//delete query
		//this appId should be same as injected parameter
		Query deleteQuery=currentSession.createQuery("delete from Application where application_Id=:appId");		
		
		deleteQuery.setParameter("appId", appId);
		
		deleteQuery.executeUpdate();
		
	}

}
