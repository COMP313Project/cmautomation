///



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

import com.cmautomation.spring.entity.DeploymentEnvironment;
import com.cmautomation.spring.entity.DeploymentPlan;
import com.cmautomation.spring.entity.QACheckList;
import com.cmautomation.spring.entity.QACompositeKeyId;
import com.cmautomation.spring.service.DefectFixDetailService;
import com.cmautomation.spring.service.DeploymentEnvironmentService;
import com.cmautomation.spring.service.DeploymentPlanService;
import com.cmautomation.spring.service.QACheckListService;

@Controller
@RequestMapping("/qa/checkList")
public class QACheckListController {

	@Autowired
	private QACheckListService qaCheckListservice;

	@Autowired
	private DefectFixDetailService defectFixDetailService;

	@Autowired
	private DeploymentPlanService deploymentPlanService;

	@Autowired
	private DeploymentEnvironmentService deploymentEnvironmentService;

	// pre-process form data and eliminates any white spaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// shows the list of qa checklists
	@GetMapping("/list")
	public String getQACheckList(Model theQACheckListModel) {

		List<QACheckList> qaCheckList = qaCheckListservice.getQACheckList();

		theQACheckListModel.addAttribute("qaCheckList", qaCheckList);
		theQACheckListModel.addAttribute("title", "QACheckList");

		return "qa-checklist";
	}

	// renders the add qa checklist form to insert data
	@GetMapping("/QACheckListAddForm")
	public String showQACheckListAddForm(Model theQACheckListModel) {

		QACheckList theQACheckList = new QACheckList();

		List<DeploymentPlan> deploymentPlan = deploymentPlanService.getDeploymentPlanList();
		List<DeploymentEnvironment> deploymentEnvironmentList = deploymentEnvironmentService.getEnvironmentList();

		theQACheckListModel.addAttribute("theQACheckListDetail", theQACheckList);

		theQACheckListModel.addAttribute("deploymentPlan", deploymentPlan);

		theQACheckListModel.addAttribute("deploymentEnvironmentList", deploymentEnvironmentList);
		theQACheckListModel.addAttribute("title", "AddQACheckList");

		return "qaChecklist-form";
	}

	// saves a new record
	@PostMapping("/saveQACheckList")
	public String saveQACheckListForm(@Valid @ModelAttribute("theQACheckListDetail") QACheckList qaCheckList,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "redirect:/qa/checkList/QACheckListAddForm";
		}else {
			qaCheckListservice.saveQACheckList(qaCheckList);
			return "redirect:/qa/checkList/list";		
		}

		

	}

	// update
	@GetMapping("/qaCheckListUpdateForm")
	public String qaCheckListUpdateForm(@RequestParam("qaChecklistId") int qaComp_Id, Model theQACheckListModel) {

		QACheckList qaCheckList = qaCheckListservice.getQACheckListDetail(qaComp_Id);

		List<DeploymentPlan> deploymentPlan = deploymentPlanService.getDeploymentPlanList();
		List<DeploymentEnvironment> deploymentEnvironmentList = deploymentEnvironmentService.getEnvironmentList();

		theQACheckListModel.addAttribute("theQACheckListDetail", qaCheckList);
		theQACheckListModel.addAttribute("deploymentPlan", deploymentPlan);
		theQACheckListModel.addAttribute("deploymentEnvironmentList", deploymentEnvironmentList);
		theQACheckListModel.addAttribute("title", "AddQACheckList");

		return "qaChecklist-form";

	}

	// delete
	@GetMapping("/delete")
	public String deleteQACheckList(@RequestParam("qaChecklistId") int qaComp_Id, Model theQACheckListModel) {

		qaCheckListservice.deleteQaCheckList(qaComp_Id);

		return "redirect:/qa/checkList/list";
	}

}
