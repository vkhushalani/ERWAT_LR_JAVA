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

import com.erwat.lr.model.BusinessRule;
import com.erwat.lr.service.BusinessRuleService;

@RestController
@RequestMapping("/TableMaint")
public class BusinessRuleController {
	Logger logger = LoggerFactory.getLogger(BusinessRuleController.class);
	private static String successMessage = "SUCCESS";
	@Autowired
	BusinessRuleService businessRuleService;
	
	@GetMapping("/BusinessRule")
	public ResponseEntity<List<BusinessRule>> getAll(){
		
		List<BusinessRule> BusinessRules = businessRuleService.findAll();
	     return ResponseEntity.ok().body(BusinessRules);
		
	}
	
	@GetMapping("/BusinessRule/{id}")
	 public ResponseEntity <BusinessRule> getById(@PathVariable("id") Integer id) {
		BusinessRule businessRule = businessRuleService.findById(id);
		return ResponseEntity.ok().body(businessRule);
	   }
	@PostMapping(value = "/BusinessRule")
	public ResponseEntity<?> create(@RequestBody BusinessRule businessRule)  {
		businessRule =  businessRuleService.create(businessRule);
		 return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/BusinessRule")
	public ResponseEntity<?> update(@RequestBody BusinessRule businessRule)  {
		businessRule =  businessRuleService.update(businessRule);
		 return ResponseEntity.ok().body(successMessage);
		
	}


}
