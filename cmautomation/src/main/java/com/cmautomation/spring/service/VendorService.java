package com.cmautomation.spring.service;

import java.util.List;

import com.cmautomation.spring.entity.Vendor;


/**
 * @author farhana
 *
 */
public interface VendorService {
	
	public List<Vendor> getVendors();

	public void saveVendor(Vendor vendor);

	public Vendor getVendor(int vendor_id);

	public void deleteVendor(int vendor_id);

}
