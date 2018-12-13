package com.cmautomation.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmautomation.spring.entity.Vendor;

/*
 * This Data access layer inherits from VendorDAO, and communicates with Database with 
 * CRUD operation for Vendor for ADMIN User 
 * 
 * */
@Repository
public class VendorDAOImpl implements VendorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// list all vendors
	@Override
	public List<Vendor> getVendors() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Vendor> theQuery = currentSession.createQuery("from Vendor", Vendor.class);

		// execute query and get result list
		List<Vendor> vendors = theQuery.getResultList();
		
		for(Vendor v: vendors)
		{			
			if(v.getAapplicationList().size()>0)
			{
				
				
			}
		}
		
		

		// return the results
		return vendors;
	}
	//saves a new entry into database
	@Override
	public void saveVendor(Vendor vendor) {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(vendor);
		
	}
	// gets the application from database and pre-populate the form
	@Override
	public Vendor getVendor(int vendor_Id) {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		Vendor theVendor=currentSession.get(Vendor.class, vendor_Id);
		
		return theVendor;
	}
	//deletes an application from database
	@Override
	public void deleteVendor(int vendor_Id) {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		//delete query
		Query deleteQuery=currentSession.createQuery("delete from Vendor where vendor_Id=:vendor_Id");
		deleteQuery.setParameter("vendor_Id", vendor_Id);
		deleteQuery.executeUpdate();
	}

}
