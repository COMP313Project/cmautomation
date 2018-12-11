package com.cmautomation.spring.dao;

import java.util.List;

import com.cmautomation.spring.entity.DeploymentPlan;

public interface DeploymentPlanDAO {

	List<DeploymentPlan> getDeploymentPlanList();

	public void saveDeploymentPlan(DeploymentPlan theDeploymentPlan);

	public DeploymentPlan getDeploymentPlan(int deployment_Id);

	public void deleteDeploymentPlan(int deployment_Id);
	

	public List<DeploymentPlan> searchDeploymentPlan(String theSearchName);
	
	public Integer getDeploymentPlanCountByDefectId(int defect_Id);

}
