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

import com.erwat.lr.model.CaseRole;
import com.erwat.lr.service.CaseRoleService;

@RequestMapping("/TableMaint")
@RestController
public class CaseRoleController {
	private static String successMessage = "SUCCESS";
	Logger logger = LoggerFactory.getLogger(CaseRoleController.class);
	@Autowired
	CaseRoleService caseRoleService;

	@GetMapping("/CaseRole")
	public ResponseEntity<List<CaseRole>> getAll(){
		
		List<CaseRole> caseRoles = caseRoleService.findAll();
	     return ResponseEntity.ok().body(caseRoles);
		
	}
	
	@GetMapping("/CaseRole/{id}")
	 public ResponseEntity <CaseRole> getById(@PathVariable("id") Integer id) {
		CaseRole caseRole = caseRoleService.findById(id);
		return ResponseEntity.ok().body(caseRole);
	   }
	@PostMapping(value = "/CaseRole")
	public ResponseEntity<?> create(@RequestBody CaseRole caseRole)  {
		caseRole =  caseRoleService.create(caseRole);
		 return ResponseEntity.ok().body(successMessage);
		
	}
	
	@PutMapping(value = "/CaseRole")
	public ResponseEntity<?> update(@RequestBody CaseRole caseRole)  {
		caseRole =  caseRoleService.update(caseRole);
		 return ResponseEntity.ok().body(successMessage);
		
	}
}
