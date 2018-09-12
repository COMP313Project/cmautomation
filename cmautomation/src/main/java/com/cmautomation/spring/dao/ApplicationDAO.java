package com.cmautomation.spring.dao;

import java.util.List;

import com.cmautomation.spring.entity.Application;

public interface ApplicationDAO {

	List<Application> getApplications();

	public void saveApplication(Application application);

	public Application getApplication(int appId);

	public void deleteApplication(int appId);

}
