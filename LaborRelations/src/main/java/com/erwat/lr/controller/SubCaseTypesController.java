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


import com.erwat.lr.model.SubCaseType;

import com.erwat.lr.service.SubCaseTypeService;

@RestController
@RequestMapping("/TableMaint")
public class SubCaseTypesController {
Logger logger = LoggerFactory.getLogger(SubCaseTypesController.class);
private static String successMessage = "SUCCESS";
@Autowired
SubCaseTypeService subCaseTypeService;
	

	
	@GetMapping("/SubCaseType")
	public ResponseEntity<List<SubCaseType>> getAllCaseTypes(){
		
		List<SubCaseType> caseTypes = subCaseTypeService.findAll();
	     return ResponseEntity.ok().body(caseTypes);
		
	}
	
	@GetMapping("/SubCaseType/{id}")
	 public ResponseEntity <SubCaseType> getCaseTypeById(@PathVariable("id") Integer id) {
		SubCaseType caseTypes = subCaseTypeService.findById(id);
		return ResponseEntity.ok().body(caseTypes);
	   }
	
	
	@PostMapping(value = "/SubCaseType")
	public ResponseEntity<?> createCaseType(@RequestBody SubCaseType caseType)  {
		caseType =  subCaseTypeService.create(caseType);
		 return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/SubCaseType")
	public ResponseEntity<?> updateCaseType(@RequestBody SubCaseType caseType)  {
		caseType =  subCaseTypeService.update(caseType);
		 return ResponseEntity.ok().body(successMessage);
		
	}

}
