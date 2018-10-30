package com.cmautomation.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmautomation.spring.entity.Application;
import com.cmautomation.spring.entity.DeploymentEnvironment;

@Repository
public class DeploymentEnvironmentDAOImpl implements DeploymentEnvironmentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DeploymentEnvironment> getEnvironmentList() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<DeploymentEnvironment> theQuery = currentSession.createQuery("from DeploymentEnvironment",
				DeploymentEnvironment.class);

		// execute query and get result list
		List<DeploymentEnvironment> environments = theQuery.getResultList();

		// return the results
		return environments;
	}

	@Override
	public void saveEnvironment(DeploymentEnvironment deploymentEnvironment) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(deploymentEnvironment);

	}

	// update
	@Override
	public DeploymentEnvironment getDeploymentEnvironment(int envId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		DeploymentEnvironment deploymentEnvironment = currentSession.get(DeploymentEnvironment.class, envId);

		return deploymentEnvironment;
	}

	// delete
	@Override
	public void deleteEnvironment(int envId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete query
		//this envId should be same as injected parameter
		Query deleteQuery=currentSession.createQuery("delete from DeploymentEnvironment where environment_Id=:envId");
		
		deleteQuery.setParameter("envId", envId);
		
		deleteQuery.executeUpdate();
		

	}

}
