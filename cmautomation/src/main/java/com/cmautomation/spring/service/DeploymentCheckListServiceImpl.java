package com.cmautomation.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmautomation.spring.dao.DeploymentCheckListDAO;
import com.cmautomation.spring.entity.DeploymentCheckList;

@Service
public class DeploymentCheckListServiceImpl implements DeploymentCheckListService {

	@Autowired
	private DeploymentCheckListDAO deploymentCheckListDAO;
	
	@Override
	@Transactional
	public List<DeploymentCheckList> getDeploymentCheckList() {
		
		
		return deploymentCheckListDAO.getDeploymentCheckList();
	}
	
	//save
	@Override
	@Transactional
	public void saveDeploymentCheckList(DeploymentCheckList theDeploymentCheckList) {
		
		
		deploymentCheckListDAO.saveDeploymentCheckList(theDeploymentCheckList);
	}
	
	//update
	@Override
	@Transactional
	public DeploymentCheckList getDeploymentCheckListDetail(int dpCkeck_Id) {
		
		return deploymentCheckListDAO.getDeploymentCheckListDetail(dpCkeck_Id);
	}


	//delete
	@Override
	@Transactional
	public void deleteDeploymentCheckList(int dpCkeck_Id) {
		
		deploymentCheckListDAO.deleteDeploymentCheckList(dpCkeck_Id);
		
		
	}

	
	

}
