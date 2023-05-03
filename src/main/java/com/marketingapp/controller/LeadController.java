package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.dto.LeadDto;
import com.marketingapp.entities.Lead;
import com.marketingapp.repository.LeadRepository;
import com.marketingapp.service.LeadService;
import com.marketingapp.util.EmailService;
import com.marketingapp.util.EmailServiceImpl;

@Controller
public class LeadController {

	@Autowired
	private LeadService leadservice;
	
	@Autowired
	private EmailService emailService;

	// localhost:8080/creates

	@RequestMapping("/creates") // @webServlet
	public String viewCreateLeadForm() {
		return "create_lead";// request dispatcher
	}

	//localhost:8080/creates
	
	//@ModelAttribute method
	
	@RequestMapping("/saveReg")
	public String SaveOneLead(@ModelAttribute Lead lead,Model model) {
		leadservice.saveReg(lead);
		model.addAttribute("msg", "saved successfully..!");
		
		emailService.SendEmail(lead.getEmail(), "testing email", "please check...did it worked  ");
		return "create_lead";
	}
	
	//localhost:8080/creates
	
	//@RequestParam method
//      @RequestMapping("/saveReg")
//      public String SaveOneLead(@RequestParam("firstName")String firstName,@RequestParam("lastName")String lastName,@RequestParam("email")String email,@RequestParam("mobile")Long mobile,ModelMap model) {
//    	 Lead l = new Lead();
//    	 l.setFirstName(firstName);
//    	 l.setLastName(lastName);
//    	 l.setEmail(email);
//    	 l.setMobile(mobile);
//    	 leadservice.saveReg(l);
//    	 model.addAttribute("msg", "saved the record successfully");
//    	  return "create_lead";
//	}
      

  	//localhost:8080/creates
  	
  	//Dto method

//  	@RequestMapping("/saveReg")
//  	public String SaveOneLead(LeadDto dto,Model model) {
//  		
//  		Lead lead = new Lead ();
//  		lead.setFirstName(dto.getFirstName());
//  		lead.setLastName(dto.getLastName());
//  		lead.setEmail(dto.getEmail());
//  		lead.setMobile(dto.getMobile());
//  		
//  		leadservice.saveReg(lead);
//  				
//  		model.addAttribute("msg", "Data saved successfully..!");
//  		return "create_lead";
//  	}
	
	//localhost:8080/list_lead
	@RequestMapping("/list_lead")
	public String ListAllLead(Model model) {
		List<Lead> leads = leadservice.findAllId();
		model.addAttribute("leads", leads);
		return "list_lead";
	}
	
	@RequestMapping("/delete")
	public String deleteById(@RequestParam("id")long id, Model model) {
		
		leadservice.deleteById(id);
		List<Lead> leads = leadservice.findAllId();
		model.addAttribute("leads", leads);
		return "list_lead";
	}
	
	@RequestMapping("/update")
	public String UpdateLead(@RequestParam("id")long id, Model model) {
		Lead lead = leadservice.findLeadById(id);
		model.addAttribute("lead", lead);
		return "update_lead";
	}
	
	@RequestMapping("/editReg")
	public String editReg (LeadDto dto, Model model) {
		
		Lead lead = new Lead ();
		lead.setId(dto.getId());
		lead.setFirstName(dto.getFirstName());
		lead.setLastName(dto.getLastName());
		lead.setEmail(dto.getEmail());
		lead.setMobile(dto.getMobile());
		
		leadservice.saveReg(lead);
		List<Lead> leads = leadservice.findAllId();

		model.addAttribute("leads", leads);
		return "list_lead";
	}
	
	
	
	

}
