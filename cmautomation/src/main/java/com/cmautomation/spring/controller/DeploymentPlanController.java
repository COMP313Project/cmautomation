package com.cmautomation.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmautomation.spring.entity.Application;
import com.cmautomation.spring.entity.DefectFixDetail;
import com.cmautomation.spring.entity.DeploymentPlan;
import com.cmautomation.spring.entity.Vendor;
import com.cmautomation.spring.service.ApplicationService;
import com.cmautomation.spring.service.DefectFixDetailService;
import com.cmautomation.spring.service.DeploymentPlanService;


@Controller
@RequestMapping("/cma/deploymentPlan")
public class DeploymentPlanController {

	@Autowired
	private DeploymentPlanService deploymentPlanService;

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private DefectFixDetailService defectFixDetailService;
	
//	@Autowired
//	private QACheckListService qACheckListService;

//	// pre-process form data and eliminates any white spaces
//	@InitBinder
//	public void initBinder(WebDataBinder dataBinder) {
//
//		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//	}

	@GetMapping("/list") // works
	public String getDeploymentPlan(Model theDeploymentPlanModel) {

		List<DeploymentPlan> theDeploymentPlan = deploymentPlanService.getDeploymentPlanList();

		theDeploymentPlanModel.addAttribute("theDeploymentPlanList", theDeploymentPlan);
		theDeploymentPlanModel.addAttribute("title", "DeploymentSchedule");

		return "list-deployment";
	}
	
	 
	@RequestMapping(value = "/getApps", method = RequestMethod.GET)
	public @ResponseBody
	List<Application> getApps() {
		List<Application> applications = applicationService.getApplications();
		return applications;
	}
	


	@GetMapping("/addForm")
	public String showPlanAddForm(Model theDeploymentPlanModel) {

		DeploymentPlan theDeploymentPlan = new DeploymentPlan();

		List<Application> applications = applicationService.getApplications();

		List<DefectFixDetail> listDefectFixDetail = defectFixDetailService.getDefectList();

		theDeploymentPlanModel.addAttribute("deploymentPlanDetail", theDeploymentPlan);
		theDeploymentPlanModel.addAttribute("applications", applications);
		theDeploymentPlanModel.addAttribute("listDefectFixDetail", listDefectFixDetail);
		theDeploymentPlanModel.addAttribute("title", "PlanDeployment");

		return "deploymentPlan-form";
	}

	@PostMapping("/saveDeploymentPlan")
	public String saveDeploymentPlan( @ModelAttribute("deploymentPlanDetail") DeploymentPlan theDeploymentPlan) {
		
		List<DefectFixDetail> listDeploymentDefects = new ArrayList<>();

		List<String> objDefects = theDeploymentPlan.getDefects();

		// if(objDefects!=null && defects.size()>0)
			// {
			for (String defect : objDefects) {
				DefectFixDetail obj1 = new DefectFixDetail();
				obj1.setDefect_Id(Integer.parseInt(defect));
				listDeploymentDefects.add(obj1);
			}
			// }
			theDeploymentPlan.setListDeploymentDefects(listDeploymentDefects);
			deploymentPlanService.saveDeploymentPlan(theDeploymentPlan);
			return "redirect:/cma/deploymentPlan/list";
	}

	// update deploymentPlan
	@GetMapping("/deploymentPlanUpdateForm")
	public String getDeploymentPlanUpdateForm(@RequestParam("deployment_Id") int deployment_Id,
			Model theDeploymentPlanModel) {
		try {

			DeploymentPlan theDeploymentPlan = deploymentPlanService.getDeploymentPlan(deployment_Id);

			 List<DefectFixDetail> listDeploymentDefects=new ArrayList<>();
			 
			 List<DefectFixDetail> listDefectFixDetail = defectFixDetailService.getDefectList();

			List<String> objDefects = new ArrayList<>();

			// if(objDefects!=null && defects.size()>0)
			// {
			for (DefectFixDetail deploymentDefect : theDeploymentPlan.getListDeploymentDefects()) {

				objDefects.add(deploymentDefect.getDefect_Id().toString());
				//listDefectFixDetail.add(defectFixDetailService.getDefectFixDetail(deploymentDefect.getDefect_Id()));
				
			}
			// }

			theDeploymentPlan.setDefects(objDefects);

			List<Application> applications = applicationService.getApplications();

			

			theDeploymentPlanModel.addAttribute("deploymentPlanDetail", theDeploymentPlan);
			theDeploymentPlanModel.addAttribute("applications", applications);
			theDeploymentPlanModel.addAttribute("listDefectFixDetail", listDefectFixDetail);
			theDeploymentPlanModel.addAttribute("title", "PlanDeployment");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "deploymentPlan-form";
	}

	// delete defect fix detail
	@GetMapping("/deleteDeploymentPlan") // works fine
	public String deleteDeploymentPlan(@RequestParam("deployment_Id") int deployment_Id) {

		deploymentPlanService.deleteDeploymentPlan(deployment_Id);
		//qACheckListService.deleteQaCheckList();
		

		return "redirect:/cma/deploymentPlan/list";

	}

	// search
	@PostMapping("/searchDeploymentPlan")
	public String searchDeploymentPlan(@RequestParam("theSearchName") String theSearchName, Model theModel) {

		List<DeploymentPlan> searchDeploymentPlan = deploymentPlanService.searchDeploymentPlan(theSearchName);

		theModel.addAttribute("theDeploymentPlanList", searchDeploymentPlan);
		theModel.addAttribute("title", "listDeploymentPlan");

		return "list-deployment";

	}

}
