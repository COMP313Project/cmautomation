package com.cmautomation.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmautomation.spring.dao.ApplicationDAO;
import com.cmautomation.spring.entity.Application;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationDAO applicationDAO;
	
	// get the list of applications
	@Override
	@Transactional
	public List<Application> getApplications() {
		
		return applicationDAO.getApplications();
	}

	// saves the new application record to the database
	@Override
	@Transactional
	public void saveApplication(Application application) {
		
		applicationDAO.saveApplication(application);
		
	}

	//get back the application back to the app-form page for update
	@Override
	@Transactional
	public Application getApplication(int appId) {
		
		 return applicationDAO.getApplication(appId);
	}

	// delete the application
	@Override
	@Transactional
	public void deleteApplication(int appId) {
		
		applicationDAO.deleteApplication(appId);
		
	}

}
