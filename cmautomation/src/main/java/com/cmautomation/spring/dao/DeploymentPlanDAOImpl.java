package com.cmautomation.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmautomation.spring.entity.DefectFixDetail;
import com.cmautomation.spring.entity.DeploymentPlan;

@Repository
public class DeploymentPlanDAOImpl implements DeploymentPlanDAO {

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
	
	@Override
	public  DeploymentPlan getDeploymentPlan(int deployment_Id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		DeploymentPlan theDeploymentPlan = currentSession.get(DeploymentPlan.class, deployment_Id);

		return theDeploymentPlan;
	}

	// delete
	@Override
	public void deleteDeploymentPlan(int deployment_Id) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// delete query
		Query deleteQuery = currentSession.createQuery("delete from DeploymentPlan where deployment_Id=:deployment_Id");

		deleteQuery.setParameter("deployment_Id", deployment_Id);

		deleteQuery.executeUpdate();

	}

	
	//search
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

}
