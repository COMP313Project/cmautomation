package com.cmautomation.spring.service;

import java.util.List;

import com.cmautomation.spring.entity.DeploymentPlan;

public interface DeploymentPlanService {

	public List<DeploymentPlan> getDeploymentPlanList();

	public void saveDeploymentPlan(DeploymentPlan theDeploymentPlan);
	
	public DeploymentPlan getDeploymentPlan(int deployment_Id);

	public void deleteDeploymentPlan(int deployment_Id);

	public List<DeploymentPlan> searchDeploymentPlan(String theSearchName);

}
