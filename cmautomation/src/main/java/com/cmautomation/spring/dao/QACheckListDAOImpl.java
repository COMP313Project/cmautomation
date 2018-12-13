package com.cmautomation.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmautomation.spring.entity.DeploymentCheckList;
import com.cmautomation.spring.entity.QACheckList;
import com.cmautomation.spring.entity.QACompositeKeyId;

/*
 * This Data access layer inherits from QAChecklistDAO, and communicates with Database with 
 * CRUD operation for Application for QA User 
 * 
 * */

@Repository
public class QACheckListDAOImpl implements QACheckListDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// list all QA checklists
	@Override
	public List<QACheckList> getQACheckList() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<QACheckList> theQuery = currentSession.createQuery("from QACheckList", QACheckList.class);

		// execute query and get result list
		List<QACheckList> qaCheckList = theQuery.getResultList();
		
		qaCheckList = assignViewStatus(qaCheckList);

		return qaCheckList;
	}

	//Assign view property for status
		private List<QACheckList> assignViewStatus(List<QACheckList> qaCheckLists)
		{
			for(int i=0; i<qaCheckLists.size();i++)
			{
				qaCheckLists.set(i, assignViewStatus(qaCheckLists.get(i)));
			}
			
			return qaCheckLists;
		}
		
		//Assign view property for status
		private QACheckList assignViewStatus(QACheckList qaCheckList)
		{
				if(qaCheckList.getTestStatus()==1)
				{
					qaCheckList.setViewStatus("PASS");
				}
				else if(qaCheckList.getTestStatus()==2)
				{
					qaCheckList.setViewStatus("FAIL");
				}
			
			return qaCheckList;
		}

		// saves a qa checklist
	@Override
	public void saveQACheckList(QACheckList qaCheckList) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(qaCheckList);
	}

	// get a QA checklist by ID
	@Override
	public QACheckList getQACheckListDetail(int qaComp_Id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		QACheckList qaCheckList = currentSession.get(QACheckList.class, qaComp_Id);
		//qaCheckList = assignViewStatus(qaCheckList);

		return qaCheckList;
	}

	// delete QA checklist
	@Override
	public void deleteQaCheckList(int qaComp_Id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// delete query
		Query deleteQuery = currentSession.createQuery("delete from QACheckList where qachecklist_id=:qaComp_Id");

		deleteQuery.setParameter("qaComp_Id", qaComp_Id);

		deleteQuery.executeUpdate();

	}

}
