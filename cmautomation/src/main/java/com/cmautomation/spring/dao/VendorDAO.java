package com.cmautomation.spring.dao;

import java.util.List;

import com.cmautomation.spring.entity.Vendor;


public interface VendorDAO {

	List<Vendor> getVendors();

	public void saveVendor(Vendor vendor);

	public Vendor getVendor(int appId);

	public void deleteVendor(int appId);

}

