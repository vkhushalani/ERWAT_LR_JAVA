package com.erwat.lr.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;

import com.erwat.lr.documentRepository.CustomDocument;
import com.erwat.lr.documentRepository.SharedConstants;
import com.erwat.lr.helper.AttachmentsObject;
import com.erwat.lr.model.CaseParticipants;
import com.erwat.lr.model.CaseResults;
import com.erwat.lr.model.CaseTypeNatureCategoryMap;
import com.erwat.lr.model.CaseTypeStatusOutcomeMap;
import com.erwat.lr.model.CaseTypeSubTypeMap;
import com.erwat.lr.model.LodgedCase;
import com.erwat.lr.model.Users;
import com.erwat.lr.service.CaseCategoryService;
import com.erwat.lr.service.CaseParticipantsService;
import com.erwat.lr.service.CaseResultsService;
import com.erwat.lr.service.CaseTypeNatureCategoryMapService;
import com.erwat.lr.service.CaseTypeService;
import com.erwat.lr.service.CaseTypeStatusOutcomeMapService;
import com.erwat.lr.service.CaseTypeSubTypeMapService;
import com.erwat.lr.service.LodgedCaseService;
import com.erwat.lr.service.StatusBusinessRuleService;
import com.erwat.lr.service.SubCaseTypeService;
import com.erwat.lr.service.UsersService;
import com.sap.ecm.api.EcmService;

@RestController
public class EmployeeAppController {
	
Logger logger = LoggerFactory.getLogger(EmployeeAppController.class);
private static String successMessage = "SUCCESS";
	@Autowired
	LodgedCaseService lodgedCaseService;
	
	@Autowired
	CaseParticipantsService caseParticipantsService;
	
	@Autowired
	CaseResultsService caseResultsService;
	
	@Autowired
	 CaseTypeService caseTypeService;
	
	@Autowired
	SubCaseTypeService subCaseTypeService;
	
	@Autowired
	CaseTypeSubTypeMapService caseTypeSubTypeMapService;
	
	@Autowired
	CaseTypeStatusOutcomeMapService caseTypeStatusOutcomeMapService;
	
	@Autowired
	CaseTypeNatureCategoryMapService caseTypeNatureCategoryMapService;	
	
	@Autowired
	StatusBusinessRuleService statusBusinessRuleService;
	
	@Autowired
	CaseCategoryService caseCategoryService;
	
	@Autowired
	UsersService usersService;

	@PostMapping(value = "/LodgedCase")
	public ResponseEntity<?> create(@RequestBody LodgedCase lodgedCase,HttpServletRequest request)  {
		// restrict user to post and put on the basis of role and the field
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		int cYear = cal.get(Calendar.YEAR);
		cal.set(9999, Calendar.DECEMBER, 31); //Year, month and day of month
		Date lastDate = cal.getTime();
		
		Random rand = new Random();
		Integer id = rand.nextInt(999999999);
		
		String loggedUserId =  request.getUserPrincipal().getName().toUpperCase();
//		if(request.isUserInRole("ManagerRole"))
		
		CaseTypeSubTypeMap caseTypeSubTypeMap = caseTypeSubTypeMapService.findById(lodgedCase.getCaseSubCaseId());
		String subCaseDescription = subCaseTypeService.findById(caseTypeSubTypeMap.getSubCaseId()).getDescription();
		String caseDescription = caseTypeService.findById(caseTypeSubTypeMap.getCaseId()).getDescription();
		CaseTypeStatusOutcomeMap firstcaseTypeStatusOutcomeMap = caseTypeStatusOutcomeMapService.findStatusBySequence(lodgedCase.getCaseSubCaseId(),1).get(0);
		lodgedCase.setId(id);
		lodgedCase.setCaseNumber(caseDescription.substring(0, 3).toUpperCase()+"-"+subCaseDescription.substring(0, 3).toUpperCase()+"-"+cYear+"-"+rand.nextInt(99999));
		lodgedCase.setCaseStatusId(firstcaseTypeStatusOutcomeMap.getCaseStatusId());
		lodgedCase.setCreatedBy(loggedUserId);
		lodgedCase.setCreatedByName(usersService.findById(loggedUserId).getName());
		lodgedCase.setOnBehalfEmployee(loggedUserId);
		lodgedCase.setOnBehalfEmployeeName(usersService.findById(loggedUserId).getName());
		lodgedCase.setCreatedOn(today);
		lodgedCase.setEmployeeId(loggedUserId);
		lodgedCase.setEmployeeLastName(usersService.findById(loggedUserId).getName());
		lodgedCase.setEmployeeFirstName(usersService.findById(loggedUserId).getName());
		lodgedCase.setExternalCaseRefNo(null);
		lodgedCase.setFollowUpCase(null);
		lodgedCase.setLastUpdatedBy(loggedUserId);
		lodgedCase.setLastUpdatedByName(usersService.findById(loggedUserId).getName());
		lodgedCase.setLastUpdatedOn(today);
		lodgedCase.setParentCaseId(id);
		lodgedCase.setStage(firstcaseTypeStatusOutcomeMap.getStage());
		lodgedCase.setEndDate(lastDate);
		//employee details needs to be updated too from SF Linkage.
		
		lodgedCase =  lodgedCaseService.create(lodgedCase);
		return new ResponseEntity<>(id,HttpStatus.CREATED);
		
		
			
	}
	
	@PutMapping(value = "/LodgedCase")
	public ResponseEntity<?> update(@RequestBody LodgedCase lodgedCase,HttpServletRequest request)  {
//		// restrict user to post and put on the basis of role and the fields 
//		String loggedUserId =  request.getUserPrincipal().getName().toUpperCase();
//		Date today = new Date();
//		if(request.isUserInRole("ManagerRole"))
		LodgedCase eLodgedCase = lodgedCaseService.findById(lodgedCase.getId());
		eLodgedCase.setAttachmentId(lodgedCase.getAttachmentId());
		eLodgedCase =  lodgedCaseService.update(eLodgedCase);
//		eLodgedCase.setLastUpdatedBy(loggedUserId);
//		eLodgedCase.setLastUpdatedOn(today);
		return new ResponseEntity<String>(successMessage,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping(value = "/Results")
	public ResponseEntity<?> updateResult(@RequestBody CaseResults result,HttpServletRequest request)  {
//		// restrict user to post and put on the basis of role and the fields 
		String loggedUserId =  request.getUserPrincipal().getName().toUpperCase();
//		if(request.isUserInRole("ManagerRole"))
		Date today = new Date();
		result = caseResultsService.findById(result.getId());
		result.setAccepted(true);
		result.setAcceptedOn(today);
		result.setAcceptedBy(loggedUserId);
		result.setAcceptedByName(usersService.findById(loggedUserId).getName());
		result = caseResultsService.update(result);
		return new ResponseEntity<String>(successMessage,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/LodgedCase")
	public ResponseEntity <List<LodgedCase>> getAllCases(HttpServletRequest request){
		String loggedUserId =  request.getUserPrincipal().getName().toUpperCase();
		List<LodgedCase> lodgedCases = lodgedCaseService.findByEmployeeId(loggedUserId);
		List <CaseParticipants> userCases = caseParticipantsService.findByParticipantId(loggedUserId);
		Integer statusBRuleId;
		Integer sequence;
		for (LodgedCase lodgedCase :lodgedCases)
		{
			statusBRuleId = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getbStatusRuleId();
			sequence = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getSequence();
			if(statusBRuleId !=null){
			lodgedCase.setStatusBRule(statusBusinessRuleService.findById(statusBRuleId));}
			lodgedCase.setcStatusSeq(sequence);
			lodgedCase.setGroup("S");
		}
		//adding participant cases.
		for(CaseParticipants userCase : userCases )
		{
			LodgedCase lCase = lodgedCaseService.findById(userCase.getLodgedCaseId());
			if(userCase.getStage().intValue() == lCase.getStage().intValue()){
			statusBRuleId = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lCase.getCaseSubCaseId(),lCase.getCaseStatusId()).get(0).getbStatusRuleId();
			sequence = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lCase.getCaseSubCaseId(),lCase.getCaseStatusId()).get(0).getSequence();
			if(statusBRuleId !=null){
			lCase.setStatusBRule(statusBusinessRuleService.findById(statusBRuleId));}
			lCase.setcStatusSeq(sequence);
			lCase.setGroup("P");
			lodgedCases.add(lCase);}
			
		}
		
		HashSet<Object> seen=new HashSet<>();
		lodgedCases.removeIf(e->!seen.add(e.getId()));
			
		return ResponseEntity.ok().body(lodgedCases);
	}
	
	@GetMapping("/LodgedCase/{id}")
	public ResponseEntity <?> getCasePerId(@PathVariable("id") Integer lodgedCaseId,HttpServletRequest request){
		String loggedUserId = request.getUserPrincipal().getName().toUpperCase();
		LodgedCase lodgedCase = lodgedCaseService.findById(lodgedCaseId);
		List <CaseParticipants> userCases = caseParticipantsService.findByLodgedCaseId(lodgedCaseId);
		Boolean participated = false;
		for (CaseParticipants userCase :userCases){
			if (userCase.getParticipantId().equalsIgnoreCase(loggedUserId))
				{ participated = true;
				break;}
		}
		if(loggedUserId.equalsIgnoreCase(lodgedCase.getEmployeeId()) || participated ){
		Integer statusBRuleId = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getbStatusRuleId();
		if(statusBRuleId !=null){
		lodgedCase.setStatusBRule(statusBusinessRuleService.findById(statusBRuleId));}
		if(usersService.findById(lodgedCase.getOnBehalfEmployee())!= null){
		lodgedCase.setOnBehalfEmployeeName(usersService.findById(lodgedCase.getOnBehalfEmployee()).getName());
		}
		Integer sequence = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getSequence();
		lodgedCase.setcStatusSeq(sequence);
		
		lodgedCase.setCaseCategoryText(caseCategoryService.findById(caseTypeNatureCategoryMapService.findByCaseSubCaseNature(lodgedCase.getCaseSubCaseId(), lodgedCase.getNatureId()).getCaseCategoryId()).getDescription());
		List<CaseResults> results = new ArrayList<CaseResults>(); 
		List<CaseParticipants> participants = new ArrayList<CaseParticipants>(); 
		List<CaseResults> cResults = lodgedCase.getResults();
		List<CaseParticipants> cParticipants = lodgedCase.getParticipants();
		for (CaseResults result :cResults)
		{	
			if(result.getAccepted())
			{
				if(usersService.findById(result.getAcceptedBy()) != null){
				result.setAcceptedByName(usersService.findById(result.getAcceptedBy()).getName());}
			}
			results.add(result);
		}
		lodgedCase.setResults(results);
		
		for(CaseParticipants participant : cParticipants )
		{
			if(participant.getStage().intValue() == lodgedCase.getStage().intValue() )
			{participants.add(participant);}
		}
		lodgedCase.setParticipants(participants);
		return ResponseEntity.ok().body(lodgedCase);}
		else
		{
			return new ResponseEntity<>("ERROR",HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/LodgedCase/{id}/attachments")
	public ResponseEntity <?> getAttachmentsPerId(@PathVariable("id") Integer lodgedCaseId,HttpServletRequest request) throws NamingException{
		List<AttachmentsObject> resultDoc = new ArrayList<AttachmentsObject>();
		LodgedCase lodgedCase = lodgedCaseService.findById(lodgedCaseId);
		List <CaseParticipants> userCases = caseParticipantsService.findByLodgedCaseId(lodgedCaseId);
		
		String loggedUserId = request.getUserPrincipal().getName().toUpperCase();
		Boolean participated = false;
		for (CaseParticipants userCase :userCases){
			if (userCase.getParticipantId().equalsIgnoreCase(loggedUserId))
				{ participated = true;
				break;}
		}
		
		if(loggedUserId.equalsIgnoreCase(lodgedCase.getEmployeeId())|| participated){
		String attString = lodgedCase.getAttachmentId();
		String[] parts = attString.split(",");

		 CustomDocument cDoc = new CustomDocument();
		 cDoc.setUniqueKey(SharedConstants.REP_KEY);
		 cDoc.setUniqueName(SharedConstants.REP_NAME);
		 Session openCmisSession = null;
		 
		 EcmService ecmSvc = cDoc.getECMService();
		 openCmisSession = ecmSvc.connect(cDoc.getUniqueName(),cDoc.getUniqueKey());
		 
		 AttachmentsObject att;
		 if(attString.length() != 0){
		for (String part : parts)
		{
			Document doc = cDoc.getDocumentBySession(openCmisSession, part);
			att = new AttachmentsObject();
			att.setDocId(doc.getId());
			att.setDocName(doc.getName().split("#")[0]);
			resultDoc.add(att);
		}}
		return ResponseEntity.ok().body(resultDoc);
		}
		else
		{
			return new ResponseEntity<>("ERROR",HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/CaseSubCase")
	public ResponseEntity<List<CaseTypeSubTypeMap>> getAllCaseTypes(){
		
		List<CaseTypeSubTypeMap> caseSubCaseTypes = caseTypeSubTypeMapService.findAll();
		List<CaseTypeSubTypeMap> fCaseSubCases = new ArrayList<CaseTypeSubTypeMap>();
		Date today = new Date();
		for (CaseTypeSubTypeMap caseSubCase : caseSubCaseTypes)
		{
			if(today.after(caseSubCase.getsDate()) && today.before(caseSubCase.geteDate()))
			{
				fCaseSubCases.add(caseSubCase);
				}
			
		}
	     return ResponseEntity.ok().body(fCaseSubCases);
		
	}
	
	@GetMapping("/CaseSubCase/{id}")
	   public ResponseEntity <CaseTypeSubTypeMap> getCaseTypeById(@PathVariable("id") Integer caseSubCaseId) {
		CaseTypeSubTypeMap caseSubCaseType = caseTypeSubTypeMapService.findById(caseSubCaseId);
		return ResponseEntity.ok().body(caseSubCaseType);
	   }
	
	
	@GetMapping("/CaseSubCase/{id}/CaseNatureCategory")
	 public ResponseEntity <List<CaseTypeNatureCategoryMap>> getCaseNatureByCaseId(@PathVariable("id") Integer caseSubCaseId) {
		List<CaseTypeNatureCategoryMap>  caseTypeNatureCategoryMapList = caseTypeNatureCategoryMapService.findByCaseSubCaseId(caseSubCaseId);
		List<CaseTypeNatureCategoryMap> fcaseTypeNatureCategoryMapList = new ArrayList<CaseTypeNatureCategoryMap>();
		Date today = new Date();
		for(CaseTypeNatureCategoryMap caseTypeNatureCategoryMap : caseTypeNatureCategoryMapList)
		{
			if(today.after(caseTypeNatureCategoryMap.getsDate()) && today.before(caseTypeNatureCategoryMap.geteDate()))
			{
				fcaseTypeNatureCategoryMapList.add(caseTypeNatureCategoryMap);
				}
		}
		return ResponseEntity.ok().body(fcaseTypeNatureCategoryMapList);
	  }
	@GetMapping("/Users")
	public ResponseEntity<List<Users>> getAll(){
		
		List<Users> items = usersService.findAll();
	     return ResponseEntity.ok().body(items);
		
	}
	
	@GetMapping("/Users/{id}")
	 public ResponseEntity <Users> getById(@PathVariable("id") String id) {
		Users item = usersService.findById(id);
		return ResponseEntity.ok().body(item);
	   }
	
	

}
