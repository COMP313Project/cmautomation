package com.cmautomation.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.IdClass;
//@Embeddable

public class QACompositeKeyId implements Serializable {

	/*
	 * private Integer deployment_Id;
	 * 
	 * private Integer environment_Id;
	 * 
	 */

	private DeploymentPlan deploymentPlan;

	private DeploymentEnvironment deploymentEnvironment;

	public QACompositeKeyId() {

	}

	public QACompositeKeyId(DeploymentPlan deploymentPlan, DeploymentEnvironment deploymentEnvironment) {
		super();
		this.deploymentPlan = deploymentPlan;
		this.deploymentEnvironment = deploymentEnvironment;
	}

	public DeploymentPlan getDeploymentPlan() {
		return deploymentPlan;
	}

	public void setDeploymentPlan(DeploymentPlan deploymentPlan) {
		this.deploymentPlan = deploymentPlan;
	}

	public DeploymentEnvironment getDeploymentEnvironment() {
		return deploymentEnvironment;
	}

	public void setDeploymentEnvironment(DeploymentEnvironment deploymentEnvironment) {
		this.deploymentEnvironment = deploymentEnvironment;
	}
	

}
