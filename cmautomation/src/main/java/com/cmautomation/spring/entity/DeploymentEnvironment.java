package com.cmautomation.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/*
 * This is the entity class for the DeploymentEnvironment which maps DeploymentEnvironment table in the database
 * */
@Entity
@Table(name="deployment_environments")
public class DeploymentEnvironment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="environment_Id")
	private Integer environment_Id;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name="environmentName")
	private String environmentName;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name = "description")
	private String description;
	
	public DeploymentEnvironment() {
		
	}

	public DeploymentEnvironment(Integer environment_Id, String environmentName) {
		super();
		this.environment_Id = environment_Id;
		this.environmentName = environmentName;
	}

	public Integer getEnvironment_Id() {
		return environment_Id;
	}

	public void setEnvironment_Id(Integer environment_Id) {
		this.environment_Id = environment_Id;
	}

	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}
	

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((environmentName == null) ? 0 : environmentName.hashCode());
		result = prime * result + ((environment_Id == null) ? 0 : environment_Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeploymentEnvironment other = (DeploymentEnvironment) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (environmentName == null) {
			if (other.environmentName != null)
				return false;
		} else if (!environmentName.equals(other.environmentName))
			return false;
		if (environment_Id == null) {
			if (other.environment_Id != null)
				return false;
		} else if (!environment_Id.equals(other.environment_Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeploymentEnvironment [environment_Id=" + environment_Id + ", environmentName=" + environmentName + ", description "+description+"]";
	}



}
