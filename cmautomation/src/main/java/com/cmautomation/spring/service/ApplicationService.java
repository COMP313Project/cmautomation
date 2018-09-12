/**
 * 
 */
package com.cmautomation.spring.service;

import java.util.List;

import com.cmautomation.spring.entity.Application;

/**
 * @author devdas
 *
 */
public interface ApplicationService {
	
	public List<Application> getApplications();

	public void saveApplication(Application application);

	public Application getApplication(int appId);

	public void deleteApplication(int appId);

}
