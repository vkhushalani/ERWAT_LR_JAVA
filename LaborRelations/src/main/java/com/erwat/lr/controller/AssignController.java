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

import com.erwat.lr.model.CaseNature;
import com.erwat.lr.model.CaseTypeNatureCategoryMap;
import com.erwat.lr.model.CaseTypeRoleMap;
import com.erwat.lr.model.CaseTypeStatusOutcomeMap;
import com.erwat.lr.model.CaseTypeSubTypeMap;
import com.erwat.lr.service.CaseNatureService;
import com.erwat.lr.service.CaseTypeNatureCategoryMapService;
import com.erwat.lr.service.CaseTypeRoleMapService;
import com.erwat.lr.service.CaseTypeService;
import com.erwat.lr.service.CaseTypeStatusOutcomeMapService;
import com.erwat.lr.service.CaseTypeSubTypeMapService;
import com.erwat.lr.service.SubCaseTypeService;


@RestController
@RequestMapping("/TableMaint")
public class AssignController {
	
	private static String successMessage = "SUCCESS";
	Logger logger = LoggerFactory.getLogger(AssignController.class);
	
	
	@Autowired
	CaseTypeSubTypeMapService caseTypeSubTypeMapService;
	
	@Autowired
	CaseTypeStatusOutcomeMapService caseTypeStatusOutcomeMapService;

	
	@Autowired
	CaseTypeRoleMapService caseTypeRoleMapService;
	
	@Autowired
	 CaseTypeService caseTypeService;
	
	@Autowired
	SubCaseTypeService subCaseTypeService;

	@Autowired
	CaseTypeNatureCategoryMapService caseTypeNatureCategoryMapService;
	
	@Autowired
	CaseNatureService caseNatureService;
	
	@PostMapping(value = "/SubCaseToCase")
	public  ResponseEntity<?> assignSubCaseToCase(@RequestBody CaseTypeSubTypeMap assignBody)  {
		caseTypeSubTypeMapService.create(assignBody);
		return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PostMapping(value = "/CaseStatusCaseOutcomeToCaseSubCase")
	public  ResponseEntity<?> assignStatusToCase(@RequestBody CaseTypeStatusOutcomeMap assignBody)  {
		caseTypeStatusOutcomeMapService.create(assignBody);
		return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PostMapping(value = "/CaseNatureCategoryToCaseSubCase")
	public  ResponseEntity<?> assignCategoryToCase(@RequestBody CaseTypeNatureCategoryMap assignBody)  {
		caseTypeNatureCategoryMapService.create(assignBody);
		return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PostMapping(value = "/CaseRoleToCaseSubCase")
	public  ResponseEntity<?> assignRoleToCase(@RequestBody CaseTypeRoleMap assignBody)  {
		caseTypeRoleMapService.create(assignBody);
		return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/CaseStatusCaseOutcomeToCaseSubCase")
	public  ResponseEntity<?> updateStatusToCase(@RequestBody CaseTypeStatusOutcomeMap assignBody)  {
		caseTypeStatusOutcomeMapService.update(assignBody);
		return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/CaseNatureCategoryToCaseSubCase")
	public  ResponseEntity<?> updateCategoryToCase(@RequestBody CaseTypeNatureCategoryMap assignBody)  {
		caseTypeNatureCategoryMapService.update(assignBody);
		return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/CaseRoleToCaseSubCase")
	public  ResponseEntity<?> updateRoleToCase(@RequestBody CaseTypeRoleMap assignBody)  {
		caseTypeRoleMapService.update(assignBody);
		return ResponseEntity.ok().body(successMessage);
		
	}
	
	@GetMapping("/SubCaseToCase")
	public ResponseEntity<List<CaseTypeSubTypeMap>> getAllSubCaseToCase(){
		
		List<CaseTypeSubTypeMap> caseTypeSubTypeMapList = caseTypeSubTypeMapService.findAll();
	     return ResponseEntity.ok().body(caseTypeSubTypeMapList);
		
	}
	@GetMapping("/SubCaseToCase/{id}")
	public ResponseEntity<CaseTypeSubTypeMap> getSubCaseToCase(@PathVariable("id") Integer caseSubCaseId){
		CaseTypeSubTypeMap caseTypeSubTypeMap = caseTypeSubTypeMapService.findById(caseSubCaseId);
		 return ResponseEntity.ok().body(caseTypeSubTypeMap);
	}
	@GetMapping("/SubCaseToCase/{id}/CaseStatusCaseOutcomeToCaseSubCase")
	   public ResponseEntity <List<CaseTypeStatusOutcomeMap>> getCaseStatusByCaseId(@PathVariable("id") Integer caseSubCaseId) {
	
		
		List<CaseTypeStatusOutcomeMap> caseStatusOutcomeMapList = caseTypeStatusOutcomeMapService.findByCaseSubCaseId(caseSubCaseId);

		return ResponseEntity.ok().body(caseStatusOutcomeMapList);
	  }
	
	@GetMapping("/SubCaseToCase/{id}/CaseNatureCategoryToCaseSubCase")
	   public ResponseEntity <List<CaseTypeNatureCategoryMap>> getCaseCategoryByCaseId(@PathVariable("id") Integer caseSubCaseId) {
		List<CaseTypeNatureCategoryMap> caseNatureCategoryMapList = caseTypeNatureCategoryMapService.findByCaseSubCaseId(caseSubCaseId);
		return ResponseEntity.ok().body(caseNatureCategoryMapList);
	  }
	
	@GetMapping("/SubCaseToCase/{id}/CaseRoleToCaseSubCase")
	   public ResponseEntity <List<CaseTypeRoleMap>> getCaseRoleByCaseId(@PathVariable("id") Integer caseSubCaseId) {
		List<CaseTypeRoleMap> caseRoleMapList = caseTypeRoleMapService.findByCaseSubCaseId(caseSubCaseId);
		return ResponseEntity.ok().body(caseRoleMapList);
	  }
	@GetMapping("/SubCaseToCase/{id}/DistinctCaseNature")
	   public ResponseEntity <List<CaseNature>> getDistictNatureByCaseId(@PathVariable("id") Integer caseSubCaseId) {
		List<CaseTypeNatureCategoryMap> caseTypeNatureCategoryMapList = caseTypeNatureCategoryMapService.findByCaseSubCaseId(caseSubCaseId);
		List<CaseNature> caseNatureList = caseNatureService.findAll();
		List<CaseNature> tempCaseNatureList = new ArrayList<CaseNature>();
		for(CaseTypeNatureCategoryMap caseTypeNatureCategoryMap:caseTypeNatureCategoryMapList)
		{
			for(CaseNature caseNature:caseNatureList)
			{
				if(caseNature.getId().equals(caseTypeNatureCategoryMap.getNatureId())){
				tempCaseNatureList.add(caseNature);
				
				}
			}
		}
		
		if(tempCaseNatureList.size()>0){
			
		caseNatureList.removeAll(tempCaseNatureList);}
		return ResponseEntity.ok().body(caseNatureList);
	  }
}
