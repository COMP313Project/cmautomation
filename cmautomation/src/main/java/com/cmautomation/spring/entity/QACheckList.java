package com.cmautomation.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="qachecklist")
//@IdClass(QACompositeKeyId.class)
public class QACheckList implements Serializable{
	// need to add composite primary keys
	
	//@EmbeddedId
	//private QACompositeKeyId qaCompositeKeyId;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="qachecklist_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer qachecklist_id; 


	
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="deployment_Id", updatable=true)
	private DeploymentPlan deploymentPlan;	
	
		
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="environment_Id", updatable=true )
	private DeploymentEnvironment deploymentEnvironment;
	
	@NotNull(message="required")
	@Column(name="testDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date testDate;
	
	@Column(name="TestStatus")
	private int testStatus;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Lob
	@Column(name="comment")
	private String comment;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name="testedBy")
	private String testedBy;
	
	
	public QACheckList() {
		
	}


	public QACheckList(Integer qachecklist_id, DeploymentPlan deploymentPlan,
			DeploymentEnvironment deploymentEnvironment, Date testDate, int testStatus, String comment,
			String testedBy) {
		super();
		this.qachecklist_id = qachecklist_id;
		this.deploymentPlan = deploymentPlan;
		this.deploymentEnvironment = deploymentEnvironment;
		this.testDate = testDate;
		this.testStatus = testStatus;
		this.comment = comment;
		this.testedBy = testedBy;
	}
	

	
	@Transient
	private String viewStatus;
	
	public String  getViewStatus() {
		return viewStatus;
	}
	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}
	


	public Integer getQachecklist_id() {
		return qachecklist_id;
	}


	public void setQachecklist_id(Integer qachecklist_id) {
		this.qachecklist_id = qachecklist_id;
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


	public Date getTestDate() {
		return testDate;
	}


	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}


	public int getTestStatus() {
		return testStatus;
	}


	public void setTestStatus(int testStatus) {
		this.testStatus = testStatus;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getTestedBy() {
		return testedBy;
	}


	public void setTestedBy(String testedBy) {
		this.testedBy = testedBy;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
