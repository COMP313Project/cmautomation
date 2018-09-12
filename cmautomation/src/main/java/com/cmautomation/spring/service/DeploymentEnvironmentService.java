package com.cmautomation.spring.service;

import java.util.List;

import com.cmautomation.spring.entity.DeploymentEnvironment;

public interface DeploymentEnvironmentService {

	public List<DeploymentEnvironment> getEnvironmentList();

	public void saveEnvironment(DeploymentEnvironment deploymentEnvironment);

	public DeploymentEnvironment getDeploymentEnvironment(int envId);

	public void deleteEnvironment(int envId);

}
