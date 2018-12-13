package com.cmautomation.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmautomation.spring.entity.Vendor;
import com.cmautomation.spring.service.VendorService;


/*
 * This controller deals with CRUD operation for ADMIN user
 * 
 * */
@Controller
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	// pre-process form data and eliminates any white spaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// this method shows the application list
	@GetMapping("/list") // this is the view name
	public String listVendors(Model theVendor) {

		List<Vendor> theVendors = vendorService.getVendors();

		// add the applications to the model
		theVendor.addAttribute("vendors", theVendors);
		theVendor.addAttribute("title", "listVendors");

		return "list-vendors";// this is the jsp file to be rendered
	}

	// this method renders the app-form and binds the fields with the model
	@GetMapping("/vendorAddForm")
	public String showVendorAddForm(Model theVendor) {

		Vendor vendor = new Vendor();
		theVendor.addAttribute("vendor", vendor);
		theVendor.addAttribute("title", "vendorForm");
		return "vendor-form";
	}

	// this method saves the application
	@PostMapping("/saveVendor")
	public String saveVendor(@Valid @ModelAttribute("vendor") Vendor vendor, BindingResult theBindingResult) {

		if (theBindingResult.hasErrors()) {
			return "vendor-form";
		} else {
			vendorService.saveVendor(vendor);
			return "redirect:/vendor/list";
		}
	}

	// this method updates the application
	@GetMapping("/vendorUpdateForm")
	public String vendorUpdateForm(@RequestParam("vendor_id") int vendor_id, Model theVendor) {

		// get the vendor from vendorService
		Vendor vendor = vendorService.getVendor(vendor_id);

		// set application as a model to pre-populate the update form
		theVendor.addAttribute("vendor", vendor);
		theVendor.addAttribute("title", "vendorForm");
		return "vendor-form";
	}

	// method to delete the application
	@GetMapping("/vendorDeleteForm")
	public String vendorDeleteForm(@RequestParam("vendor_id") int vendor_id) {
		// delete the application
		vendorService.deleteVendor(vendor_id);

		return "redirect:/vendor/list";
	}
}
