package com.cmautomation.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmautomation.spring.dao.DeploymentEnvironmentDAO;
import com.cmautomation.spring.entity.DeploymentEnvironment;

/*
 * This class serves as service layer of MVC and handles business logic of the application
 * and communicate to DeploymentEnvironment controller
 * 
 * 
 * */
@Service
public class DeploymentEnvironmentServiceImpl implements DeploymentEnvironmentService {

	@Autowired
	private DeploymentEnvironmentDAO deploymentEnvironmentDAO;
	
	@Override
	@Transactional
	public List<DeploymentEnvironment> getEnvironmentList() {
		
		return deploymentEnvironmentDAO.getEnvironmentList();
	}

	@Override
	@Transactional
	public void saveEnvironment(DeploymentEnvironment deploymentEnvironment) {
		
		deploymentEnvironmentDAO.saveEnvironment(deploymentEnvironment);
	}

	@Override
	@Transactional
	public DeploymentEnvironment getDeploymentEnvironment(int envId) {
		
		return deploymentEnvironmentDAO.getDeploymentEnvironment(envId);
	}

	@Override
	@Transactional
	public void deleteEnvironment(int envId) {
		
		deploymentEnvironmentDAO.deleteEnvironment(envId);
	}

}
