package com.cmautomation.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/*
 * This is the entity class for the application which maps application table in the database
 * */

@Entity
@Table(name = "applications")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_Id")
	private int application_Id;

	@NotNull(message="required")
	@Size(min=1,message="required")
	@Pattern(regexp="^[a-zA-Z0-9 ]+$", message="only alphanumaric with no space")
	@Column(name = "applicationName")
	private String applicationName;	
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name = "description")
	private String description;
	
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="vendor_Id")
	private Vendor vendor;
	
	@OneToMany(mappedBy="application",cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DeploymentPlan> deploymentPlan;
	
	@OneToMany(mappedBy="application",cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DefectFixDetail> defectFixDetail;
	
		
	public Application() {

	}
	//convenience method
	public void addDeploymentPlan(DeploymentPlan theDeploymentPlan) {
		if(deploymentPlan==null) {
			deploymentPlan=new ArrayList<>();
			}
		deploymentPlan.add(theDeploymentPlan);
	}
	//convenience method
	public void addDefectFixDetail(DefectFixDetail theDefectFixDetail) {
		if(defectFixDetail==null) {
			defectFixDetail=new ArrayList<>();
			}
		defectFixDetail.add(theDefectFixDetail);
	}
	
	public Application(int application_Id, String applicationName, List<DeploymentPlan> deploymentPlan,
			List<DefectFixDetail> defectFixDetail) {
		super();
		this.application_Id = application_Id;
		this.applicationName = applicationName;
		this.deploymentPlan = deploymentPlan;
		this.defectFixDetail = defectFixDetail;
	}
	public int getApplication_Id() {
		return application_Id;
	}
	public void setApplication_Id(int application_Id) {
		this.application_Id = application_Id;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	
	public List<DefectFixDetail> getDefectFixDetail() {
		return defectFixDetail;
	}
	public void setDefectFixDetail(List<DefectFixDetail> defectFixDetail) {
		this.defectFixDetail = defectFixDetail;
	}
	public List<DeploymentPlan> getDeploymentPlan() {
		return deploymentPlan;
	}
	public void setDeploymentPlan(List<DeploymentPlan> deploymentPlan) {
		this.deploymentPlan = deploymentPlan;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result + application_Id;
		result = prime * result + ((defectFixDetail == null) ? 0 : defectFixDetail.hashCode());
		result = prime * result + ((deploymentPlan == null) ? 0 : deploymentPlan.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
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
		Application other = (Application) obj;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		if (application_Id != other.application_Id)
			return false;
		if (defectFixDetail == null) {
			if (other.defectFixDetail != null)
				return false;
		} else if (!defectFixDetail.equals(other.defectFixDetail))
			return false;
		if (deploymentPlan == null) {
			if (other.deploymentPlan != null)
				return false;
		} else if (!deploymentPlan.equals(other.deploymentPlan))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Application [application_Id=" + application_Id + ", applicationName=" + applicationName + ", Description "+description+" vendor=" +vendor+"]";
	}

}
