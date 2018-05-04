package com.erwat.lr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erwat.lr.model.StatusBusinessRule;
import com.erwat.lr.service.StatusBusinessRuleService;

@RestController
@RequestMapping("/TableMaint")
public class StatusBusinessRuleController {
Logger logger = LoggerFactory.getLogger(StatusBusinessRuleController.class);
	private static String successMessage = "SUCCESS";
	@Autowired
	StatusBusinessRuleService businessRuleService;
	
	@GetMapping("/StatusBusinessRule")
	public ResponseEntity<List<StatusBusinessRule>> getAll(){
		
		List<StatusBusinessRule> BusinessRules = businessRuleService.findAll();
	     return ResponseEntity.ok().body(BusinessRules);
		
	}
	
	@GetMapping("/StatusBusinessRule/{id}")
	 public ResponseEntity <StatusBusinessRule> getById(@PathVariable("id") Integer id) {
		StatusBusinessRule businessRule = businessRuleService.findById(id);
		return ResponseEntity.ok().body(businessRule);
	   }
	@PostMapping(value = "/StatusBusinessRule")
	public ResponseEntity<?> create(@RequestBody StatusBusinessRule businessRule)  {
		businessRule =  businessRuleService.create(businessRule);
		 return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/StatusBusinessRule")
	public ResponseEntity<?> update(@RequestBody StatusBusinessRule businessRule)  {
		businessRule =  businessRuleService.update(businessRule);
		 return ResponseEntity.ok().body(successMessage);
		
	}


}
