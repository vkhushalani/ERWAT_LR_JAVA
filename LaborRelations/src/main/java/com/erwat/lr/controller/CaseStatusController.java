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

import com.erwat.lr.model.CaseStatus;
import com.erwat.lr.service.CaseStatusService;

@RequestMapping("/TableMaint")
@RestController
public class CaseStatusController {
	private static String successMessage = "SUCCESS";
	Logger logger = LoggerFactory.getLogger(CaseStatusController.class);
	@Autowired
	CaseStatusService caseStatusService;

	@GetMapping("/CaseStatus")
	public ResponseEntity<List<CaseStatus>> getAll(){
		
		List<CaseStatus> caseStatuses = caseStatusService.findAll();
	     return ResponseEntity.ok().body(caseStatuses);
		
	}
	
	@GetMapping("/CaseStatus/{id}")
	 public ResponseEntity <CaseStatus> getById(@PathVariable("id") Integer id) {
		CaseStatus caseStatus = caseStatusService.findById(id);
		return ResponseEntity.ok().body(caseStatus);
	   }
	@PostMapping(value = "/CaseStatus")
	public ResponseEntity<?> create(@RequestBody CaseStatus caseStatus)  {
		caseStatus =  caseStatusService.create(caseStatus);
		 return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/CaseStatus")
	public ResponseEntity<?> update(@RequestBody CaseStatus caseStatus)  {
		caseStatus =  caseStatusService.update(caseStatus);
		 return ResponseEntity.ok().body(successMessage);
		
	}
}
