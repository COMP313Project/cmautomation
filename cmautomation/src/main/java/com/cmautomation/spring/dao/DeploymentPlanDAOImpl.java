package com.cmautomation.spring.dao;

import java.util.List;

import javax.persistence.Convert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmautomation.spring.entity.DefectFixDetail;
import com.cmautomation.spring.entity.DeploymentPlan;

@Repository
public class DeploymentPlanDAOImpl extends Exception implements DeploymentPlanDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<DeploymentPlan> getDeploymentPlanList() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<DeploymentPlan> theQuery = currentSession.createQuery("from DeploymentPlan", DeploymentPlan.class);

		// execute query and get result list
		List<DeploymentPlan> deploymentPlanList = theQuery.getResultList();
		
		return deploymentPlanList;
	}

	@Override
	public void saveDeploymentPlan(DeploymentPlan theDeploymentPlan) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theDeploymentPlan);		   
	}
	
	//GET DeploymentPlan
	@Override
	public  DeploymentPlan getDeploymentPlan(int deployment_Id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		DeploymentPlan theDeploymentPlan = currentSession.get(DeploymentPlan.class, deployment_Id);

		return theDeploymentPlan;
	}

	// delete DeploymentPlan
	@Override
	public void deleteDeploymentPlan(int deployment_Id) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// delete query
		Query deleteQuery = currentSession.createQuery("delete from DeploymentPlan where deployment_Id=:deployment_Id");

		deleteQuery.setParameter("deployment_Id", deployment_Id);

		deleteQuery.executeUpdate();

	}

	
	//search specific plan
	@Override
	public List<DeploymentPlan> searchDeploymentPlan(String theSearchName) {

		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// search query
		Query searchQuery = null;
		// search by name if theSearchName is not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {
		// search the defect name--case insensetive
		searchQuery = currentSession.createQuery("from DeploymentPlan where lower(title) like:theName",DeploymentPlan.class);
		searchQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			searchQuery = currentSession.createQuery("from DeploymentPlan", DeploymentPlan.class);
		}
		// execute query and get result list
		List<DeploymentPlan> listDeploymentPlan = searchQuery.getResultList();

		return listDeploymentPlan;
	}
	
	public Integer getDeploymentPlanCountByDefectId(int defect_Id)
	{
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query theQuery = currentSession.getNamedNativeQuery("getDeploymentPlanCountByDefectIdSQL");
		
		theQuery.setParameter("defect_Id", defect_Id);

		// execute query and get result list
		List<DeploymentPlan> listDeploymentPlan = theQuery.getResultList();
		
				
		return listDeploymentPlan.size();
	}

}
