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

import com.erwat.lr.model.CaseNature;
import com.erwat.lr.model.CaseNatureCategoryMap;
import com.erwat.lr.service.CaseNatureCategoryMapService;
import com.erwat.lr.service.CaseNatureService;

@RestController
@RequestMapping("/TableMaint")
public class CaseNatureController {
	private static String successMessage = "SUCCESS";
	Logger logger = LoggerFactory.getLogger(CaseNatureController.class);
	
	@Autowired
	CaseNatureService caseNatureService;
	@Autowired
	CaseNatureCategoryMapService caseNatureCategoryMapService;
	
	@GetMapping("/CaseNature")
	public ResponseEntity<List<CaseNature>> getAll(){
		
		List<CaseNature> CaseNatures = caseNatureService.findAll();
	     return ResponseEntity.ok().body(CaseNatures);
		
	}
	
	@GetMapping("/CaseNature/{id}")
	 public ResponseEntity <CaseNature> getById(@PathVariable("id") Integer id) {
		CaseNature caseNature = caseNatureService.findById(id);
		return ResponseEntity.ok().body(caseNature);
	   }
	
	@GetMapping("/CaseNature/{id}/CaseCategory")
	 public ResponseEntity <List<CaseNatureCategoryMap>> getCategoryById(@PathVariable("id") Integer id) {
		List<CaseNatureCategoryMap> caseNatureCategoryMapList = caseNatureCategoryMapService.findByCaseNatureId(id);
		return ResponseEntity.ok().body(caseNatureCategoryMapList);
	   }
	
	@PostMapping(value = "/CaseNature")
	public ResponseEntity<?> create(@RequestBody CaseNature caseNature)  {
		caseNature =  caseNatureService.create(caseNature);
		 return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/CaseNature")
	public ResponseEntity<?> update(@RequestBody CaseNature caseNature)  {
		caseNature =  caseNatureService.update(caseNature);
		 return ResponseEntity.ok().body(successMessage);
		
	}

}
