package com.erwat.lr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erwat.lr.model.CaseNatureCategoryMap;
import com.erwat.lr.service.CaseNatureCategoryMapService;

@RestController
@RequestMapping("/TableMaint")
public class CaseNatureCategoryMapController {
	private static String successMessage = "SUCCESS";
Logger logger = LoggerFactory.getLogger(CaseNatureCategoryMapController.class);
	
	@Autowired
	CaseNatureCategoryMapService caseNatureCategoryMapService;
	
	@GetMapping("/CaseNatureCategory")
	public ResponseEntity<List<CaseNatureCategoryMap>> getAll(){
		
		List<CaseNatureCategoryMap> caseNatureCategory = caseNatureCategoryMapService.findAll();
	     return ResponseEntity.ok().body(caseNatureCategory);
		
	}
	
	@PostMapping(value = "/CaseNatureCategory")
	public ResponseEntity<?> create(@RequestBody CaseNatureCategoryMap caseNatureCategory)  {
		caseNatureCategory =  caseNatureCategoryMapService.create(caseNatureCategory);
		 return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/CaseNatureCategory")
	public ResponseEntity<?> update(@RequestBody CaseNatureCategoryMap caseNatureCategory)  {
		caseNatureCategory =  caseNatureCategoryMapService.update(caseNatureCategory);
		 return ResponseEntity.ok().body(successMessage);
		
	}

}
