package com.cmautomation.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmautomation.spring.dao.DeploymentPlanDAO;
import com.cmautomation.spring.entity.DeploymentPlan;


/*
 * This class serves as service layer of MVC and handles business logic of the application
 * and communicate to DeploymentPlan controller
 * 
 * 
 * */
@Service
public class DeploymentPlanServiceImpl implements DeploymentPlanService {

	@Autowired 
	private DeploymentPlanDAO deploymentPlanDAO;
	
	@Override
	@Transactional
	public List<DeploymentPlan> getDeploymentPlanList() {
		
		return deploymentPlanDAO.getDeploymentPlanList();
	}

	@Override
	@Transactional
	public void saveDeploymentPlan(DeploymentPlan theDeploymentPlan) {
		
		deploymentPlanDAO.saveDeploymentPlan(theDeploymentPlan);
	}
	
	@Override
	@Transactional
	public DeploymentPlan getDeploymentPlan(int deployment_Id)
	{
		return deploymentPlanDAO.getDeploymentPlan(deployment_Id);
	}

	@Override
	@Transactional
	public void deleteDeploymentPlan(int deployment_Id)
	{
		deploymentPlanDAO.deleteDeploymentPlan(deployment_Id);
	}

	@Override
	@Transactional
	public List<DeploymentPlan> searchDeploymentPlan(String theSearchName)
	{
		return deploymentPlanDAO.searchDeploymentPlan(theSearchName);
	}
}
