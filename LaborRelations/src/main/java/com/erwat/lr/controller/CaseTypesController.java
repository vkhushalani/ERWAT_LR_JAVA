package com.erwat.lr.controller;

import java.util.ArrayList;
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

import com.erwat.lr.helper.TileCount;
import com.erwat.lr.model.CaseTypes;
import com.erwat.lr.service.BusinessRuleService;
import com.erwat.lr.service.CaseCategoryService;
import com.erwat.lr.service.CaseNatureCategoryMapService;
import com.erwat.lr.service.CaseNatureService;
import com.erwat.lr.service.CaseOutcomeService;
import com.erwat.lr.service.CaseRoleService;
import com.erwat.lr.service.CaseStatusService;
import com.erwat.lr.service.CaseTypeService;
import com.erwat.lr.service.StatusBusinessRuleService;
import com.erwat.lr.service.SubCaseTypeService;


@RestController
@RequestMapping("/TableMaint")
public class CaseTypesController {
	private static String successMessage = "SUCCESS";
	Logger logger = LoggerFactory.getLogger(CaseTypesController.class);
	
	@Autowired
	 CaseTypeService caseTypeService;
	@Autowired
	SubCaseTypeService subCaseTypeService;
	
	@Autowired
	CaseStatusService caseStatusService;
	
	@Autowired
	CaseNatureCategoryMapService caseNatureCategoryMapService;
	
	@Autowired
	CaseCategoryService caseCategoryService;
	
	@Autowired
	CaseOutcomeService caseOutcomeService;
	
	@Autowired
	CaseRoleService caseRoleService;
	
	@Autowired
	CaseNatureService caseNatureService;
	
	@Autowired
	BusinessRuleService businessRuleService;
	
	@Autowired
	StatusBusinessRuleService statusBusinessRuleService;
	
	@GetMapping("/CaseType")
	public ResponseEntity<List<CaseTypes>> getAllCaseTypes(){
		
		List<CaseTypes> caseTypes = caseTypeService.findAll();
	     return ResponseEntity.ok().body(caseTypes);
		
	}
	
	@GetMapping("/CaseType/{id}")
	   public ResponseEntity <CaseTypes> getCaseTypeById(@PathVariable("id") Integer caseId) {
		CaseTypes caseTypes = caseTypeService.findById(caseId);
		return ResponseEntity.ok().body(caseTypes);
	   }
	

	
	@PostMapping(value = "/CaseType")
	public ResponseEntity<?> createCaseType(@RequestBody CaseTypes caseType)  {
		caseType =  caseTypeService.create(caseType);
		return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/CaseType")
	public ResponseEntity<?> updateCaseType(@RequestBody CaseTypes caseType)  {
		caseType =  caseTypeService.update(caseType);
		return ResponseEntity.ok().body(successMessage);
		
	}
	@GetMapping("/tilesCount")
	public ResponseEntity <List<TileCount>> getTileCount(){
		List<TileCount> tileCountList = new ArrayList<TileCount>();
		TileCount tileCount;
		
		//Case Type
		tileCount = new TileCount();
		tileCount.setKey("CaseType");
		tileCount.setCount(caseTypeService.findAll().size());
		tileCountList.add(tileCount);
		
		//Sub Case 
		tileCount = new TileCount();
		tileCount.setKey("SubCaseType");
		tileCount.setCount(subCaseTypeService.findAll().size());
		tileCountList.add(tileCount);
		
		// Case Status
		tileCount = new TileCount();
		tileCount.setKey("CaseStatus");
		tileCount.setCount(caseStatusService.findAll().size());
		tileCountList.add(tileCount);
		
		//Case Category
		tileCount = new TileCount();
		tileCount.setKey("CaseCategory");
		tileCount.setCount(caseCategoryService.findAll().size());
		tileCountList.add(tileCount);
		
		//Case Nature
		tileCount = new TileCount();
		tileCount.setKey("CaseNature");
		tileCount.setCount(caseNatureService.findAll().size());
		tileCountList.add(tileCount);
		// Case Nature Category Map
		tileCount = new TileCount();
		tileCount.setKey("CaseNatureCategory");
		tileCount.setCount(caseNatureCategoryMapService.findAll().size());
		tileCountList.add(tileCount);
		
		// Case Outcome
		tileCount = new TileCount();
		tileCount.setKey("CaseOutcome");
		tileCount.setCount(caseOutcomeService.findAll().size());
		tileCountList.add(tileCount);
		
		//Case Role
		tileCount = new TileCount();
		tileCount.setKey("CaseRole");
		tileCount.setCount(caseRoleService.findAll().size());
		tileCountList.add(tileCount);
		
		tileCount = new TileCount();
		tileCount.setKey("BusinessRule");
		tileCount.setCount(businessRuleService.findAll().size());
		tileCountList.add(tileCount);
		
		tileCount = new TileCount();
		tileCount.setKey("StatusBusinessRule");
		tileCount.setCount(statusBusinessRuleService.findAll().size());
		tileCountList.add(tileCount);
		
		return ResponseEntity.ok().body(tileCountList);
		
	}
	
}
