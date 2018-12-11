package com.cmautomation.spring.entity;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//=======
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;


@NamedNativeQueries({
	@NamedNativeQuery(
	name = "getNotDeployedDefectListSQL",	
	query = "select * from defect_fix_detail df where df.defect_Id not in(select defect_Id from deployement_defectlist)",
        resultClass = DefectFixDetail.class
	)
})

@Entity
@Table(name="defect_fix_detail")
public class DefectFixDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="defect_Id")
	private Integer defect_Id;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="application_Id")
	private Application application;
	
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="vendor_Id")
	private Vendor vendor;
	
	@NotNull(message="required")
	@Column(name="defectCreationDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date defectCreationDate;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name="description")
	@Lob
	private String description;
	
	@Column(name="dependentDefect_Id")
	private Integer dependentDefect_Id;
	
	@Column(name="status")
	private int status;	
	
	@Column(name="fixRecieveDate")
	@Temporal(TemporalType.DATE)
	@FutureOrPresent
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fixRecieveDate;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name="impactedComponent")
	private String impactedComponent;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name="deploymentPackageLocation")
	private String deploymentPackageLocation;/*--*/
	
	@Column(name="isTestCaseprovided")
	private int isTestCaseprovided;
	
	@Column(name="isDeploymentInstructionProvided")
	private int isDeploymentInstructionProvided;	
	
	@Column(name="reviewDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reviewDate;	
		
		
	
	public DefectFixDetail() {
		
	}	
	
	@Transient
	private String viewStatus;
	
	public String  getViewStatus() {
		return viewStatus;
	}
	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}
	
	@Transient
	private Boolean usedInDdeploymentPlan;
	
	public Boolean  getIsUsedInDdeploymentPlan() {
		return usedInDdeploymentPlan;
	}
	public void setIsUsedInDdeploymentPlan(Boolean usedInDdeploymentPlan) {
		this.usedInDdeploymentPlan = usedInDdeploymentPlan;
	}
	
	public Integer getDefect_Id() {
		return defect_Id;
	}

	public void setDefect_Id(Integer defect_Id) {
		this.defect_Id = defect_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Date getDefectCreationDate() {
		return defectCreationDate;
	}

	public void setDefectCreationDate(Date defectCreationDate) {
		this.defectCreationDate = defectCreationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDependentDefect_Id() {
		return dependentDefect_Id;
	}

	public void setDependentDefect_Id(Integer dependentDefect_Id) {
		this.dependentDefect_Id = dependentDefect_Id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getFixRecieveDate() {
		return fixRecieveDate;
	}

	public void setFixRecieveDate(Date fixRecieveDate) {
		this.fixRecieveDate = fixRecieveDate;
	}

	public String getImpactedComponent() {
		return impactedComponent;
	}

	public void setImpactedComponent(String impactedComponent) {
		this.impactedComponent = impactedComponent;
	}

	public String getDeploymentPackageLocation() {
		return deploymentPackageLocation;
	}

	public void setDeploymentPackageLocation(String deploymentPackageLocation) {
		this.deploymentPackageLocation = deploymentPackageLocation;
	}

	public int getIsTestCaseprovided() {
		return isTestCaseprovided;
	}

	public void setIsTestCaseprovided(int isTestCaseprovided) {
		this.isTestCaseprovided = isTestCaseprovided;
	}

	public int getIsDeploymentInstructionProvided() {
		return isDeploymentInstructionProvided;
	}

	public void setIsDeploymentInstructionProvided(int isDeploymentInstructionProvided) {
		this.isDeploymentInstructionProvided = isDeploymentInstructionProvided;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	@Override
	public String toString() {
		return "DefectFixDetail [defect_Id=" + defect_Id + ", title=" + title + ", application=" + application
				+ ", vendor=" + vendor + ", defectCreationDate=" + defectCreationDate + ", description=" + description
				+ ", dependentDefect_Id=" + dependentDefect_Id + ", status=" + status + ", fixRecieveDate="
				+ fixRecieveDate + ", impactedComponent=" + impactedComponent + ", deploymentPackageLocation="
				+ deploymentPackageLocation + ", isTestCaseprovided=" + isTestCaseprovided
				+ ", isDeploymentInstructionProvided=" + isDeploymentInstructionProvided + ", reviewDate=" + reviewDate
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((application == null) ? 0 : application.hashCode());
		result = prime * result + ((defectCreationDate == null) ? 0 : defectCreationDate.hashCode());
		result = prime * result + ((defect_Id == null) ? 0 : defect_Id.hashCode());
		result = prime * result + ((dependentDefect_Id == null) ? 0 : dependentDefect_Id.hashCode());
		result = prime * result + ((deploymentPackageLocation == null) ? 0 : deploymentPackageLocation.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fixRecieveDate == null) ? 0 : fixRecieveDate.hashCode());
		result = prime * result + ((impactedComponent == null) ? 0 : impactedComponent.hashCode());
		result = prime * result + isDeploymentInstructionProvided;
		result = prime * result + isTestCaseprovided;
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
		result = prime * result + status;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		DefectFixDetail other = (DefectFixDetail) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (defectCreationDate == null) {
			if (other.defectCreationDate != null)
				return false;
		} else if (!defectCreationDate.equals(other.defectCreationDate))
			return false;
		if (defect_Id == null) {
			if (other.defect_Id != null)
				return false;
		} else if (!defect_Id.equals(other.defect_Id))
			return false;
		if (dependentDefect_Id == null) {
			if (other.dependentDefect_Id != null)
				return false;
		} else if (!dependentDefect_Id.equals(other.dependentDefect_Id))
			return false;
		if (deploymentPackageLocation == null) {
			if (other.deploymentPackageLocation != null)
				return false;
		} else if (!deploymentPackageLocation.equals(other.deploymentPackageLocation))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fixRecieveDate == null) {
			if (other.fixRecieveDate != null)
				return false;
		} else if (!fixRecieveDate.equals(other.fixRecieveDate))
			return false;
		if (impactedComponent == null) {
			if (other.impactedComponent != null)
				return false;
		} else if (!impactedComponent.equals(other.impactedComponent))
			return false;
		if (isDeploymentInstructionProvided != other.isDeploymentInstructionProvided)
			return false;
		if (isTestCaseprovided != other.isTestCaseprovided)
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}	
}
