package com.cmautomation.spring.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmautomation.spring.entity.Application;
import com.cmautomation.spring.entity.Vendor;
import com.cmautomation.spring.service.ApplicationService;
import com.cmautomation.spring.service.VendorService;


/*
 * This controller class deals with the application component of the app
 * 
 * */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private VendorService vendorService;
	
	//pre-process form data and eliminates any white spaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// this method shows the application list
	@GetMapping("/app/list") // this is the view name
	public String listApplications(Model theAppModel) {

		List<Application> theApplications = applicationService.getApplications();

		// add the applications to the model
		theAppModel.addAttribute("applications", theApplications);
		
		theAppModel.addAttribute("title", "listApplication");

		return "list-apps";// this is the jsp file to be rendered
	}
	// this method renders the app-form and binds the fields with the model
	@GetMapping("/app/appAddForm")
	public String showAppAddForm(Model theAppModel) {
		
		Application application=new Application();
		// Get Vendor List for input
		List<Vendor> vendors = vendorService.getVendors();
		
		theAppModel.addAttribute("application",application);
		theAppModel.addAttribute("vendors", vendors);
		theAppModel.addAttribute("title", "applicationForm");
		
		return "app-form";
	}
	
	// this method saves the application
	@PostMapping("/app/saveApplication")
	public String saveApplication(
			@Valid @ModelAttribute("application") Application application,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "app-form";
		}else {
			applicationService.saveApplication(application);
			return "redirect:/admin/app/list";
		}
	}
	//this method updates the application
	@GetMapping("/app/appUpdateForm")
	public String appUpdateForm(@RequestParam("applicationId") int appId, Model theAppModel) {
		
		//get the application from aplicationService
		Application application=applicationService.getApplication(appId);
		// Get Vendor List for input
		List<Vendor> vendors = vendorService.getVendors();
		
		//set application as a model to pre-populate the update form
		theAppModel.addAttribute("application", application);
		theAppModel.addAttribute("vendors", vendors);
		theAppModel.addAttribute("title", "applicationForm");
		
		return "app-form";
	}
	//method to delete the application
	@GetMapping("/app/delete")
	public String deleteForm(@RequestParam("applicationId") int appId) {
		//delete the application
		applicationService.deleteApplication(appId);
		
		return "redirect:/admin/app/list";
	}
	
}
