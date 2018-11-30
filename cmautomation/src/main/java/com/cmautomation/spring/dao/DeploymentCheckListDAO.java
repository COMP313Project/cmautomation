package com.cmautomation.spring.dao;

import java.util.List;

import com.cmautomation.spring.entity.DeploymentCheckList;

public interface DeploymentCheckListDAO {

	List<DeploymentCheckList> getDeploymentCheckList();

	public void deleteDeploymentCheckList(int dpCkeck_Id);

	public void saveDeploymentCheckList(DeploymentCheckList theDeploymentCheckList);

	public DeploymentCheckList getDeploymentCheckListDetail(int dpCkeck_Id);

}
