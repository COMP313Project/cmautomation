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

import com.cmautomation.spring.entity.Application;
import com.cmautomation.spring.entity.DefectFixDetail;
import com.cmautomation.spring.entity.Vendor;
import com.cmautomation.spring.service.ApplicationService;
import com.cmautomation.spring.service.DefectFixDetailService;
import com.cmautomation.spring.service.VendorService;

@Controller
@RequestMapping("/cma")
public class DefectFixDetailController {

	// private static final Model=null;

	@Autowired
	private DefectFixDetailService defectFixDetailService;

	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private VendorService vendorService;

	// pre-process form data and eliminates any white spaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// render defect list
	@GetMapping("/defect/list") // works fine
	private String getDefectFixList(Model theDefectFixDetailModel) {

		List<DefectFixDetail> theDefectFixDetail = defectFixDetailService.getDefectList();

		theDefectFixDetailModel.addAttribute("theDefectFixList", theDefectFixDetail);
		theDefectFixDetailModel.addAttribute("title", "listDefectFixDetail");

		return "list-defectFixDetail";
	}

	// render the defectAddForm
	@GetMapping("/defect/defectAddForm") // works fine
	public String showDefectAddForm(Model theDefectFixDetailModel) {

		DefectFixDetail theDefectFixDetail = new DefectFixDetail();

		// Get Application List for input
		List<Application> applications = applicationService.getApplications();

		// Get Vendor List for input
		List<Vendor> vendors = vendorService.getVendors();

		theDefectFixDetailModel.addAttribute("theDefectFixDetail", theDefectFixDetail);
		theDefectFixDetailModel.addAttribute("applications", applications);
		theDefectFixDetailModel.addAttribute("vendors", vendors);
		theDefectFixDetailModel.addAttribute("title", "defectForm");
		return "defect-form";
	}

	// saves a new record in the defect fix table
	@PostMapping("/defect/saveDefectFixDetail") // works fine
	public String saveDefectFixDetail(@Valid @ModelAttribute("theDefectFixDetail") DefectFixDetail defectFixDetail,
			BindingResult theBindingResult, Model model) {
		
		if(theBindingResult.hasErrors()) {			
			//return "defect-form";			
			return "redirect:/cma/defect/defectAddForm";		
		}else {
			defectFixDetailService.saveDefectFixDetail(defectFixDetail);
			return "redirect:/cma/defect/list";			
		}		
	}

	// update defect
	@GetMapping("/defect/defectUpdateForm")
	public String defectUpdateForm(@RequestParam("defectId") int defect_Id, Model theDefectFixDetailModel) {

		DefectFixDetail theDefectFixDetail = defectFixDetailService.getDefectFixDetail(defect_Id);

		// Get Application List for input
		List<Application> applications = applicationService.getApplications();

		// Get Vendor List for input
		List<Vendor> vendors = vendorService.getVendors();

		theDefectFixDetailModel.addAttribute("theDefectFixDetail", theDefectFixDetail);
		theDefectFixDetailModel.addAttribute("applications", applications);
		theDefectFixDetailModel.addAttribute("vendors", vendors);
		theDefectFixDetailModel.addAttribute("title", "defectForm");

		return "defect-form";
	}

	// delete defect fix detail
	@GetMapping("/defect/delete") // works fine
	public String deleteDefectForm(@RequestParam("defectId") int defect_Id) {

		defectFixDetailService.deleteDefectFixDetail(defect_Id);

		return "redirect:/cma/defect/list";

	}

	// search
	@PostMapping("/defect/search")
	public String searchDefects(@RequestParam("theSearchName") String theSearchName, Model theModel) {

		List<DefectFixDetail> searchDefectFixDetail = defectFixDetailService.searchDefects(theSearchName);

		theModel.addAttribute("theDefectFixList", searchDefectFixDetail);
		theModel.addAttribute("title", "listDefectFixDetail");

		return "list-defectFixDetail";

	}

}
