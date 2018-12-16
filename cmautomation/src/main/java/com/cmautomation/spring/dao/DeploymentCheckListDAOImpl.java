package com.cmautomation.spring.dao;

import java.util.List;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmautomation.spring.entity.DefectFixDetail;
import com.cmautomation.spring.entity.DeploymentCheckList;
import com.cmautomation.spring.entity.DeploymentEnvironment;
import com.cmautomation.spring.entity.DeploymentPlan;

/*
 * This Data access layer inherits from DeploymentCheckListDAO, and communicates with Database with 
 * CRUD operation for Application for  TSA User 
 * 
 * */

@Repository
public class DeploymentCheckListDAOImpl implements DeploymentCheckListDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DeploymentEnvironmentDAO deploymentEnvironmentDAO;

	@Override
	public List<DeploymentCheckList> getDeploymentCheckList() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<DeploymentCheckList> theQuery = currentSession.createQuery("from DeploymentCheckList",
				DeploymentCheckList.class);

		// execute query and get result list
		List<DeploymentCheckList> theDeploymentCheckList = theQuery.getResultList();

		theDeploymentCheckList = assignViewStatus(theDeploymentCheckList);

		return theDeploymentCheckList;
	}

	// Assign view property for status
	private List<DeploymentCheckList> assignViewStatus(List<DeploymentCheckList> deploymentCheckLists) {
		for (int i = 0; i < deploymentCheckLists.size(); i++) {
			deploymentCheckLists.set(i, assignViewStatus(deploymentCheckLists.get(i)));
		}

		return deploymentCheckLists;
	}

	// Assign view property for status
	private DeploymentCheckList assignViewStatus(DeploymentCheckList deploymentCheckList) {
		if (deploymentCheckList.getIsPackageDeployed() == 'y') {
			deploymentCheckList.setViewIsPackageDeployed("DONE");
		} else if (deploymentCheckList.getIsPackageDeployed() == 'n') {
			deploymentCheckList.setViewIsPackageDeployed("PENDING");
		}

		return deploymentCheckList;
	}

	// save
	@Override
	public void saveDeploymentCheckList(DeploymentCheckList theDeploymentCheckList) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Transaction tx = null;
		try {

			DeploymentEnvironment environment = deploymentEnvironmentDAO
					.getDeploymentEnvironment(theDeploymentCheckList.getDeploymentEnvironment().getEnvironment_Id());

			tx = currentSession.getTransaction();
			currentSession.saveOrUpdate(theDeploymentCheckList);

			// create a query
			Query theQuery = currentSession.getNamedNativeQuery("updateDefectStatusSQL");
			int defectStatus = getDefectStatus(environment.getEnvironmentName());
			theQuery.setParameter("Status", defectStatus);
			theQuery.setParameter("deployement_Id", theDeploymentCheckList.getDeploymentPlan().getDeployment_Id());

			// execute query and get result list
			theQuery.executeUpdate();

			// tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			// session.close();
		}
	}

	// Assign defect status depending on the deployement environment
	private int getDefectStatus(String deploymentEnvironment) {
		int defectStatus = 0;

		if (deploymentEnvironment.equals("SDF")) {
			defectStatus = 3;
		} else if (deploymentEnvironment.equals("DEV")) {
			defectStatus = 4;
		} else if (deploymentEnvironment.equals("IST1")) {
			defectStatus = 5;
		} else if (deploymentEnvironment.equals("IST2")) {
			defectStatus = 6;
		} else if (deploymentEnvironment.equals("PROD")) {
			defectStatus = 7;
		}

		return defectStatus;
	}

	// update
	@Override
	public DeploymentCheckList getDeploymentCheckListDetail(int dpCkeck_Id) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		DeploymentCheckList theDeploymentCheckList = currentSession.get(DeploymentCheckList.class, dpCkeck_Id);

		theDeploymentCheckList = assignViewStatus(theDeploymentCheckList);

		return theDeploymentCheckList;
	}

	// delete
	@Override
	public void deleteDeploymentCheckList(int dpCkeck_Id) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// delete query
		Query deleteQuery = currentSession
				.createQuery("delete from DeploymentCheckList where deploymentChecklist_Id=:dpCkeck_Id");

		deleteQuery.setParameter("dpCkeck_Id", dpCkeck_Id);

		deleteQuery.executeUpdate();
	}

}
