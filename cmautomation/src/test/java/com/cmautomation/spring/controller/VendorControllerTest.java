package com.cmautomation.spring.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashPrintServiceAttributeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.cmautomation.spring.entity.Vendor;
import com.cmautomation.spring.service.VendorService;


public class VendorControllerTest {
	
	@InjectMocks//sets up controller and injects mock objects into it
	VendorController vendorController;
	@Mock // Mockito mock object
	private VendorService vendorService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);// initializes controller and mocks
		mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
	}
	

	@Test
	public void testList_of_vendors() throws Exception{
		List<Vendor> listVendors = new ArrayList<>();
		Vendor v1=new Vendor();
		Vendor v2=new Vendor();
		listVendors.add(v1);
		listVendors.add(v2);
		
		//specific mockito interaction
		when(vendorService.getVendors()).thenReturn(listVendors);
		
		mockMvc.perform(get("/vendor/list"))
		.andExpect(status().isOk())
		.andExpect(view().name("list-vendors"))
		.andExpect(model().attribute("vendors",hasSize(2)));
	}
	
	private static int hasSize(int i) {
		return i;
	}
	@Test
	public void test_save_new_product() throws Exception{	
		verifyZeroInteractions(vendorService);
		
		mockMvc.perform(get("/vendor/vendorAddForm"))
			.andExpect(status().isOk())
			.andExpect(view().name("vendor-form"))
			.andExpect(model().attribute("vendor", instanceOf(Vendor.class)));
	}
	@Test
	public void test_update_vendor() throws Exception{
		
	}
	
	@Test
	public void test_delete_vendor () throws Exception {
		Integer id=100;
		
		mockMvc.perform(get("/vendor/vendorDeleteForm"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/vendor/list"));
				
		verify(vendorService,times(1)).deleteVendor(id);
		
	}



	
}
