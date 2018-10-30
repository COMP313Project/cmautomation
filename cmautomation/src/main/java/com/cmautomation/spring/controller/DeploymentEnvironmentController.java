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
import com.cmautomation.spring.service.DeploymentEnvironmentService;

@Controller
@RequestMapping("/environment")
public class DeploymentEnvironmentController {

	@Autowired
	private DeploymentEnvironmentService deploymentEnvironmentService;

	// pre-process form data and eliminates any white spaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/list")
	public String listDeploymentEnvironments(Model theEnvModel) {

		List<DeploymentEnvironment> deploymentEnvironment = deploymentEnvironmentService.getEnvironmentList();

		theEnvModel.addAttribute("environments", deploymentEnvironment);
		theEnvModel.addAttribute("title", "deploymentEnvironmentList");

		return "list-deploymentEnvironment";
	}

	@GetMapping("/envAddForm")
	public String showAddForm(Model theEnvModel) {

		DeploymentEnvironment environments = new DeploymentEnvironment();

		theEnvModel.addAttribute("environments", environments);
		theEnvModel.addAttribute("title", "environmentForm");

		return "environment-form";
	}

	@PostMapping("/saveEnvironment")
	public String saveEnvironment(@Valid @ModelAttribute("environments") DeploymentEnvironment deploymentEnvironment,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "environment-form";
		}else {
			deploymentEnvironmentService.saveEnvironment(deploymentEnvironment);
			return "redirect:/environment/list";
		}		
	}

	@GetMapping("/envUpdateForm")
	public String envUpdateForm(@RequestParam("environmentId") int envId, Model theEnvModel) {

		// get the application from aplicationService
		DeploymentEnvironment environments = deploymentEnvironmentService.getDeploymentEnvironment(envId);

		// set application as a model to pre-populate the update form
		theEnvModel.addAttribute("environments", environments);

		theEnvModel.addAttribute("title", "environmentForm");

		return "environment-form";
	}

	@GetMapping("/delete")
	public String deleteForm(@RequestParam("environmentId") int envId) {
		// delete the application
		deploymentEnvironmentService.deleteEnvironment(envId);

		return "redirect:/environment/list";
	}

}
