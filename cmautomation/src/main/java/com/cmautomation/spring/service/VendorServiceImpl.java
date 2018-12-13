package com.cmautomation.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmautomation.spring.dao.VendorDAO;
import com.cmautomation.spring.entity.Vendor;

/*
 * This class serves as service layer of MVC and handles business logic of the application
 * and communicate to Vendor controller
 * 
 * 
 * */
@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorDAO vendorDAO;
	
	// get the list of applications
	@Override
	@Transactional
	public List<Vendor> getVendors() {
		
		return vendorDAO.getVendors();
	}

	// saves the new application record to the database
	@Override
	@Transactional
	public void saveVendor(Vendor vendor) {
		
		vendorDAO.saveVendor(vendor);
		
	}

	//get back the application back to the app-form page for update
	@Override
	@Transactional
	public Vendor getVendor(int vendor_id) {
		
		 return vendorDAO.getVendor(vendor_id);
	}

	// delete the application
	@Override
	@Transactional
	public void deleteVendor(int vendor_id) {
		
		vendorDAO.deleteVendor(vendor_id);
		
	}

}
