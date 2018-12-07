package com.cmautomation.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmautomation.spring.entity.DefectFixDetail;

/*
 * This Data access layer inherits from DefectFixDetailDAO, and communicates with Database with 
 * CRUD operation for DefectFixDetail for CMA User 
 * 
 * */

@Repository
public class DefectFixDetailDAOImpl implements DefectFixDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DefectFixDetail> getDefectList() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<DefectFixDetail> theQuery = currentSession.createQuery("from DefectFixDetail", DefectFixDetail.class);

		// execute query and get result list
		List<DefectFixDetail> defectFixDetailList = theQuery.getResultList();
		
		defectFixDetailList = assignViewStatus(defectFixDetailList);

		return defectFixDetailList;
	}
	
	public List<DefectFixDetail> getNotDeployedDefectList(){

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<DefectFixDetail> theQuery = currentSession.getNamedSQLQuery("select df from DefectFixDetail df where df.defect_Id not in(select defect_Id from cm_automation.deployement_defectlist)");

		// execute query and get result list
		List<DefectFixDetail> defectFixDetailList = theQuery.getResultList();
		
		DefectFixDetail defect= defectFixDetailList.get(0);
		
		
		return defectFixDetailList;
	}
	
	//Assign view property for status
	private List<DefectFixDetail> assignViewStatus(List<DefectFixDetail> defects)
	{
		for(int i=0; i<defects.size();i++)
		{
			DefectFixDetail defect= assignViewStatus(defects.get(i));
			defects.set(i, assignViewStatus(defects.get(i)));
		}
		
		return defects;
	}
	
	//Assign view property for status
	private DefectFixDetail assignViewStatus(DefectFixDetail defect)
	{
			if(defect.getStatus()==1)
			{
				defect.setViewStatus("Waiting For Fix");
			}
			else if(defect.getStatus()==2)
			{
				defect.setViewStatus("Fix Received");
			}
			else if(defect.getStatus()==3)
			{
				defect.setViewStatus("Deployed in SDF");
			}
			else if(defect.getStatus()==4)
			{
				defect.setViewStatus("Deployed in IST1");
			}
			else if(defect.getStatus()==5)
			{
				defect.setViewStatus("Deployed in IST2");
			}
			else if(defect.getStatus()==6)
			{
				defect.setViewStatus("Deployed in PROD");
			}
			else if(defect.getStatus()==7)
			{
				defect.setViewStatus("Deployed in Training");
			}
			else if(defect.getStatus()==8)
			{
				defect.setViewStatus("Deployed in DR");
			}
			else if(defect.getStatus()==9)
			{
				defect.setViewStatus("Closed");
			}
		
		return defect;
	}

	@Override
	public void saveDefectFixDetail(DefectFixDetail theDefectFixDetail) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theDefectFixDetail);

	}

	@Override
	public DefectFixDetail getDefectFixDetail(int defect_Id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		DefectFixDetail theDefectFixDetail = currentSession.get(DefectFixDetail.class, defect_Id);

		return theDefectFixDetail;
	}

	// delete
	@Override
	public void deleteDefectFixDetail(int defectId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// delete query
		Query deleteQuery = currentSession.createQuery("delete from DefectFixDetail where defect_Id=:defectId");

		deleteQuery.setParameter("defectId", defectId);

		deleteQuery.executeUpdate();

	}

	//search
	@Override
	public List<DefectFixDetail> searchDefects(String theSearchName) {

		// get the hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// search query
		Query searchQuery = null;
		// search by name if theSearchName is not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {
		// search the defect name--case insensetive
		searchQuery = currentSession.createQuery("from DefectFixDetail where lower(title) like:theName or vendor.vendorName like:theName or application.applicationName like:theName or defect_Id like:theName",DefectFixDetail.class);
		searchQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			searchQuery = currentSession.createQuery("from DefectFixDetail", DefectFixDetail.class);
		}
		// execute query and get result list
		List<DefectFixDetail> listDefects = searchQuery.getResultList();
		
		listDefects = assignViewStatus(listDefects);

		return listDefects;
	}
	
	

}
