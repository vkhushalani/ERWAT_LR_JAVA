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

import com.erwat.lr.model.CaseOutcome;
import com.erwat.lr.service.CaseOutcomeService;

@RestController
@RequestMapping("/TableMaint")
public class CaseOutcomeController {
	private static String successMessage = "SUCCESS";
	Logger logger = LoggerFactory.getLogger(CaseOutcomeController.class);
	@Autowired
	CaseOutcomeService caseOutcomeService;

	@GetMapping("/CaseOutcome")
	public ResponseEntity<List<CaseOutcome>> getAll(){
		
		List<CaseOutcome> caseOutcomes = caseOutcomeService.findAll();
	     return ResponseEntity.ok().body(caseOutcomes);
		
	}
	
	@GetMapping("/CaseOutcome/{id}")
	 public ResponseEntity <CaseOutcome> getById(@PathVariable("id") Integer id) {
		CaseOutcome caseOutcomes = caseOutcomeService.findById(id);
		return ResponseEntity.ok().body(caseOutcomes);
	   }
	@PostMapping(value = "/CaseOutcome")
	public ResponseEntity<?> create(@RequestBody CaseOutcome caseOutcomes)  {
		caseOutcomes =  caseOutcomeService.create(caseOutcomes);
		 return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/CaseOutcome")
	public ResponseEntity<?> update(@RequestBody CaseOutcome caseOutcomes)  {
		caseOutcomes =  caseOutcomeService.update(caseOutcomes);
		 return ResponseEntity.ok().body(successMessage);
		
	}

}
