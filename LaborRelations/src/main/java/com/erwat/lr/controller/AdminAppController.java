package com.erwat.lr.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erwat.lr.documentRepository.CustomDocument;
import com.erwat.lr.documentRepository.SharedConstants;
import com.erwat.lr.helper.AttachmentsObject;
import com.erwat.lr.helper.LodgedCaseParticipants;
import com.erwat.lr.model.CaseCategory;
import com.erwat.lr.model.CaseNature;
import com.erwat.lr.model.CaseParticipants;
import com.erwat.lr.model.CaseResults;
import com.erwat.lr.model.CaseStatus;
import com.erwat.lr.model.CaseTypeNatureCategoryMap;
import com.erwat.lr.model.CaseTypeRoleMap;
import com.erwat.lr.model.CaseTypeStatusOutcomeMap;
import com.erwat.lr.model.CaseTypeSubTypeMap;
import com.erwat.lr.model.LodgedCase;
import com.erwat.lr.model.StatusBusinessRule;
import com.erwat.lr.model.Users;
import com.erwat.lr.service.CaseCategoryService;
import com.erwat.lr.service.CaseNatureService;
import com.erwat.lr.service.CaseParticipantsService;
import com.erwat.lr.service.CaseResultsService;
import com.erwat.lr.service.CaseStatusService;
import com.erwat.lr.service.CaseTypeNatureCategoryMapService;
import com.erwat.lr.service.CaseTypeRoleMapService;
import com.erwat.lr.service.CaseTypeService;
import com.erwat.lr.service.CaseTypeStatusOutcomeMapService;
import com.erwat.lr.service.CaseTypeSubTypeMapService;
import com.erwat.lr.service.LodgedCaseService;
import com.erwat.lr.service.StatusBusinessRuleService;
import com.erwat.lr.service.SubCaseTypeService;
import com.erwat.lr.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ecm.api.EcmService;

@RestController
@RequestMapping("/Admin")
public class AdminAppController {
	private static String successMessage = "SUCCESS";
Logger logger = LoggerFactory.getLogger(AdminAppController.class);
	
	@Autowired
	LodgedCaseService lodgedCaseService;
	
	@Autowired
	CaseParticipantsService caseParticipantsService;
	
	@Autowired
	CaseResultsService caseResultsService;
	
	@Autowired
	CaseTypeStatusOutcomeMapService caseTypeStatusOutcomeMapService;
	
	@Autowired
	StatusBusinessRuleService statusBusinessRuleService;

	@Autowired
	CaseCategoryService caseCategoryService;
	
	@Autowired
	CaseTypeNatureCategoryMapService caseTypeNatureCategoryMapService;
	
	@Autowired
	 CaseTypeService caseTypeService;
	
	@Autowired
	SubCaseTypeService subCaseTypeService;
	
	@Autowired
	CaseTypeSubTypeMapService caseTypeSubTypeMapService;
	
	@Autowired
	CaseTypeRoleMapService caseTypeRoleMapService;
	
	@Autowired
	CaseStatusService caseStatusService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	CaseNatureService caseNatureService;
	
	@GetMapping(value = "/LodgedCase")
	public ResponseEntity <List<LodgedCase>> getLodgeCase()  {
		
		List<LodgedCase> lodgedCases = lodgedCaseService.findAll();
		Integer statusBRuleId;
		Integer sequence;
		for (LodgedCase lodgedCase :lodgedCases)
		{
			statusBRuleId = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getbStatusRuleId();
			if(statusBRuleId !=null){
			lodgedCase.setStatusBRule(statusBusinessRuleService.findById(statusBRuleId));}
			 sequence = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getSequence();
			lodgedCase.setcStatusSeq(sequence);	
		}
		return ResponseEntity.ok().body(lodgedCases);
	}
	
	@GetMapping("/LodgedCase/{id}")
	public ResponseEntity <?> getCasePerId(@PathVariable("id") Integer lodgedCaseId){

		LodgedCase lodgedCase = lodgedCaseService.findById(lodgedCaseId);
		Integer statusBRuleId = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getbStatusRuleId();
		if(statusBRuleId !=null){
		lodgedCase.setStatusBRule(statusBusinessRuleService.findById(statusBRuleId));}
		lodgedCase.setCaseCategoryText(caseCategoryService.findById(caseTypeNatureCategoryMapService.findByCaseSubCaseNature(lodgedCase.getCaseSubCaseId(), lodgedCase.getNatureId()).getCaseCategoryId()).getDescription());

		Users bUser = usersService.findByIdFromSF(lodgedCase.getOnBehalfEmployee());
		
		if(bUser !=null){
		lodgedCase.setOnBehalfEmployeeName(bUser.getName());
		lodgedCase.setOnBehalfEmployeeUser(bUser);
		}
		Users eUser = usersService.findByIdFromSF(lodgedCase.getEmployeeId());
		
		if(eUser !=null){
			lodgedCase.setEmployeeFirstName(eUser.getName());
			lodgedCase.setEmployeeUser(eUser);
		}
		Integer sequence = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getSequence();
		lodgedCase.setcStatusSeq(sequence);
		List<CaseResults> results = new ArrayList<CaseResults>(); 
		List<CaseResults> cResults = lodgedCase.getResults();
		for (CaseResults result :cResults)
		{	if(result.getAccepted()){
				
			Users accUser = usersService.findByIdFromSF(result.getAcceptedBy());
				if(accUser != null){
				result.setAcceptedByName(accUser.getName());
				result.setAcceptedByUser(accUser);
				}
			
			}
			results.add(result);
		}
		logger.debug("results");
		lodgedCase.setResults(results);
		
		return ResponseEntity.ok().body(lodgedCase);
		}	
	
	@GetMapping("/LodgedCase/{id}/CaseParticipants")
	public ResponseEntity <List<LodgedCaseParticipants>> getCaseParticipantsByLodgedId(@PathVariable("id") Integer lodgedCaseId,
			@RequestParam(value = "withNames", required = true) Boolean withNames
			){
		LodgedCase lodgedCase = lodgedCaseService.findById(lodgedCaseId);
		List<CaseTypeRoleMap> caseTypeRoleMapList = caseTypeRoleMapService.findByStageCaseSubCaseId(lodgedCase.getCaseSubCaseId(),lodgedCase.getStage());
		CaseParticipants cParticipant;
		List<LodgedCaseParticipants> participants = new ArrayList<LodgedCaseParticipants>();
		ArrayList<String> userIDList = new ArrayList<String>();
		
		for(CaseTypeRoleMap caseTypeRoleMap : caseTypeRoleMapList)
		{	LodgedCaseParticipants participant = new LodgedCaseParticipants();
			cParticipant = caseParticipantsService.findByLodgedCaseRoleStage(lodgedCaseId, caseTypeRoleMap.getRoleId(),lodgedCase.getStage());
			participant.setRole(caseTypeRoleMap);
			if(cParticipant == null){
			participant.setOperation("POST");
			CaseParticipants eParticipant = new CaseParticipants();
			eParticipant.setLodgedCaseId(lodgedCaseId);
			eParticipant.setStage(lodgedCase.getStage());
			participant.setParticipant(eParticipant);
			}
			else
			{	
			participant.setOperation("PUT");
			if(withNames){
//				Users partUser = usersService.findByIdFromSF(cParticipant.getParticipantId());
//				cParticipant.setParticipantName(partUser.getName());
//				cParticipant.setParticipantUser(partUser);
				
				userIDList.add(cParticipant.getParticipantId());
			}
			participant.setParticipant(cParticipant);}
			participants.add(participant);
			
		}
		
		if(withNames && userIDList.size()!=0){
			
			JSONArray jsonArr = usersService.findMultipleIdFromSF(userIDList);
			for(LodgedCaseParticipants lCPart: participants)
			{
				if(lCPart.getOperation().equals("PUT")){
				String partIdString = lCPart.getParticipant().getParticipantId();
				for(int i=0;i<jsonArr.size();i++)
				{
					JSONObject partUserObj  = (JSONObject)jsonArr.get(i);
					if(partIdString.equals((String)partUserObj.get("userId")))
						{
							Users pUser = new Users();
							pUser.setId(partIdString);
							pUser.setName( partUserObj.get("firstName").toString() + " " + partUserObj.get("lastName").toString());
							pUser.setUserName(partIdString);
						lCPart.getParticipant().setParticipantName(pUser.getName());
						lCPart.getParticipant().setParticipantUser(pUser);
						}
				}}
			}
		}
		return ResponseEntity.ok().body(participants);
	}
	
	@GetMapping("/LodgedCase/{id}/CaseStatus")
	public ResponseEntity <List<CaseTypeStatusOutcomeMap>> getCaseStatusByLodgedId(@PathVariable("id") Integer lodgedCaseId){
		LodgedCase lodgedCase = lodgedCaseService.findById(lodgedCaseId);
		List<CaseTypeStatusOutcomeMap> caseTypeStatusOutcomeMapList = caseTypeStatusOutcomeMapService.findByCaseSubCaseId(lodgedCase.getCaseSubCaseId());
		HashSet<Object> seen=new HashSet<>();
		caseTypeStatusOutcomeMapList.removeIf(e->!seen.add(e.getCaseStatusId()));
		return ResponseEntity.ok().body(caseTypeStatusOutcomeMapList);
	}
	
	@GetMapping("/LodgedCase/{id}/CaseNatureCategory")
	public ResponseEntity<List<CaseTypeNatureCategoryMap>> getCaseNatureCategoryByLodgedId(@PathVariable("id") Integer lodgedCaseId){
		LodgedCase lodgedCase = lodgedCaseService.findById(lodgedCaseId);
		List<CaseTypeNatureCategoryMap> caseTypeNatureCategoryMapList = caseTypeNatureCategoryMapService.findByCaseSubCaseId(lodgedCase.getCaseSubCaseId());
		return ResponseEntity.ok().body(caseTypeNatureCategoryMapList);
	}
	
	@GetMapping("/LodgedCase/{id}/CaseResults")
	public ResponseEntity <List<CaseResults>> getCaseResultsByLodgedId(@PathVariable("id") Integer lodgedCaseId){
		LodgedCase lodgedCase = lodgedCaseService.findById(lodgedCaseId);
		List<CaseResults> results =  caseResultsService.findByLodgedCaseId(lodgedCaseId);
		List<CaseResults> cResults = new ArrayList<CaseResults>();
		for (CaseResults result :results)
		{	
			CaseResults aResult = caseResultsService.findByLodgedCaseAccepted(lodgedCaseId,true,result.getCaseStatusId());
			if(aResult != null)
			{
				result.setCheckEdit(false);
				
				if(result.getAcceptedBy()!=null){
				Users accUser = usersService.findByIdFromSF(result.getAcceptedBy());
				if(accUser != null){
				result.setAcceptedByName(accUser.getName());
				result.setAcceptedByUser(accUser);
				}
			}
				}
			else
			{result.setCheckEdit(true);}
			cResults.add(result);
			
		}
		CaseResults acceptedResult = caseResultsService.findByLodgedCaseAccepted(lodgedCaseId, true,lodgedCase.getCaseStatusId());
		if(acceptedResult == null){
		CaseResults currentCase = new CaseResults();
		currentCase.setCaseStatusId(lodgedCase.getCaseStatusId());
		currentCase.setCaseStatus(caseStatusService.findById(lodgedCase.getCaseStatusId()));
		currentCase.setLodgedCaseId(lodgedCaseId);
		cResults.add(currentCase);}
		return ResponseEntity.ok().body(cResults);
	}
	@GetMapping("/LodgedCase/{id}/CurrentStatusOutcomes")
	public ResponseEntity <List<CaseTypeStatusOutcomeMap>> getCurrentStatusCaseOutcomes(@PathVariable("id") Integer lodgedCaseId){
		LodgedCase lodgedCase = lodgedCaseService.findById(lodgedCaseId);
		List<CaseTypeStatusOutcomeMap> caseTypeStatusOutcomeMapList = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(), lodgedCase.getCaseStatusId());
		return ResponseEntity.ok().body(caseTypeStatusOutcomeMapList);
	}
	@GetMapping("/LodgedCase/{id}/attachments")
	public ResponseEntity <List<AttachmentsObject>> getAttachmentsPerId(@PathVariable("id") Integer lodgedCaseId) throws NamingException{
		List<AttachmentsObject> resultDoc = new ArrayList<AttachmentsObject>();
		LodgedCase lodgedCase = lodgedCaseService.findById(lodgedCaseId);
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
	
	@GetMapping("/Users")
	public ResponseEntity<?> getAll(@RequestParam(value = "search", required = true) String searchString){
		
		JSONArray items = usersService.findAllSF(searchString);
	     return ResponseEntity.ok().body(items.toString());
		
	}
	
	@GetMapping("/Users/{id}")
	 public ResponseEntity <Users> getById(@PathVariable("id") String id) {
		Users item = usersService.findByIdFromSF(id);
		return ResponseEntity.ok().body(item);
	   }
	
	@GetMapping("/Users/Type/{type}")
	 public ResponseEntity <List<Users>> getByType(@PathVariable("type") String type) {
		List<Users> items = usersService.findByType(type);
		return ResponseEntity.ok().body(items);
	}
	
	// reporting services
	
	@GetMapping("/CaseStatus")
	public ResponseEntity<List<CaseStatus>> getCaseStatuses(){
		
		List<CaseStatus> caseStatuses = caseStatusService.findAll();
	     return ResponseEntity.ok().body(caseStatuses);
		
	}
	
	@GetMapping("/CaseNature")
	public ResponseEntity<List<CaseNature>> getCaseNatures(){
		
		List<CaseNature> CaseNatures = caseNatureService.findAll();
	     return ResponseEntity.ok().body(CaseNatures);
		
	}
	
	@GetMapping("/CaseCategory")
	public ResponseEntity<List<CaseCategory>> getCaseCategories(){
		
		List<CaseCategory> caseCategories = caseCategoryService.findAll();
	     return ResponseEntity.ok().body(caseCategories);
		
	}
	
	@GetMapping("/Reporting")
	public ResponseEntity<List<LodgedCase>> reportingAndSearching(
			@RequestParam(value = "caseNumber", required = false) String caseNumber,
			@RequestParam(value = "parentCaseNumber", required = false) String parentCaseNumber,
			@RequestParam(value = "creationFrom", required = false) @DateTimeFormat(pattern="yyyyMMddHHmm") Date creationFrom,
			@RequestParam(value ="creationTo", required = false) @DateTimeFormat(pattern="yyyyMMddHHmm") Date creationTo,
			@RequestParam(value ="updateFrom", required = false) @DateTimeFormat(pattern="yyyyMMddHHmm") Date updateFrom,
			@RequestParam(value ="updateTo", required = false) @DateTimeFormat(pattern="yyyyMMddHHmm") Date updateTo,
			@RequestParam(value ="caseStatus", required = false)  String caseStatus,
			@RequestParam(value ="caseSubCaseType", required = false) String caseSubCaseType,
			@RequestParam(value ="caseNature", required = false) String caseNature,
			@RequestParam(value ="caseCategory", required = false) String caseCategory,
			@RequestParam(value ="caseStage", required = false) String caseStage,
			@RequestParam(value ="employee", required = false) String employee,
			@RequestParam(value ="raisedBy", required = false) String raisedBy,
			@RequestParam(value ="participant", required = false) String participant,
			@RequestParam(value ="closedCase", required = false) Boolean closedCase,
			@RequestParam(value ="overdueCase", required = false) Boolean overdueCase,
			@RequestParam(value ="externalCase", required = false) Boolean externalCase){
		
		List <LodgedCase>lodgedCases = lodgedCaseService.findAll();
		List<LodgedCase> toRemove ;

		if(caseNumber !=null && caseNumber.length() !=0)
		{	toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				
				if(!(lCase.getCaseNumber().equalsIgnoreCase(caseNumber)))
				{ 
					toRemove.add(lCase);
				}
				
			}
			lodgedCases.removeAll(toRemove);
			
		}
		
		if(parentCaseNumber !=null && parentCaseNumber.length() !=0)
		{	
			toRemove = new ArrayList<LodgedCase>();
			LodgedCase ecase = lodgedCaseService.findByCaseNumber(parentCaseNumber);
			for(LodgedCase lCase : lodgedCases){
				
				if(!(lCase.getParentCaseId() == ecase.getParentCaseId()))
				{ 
					toRemove.add(lCase);
				}
				
			}
			lodgedCases.removeAll(toRemove);
			
		}
		
		if(creationFrom != null && creationTo !=null)
		{	logger.debug(creationFrom.toString());
			toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases)
			{
				   
			        Calendar cal = Calendar.getInstance();  
			        cal.setTime(creationFrom);  
			        cal.set(Calendar.HOUR_OF_DAY, 0);  
			        cal.set(Calendar.MINUTE, 0);  
			        cal.set(Calendar.SECOND, 0);  
			        cal.set(Calendar.MILLISECOND, 0);  
			        creationFrom = cal.getTime();
			         
			        cal.setTime(creationTo);  
			        cal.set(Calendar.HOUR_OF_DAY, 23);  
			        cal.set(Calendar.MINUTE, 59);  
			        cal.set(Calendar.SECOND, 59);  
			        cal.set(Calendar.MILLISECOND, 999);  
			        creationTo = cal.getTime();
			        
				if(!((lCase.getCreatedOn().after(creationFrom) && lCase.getCreatedOn().before(creationTo)) || lCase.getCreatedOn().equals(creationFrom) || lCase.getCreatedOn().equals(creationTo)))
				{
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
		}
		
		if(updateFrom != null && updateTo !=null)
		{
			toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases)
			{
				if(!((lCase.getLastUpdatedOn().after(updateFrom) && lCase.getLastUpdatedOn().before(updateTo)) || lCase.getLastUpdatedOn().equals(updateFrom) || lCase.getLastUpdatedOn().equals(updateTo)))
				{
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
		}
		
		if(caseStatus !=null && caseStatus.length() !=0)
		{
			toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				String statusString = String.valueOf(lCase.getCaseStatusId());
				String [] statusParts = caseStatus.split(",");
				if(!(Arrays.asList(statusParts).contains(statusString)))
				{ 
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
			
		}
		
		if(caseSubCaseType !=null && caseSubCaseType.length() !=0)
		{
			toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				
				String typeString = String.valueOf(lCase.getCaseSubCaseId());
				String [] typeParts = caseSubCaseType.split(",");
				if(!(Arrays.asList(typeParts).contains(typeString)))
				{ 
					toRemove.add(lCase);
				}
				}
			lodgedCases.removeAll(toRemove);
			
			
		}
		
		if(caseNature !=null&& caseNature.length() !=0)
		{	toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				
				String natureString = String.valueOf(lCase.getNatureId());
				String [] natureParts = caseNature.split(",");
				if(!(Arrays.asList(natureParts).contains(natureString)))
				{ 
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
			
		}
		
		if(caseStage !=null&& caseStage.length() !=0)
		{toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				
				String stageString = String.valueOf(lCase.getStage());
				String [] stageParts = caseStage.split(",");
				if(!(Arrays.asList(stageParts).contains(stageString)))
				{ 
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
			
		}
		
		if(caseCategory !=null&& caseCategory.length() !=0)
		{toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				CaseTypeNatureCategoryMap cMap = caseTypeNatureCategoryMapService.findByCaseSubCaseNature(lCase.getCaseSubCaseId(), lCase.getNatureId());
				String categoryString = String.valueOf(cMap.getCaseCategoryId());
				String [] categoryParts = caseCategory.split(",");
				if(!(Arrays.asList(categoryParts).contains(categoryString)))
				{ 
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
			
		}
		
		if(employee !=null&& employee.length() !=0)
		{toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				
				if(!(lCase.getEmployeeId().equalsIgnoreCase(employee)))
				{ 
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
			
		}
		if(raisedBy !=null&& raisedBy.length() !=0)
		{toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				
				if(!(lCase.getOnBehalfEmployee().equalsIgnoreCase(raisedBy)))
				{ 
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
			
		}
		if(participant !=null&& participant.length() !=0)
		{toRemove = new ArrayList<LodgedCase>();
			
			for(LodgedCase lCase : lodgedCases){
				Boolean dFlag = true;
				List<CaseParticipants> participants = caseParticipantsService.findByLodgedCaseStage(lCase.getId(),lCase.getStage());
				for (CaseParticipants part: participants)
				{
					if(part.getParticipantId().equalsIgnoreCase(participant))
					{
						dFlag = false;
						break;
					}
				}
				if(dFlag)
				{ 
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
			
		}
		
		if(closedCase != null && closedCase == true){
			toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				CaseTypeStatusOutcomeMap cTypeStatusOutcomeMap = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lCase.getCaseSubCaseId(), lCase.getCaseStatusId()).get(0);
				if(!(cTypeStatusOutcomeMap.getSequence() == 999))
				{toRemove.add(lCase);}
			}
			lodgedCases.removeAll(toRemove);
		}
		
		if(externalCase != null && externalCase == true){
			toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				if(!(lCase.getExternalCaseRefNo() != null && lCase.getExternalCaseRefNo().length() > 0))
				{toRemove.add(lCase);}
			}
			lodgedCases.removeAll(toRemove);
		}
		Date today = new Date();
		if(overdueCase !=null && overdueCase == true)
		{
			toRemove = new ArrayList<LodgedCase>();
			for(LodgedCase lCase : lodgedCases){
				int bDays;
				CaseTypeStatusOutcomeMap cTypeStatusOutcomeMap = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lCase.getCaseSubCaseId(), lCase.getCaseStatusId()).get(0);
				if(cTypeStatusOutcomeMap.getbStatusRuleId() != null){
				StatusBusinessRule sBRule = statusBusinessRuleService.findById(cTypeStatusOutcomeMap.getbStatusRuleId());
				 bDays = sBRule.getDays().intValue() ;
				}
				else
				{bDays = 5;}
				long diff  = today.getTime() - lCase.getLastUpdatedOn().getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				if(!(diffDays > bDays && cTypeStatusOutcomeMap.getSequence() != 999))
				{
					toRemove.add(lCase);
				}
			}
			lodgedCases.removeAll(toRemove);
		}
		Integer statusBRuleId;
		Integer sequence;
		for (LodgedCase lodgedCase :lodgedCases)
		{
			statusBRuleId = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getbStatusRuleId();
			if(statusBRuleId !=null){
			lodgedCase.setStatusBRule(statusBusinessRuleService.findById(statusBRuleId));}
			 sequence = caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(),lodgedCase.getCaseStatusId()).get(0).getSequence();
			lodgedCase.setcStatusSeq(sequence);	
		}
	    return ResponseEntity.ok().body(lodgedCases);
		
	}
	
	@PostMapping(value = "/LodgedCase")
	public ResponseEntity<?> lodgeCase(@RequestBody LodgedCase lodgedCase,HttpServletRequest request)  {
		String loggedUserId =  request.getUserPrincipal().getName();

		Random rand = new Random();
		Integer id = rand.nextInt(999999999);
		
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		int cYear = cal.get(Calendar.YEAR);
		cal.set(9999, Calendar.DECEMBER, 31); //Year, month and day of month
		Date lastDate = cal.getTime();
		CaseTypeSubTypeMap caseTypeSubTypeMap = caseTypeSubTypeMapService.findById(lodgedCase.getCaseSubCaseId());
		String subCaseDescription = subCaseTypeService.findById(caseTypeSubTypeMap.getSubCaseId()).getDescription();
		String caseDescription = caseTypeService.findById(caseTypeSubTypeMap.getCaseId()).getDescription();
		CaseTypeStatusOutcomeMap firstcaseTypeStatusOutcomeMap = caseTypeStatusOutcomeMapService.findStatusBySequence(lodgedCase.getCaseSubCaseId(),1).get(0);
		lodgedCase.setId(id);
		lodgedCase.setCaseNumber(caseDescription.substring(0, 3).toUpperCase()+"-"+subCaseDescription.substring(0, 3).toUpperCase()+"-"+cYear+"-"+rand.nextInt(99999));
		lodgedCase.setCaseStatusId(firstcaseTypeStatusOutcomeMap.getCaseStatusId());
		lodgedCase.setCreatedBy(loggedUserId);
		lodgedCase.setCreatedOn(today);
		Users empUser = usersService.findByIdFromSF(lodgedCase.getEmployeeId());
		lodgedCase.setEmployeeFirstName(empUser.getName());
		lodgedCase.setEmployeeLastName(empUser.getName());
		lodgedCase.setLastUpdatedBy(loggedUserId);
		lodgedCase.setLastUpdatedOn(today);
		if(lodgedCase.getFollowUpCase()!=null && lodgedCase.getFollowUpCase().length() > 0 ){
			if(lodgedCaseService.findByCaseNumber(lodgedCase.getFollowUpCase()) != null){
				lodgedCase.setParentCaseId(lodgedCaseService.findByCaseNumber(lodgedCase.getFollowUpCase()).getParentCaseId());
			}
			else
			{
				return new ResponseEntity<>(id,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			lodgedCase.setParentCaseId(id);
		}
		lodgedCase.setStage(firstcaseTypeStatusOutcomeMap.getStage());
		lodgedCase.setEndDate(lastDate);
		lodgedCase =  lodgedCaseService.create(lodgedCase);
		return new ResponseEntity<>(id,HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/LodgedCase")
	public ResponseEntity<?> updateCase(@RequestBody LodgedCase lodgedCase,HttpServletRequest request)  {
		LodgedCase eLodgedCase = lodgedCaseService.findById(lodgedCase.getId());
		String loggedUserId =  request.getUserPrincipal().getName();
		Date today = new Date();
		//not allow to change the non changeable fields
				lodgedCase.setCreatedBy(eLodgedCase.getCreatedBy());
				lodgedCase.setCreatedOn(eLodgedCase.getCreatedOn());
				lodgedCase.setCaseNumber(eLodgedCase.getCaseNumber());
				lodgedCase.setParentCaseId(eLodgedCase.getParentCaseId());
				lodgedCase.setLastUpdatedBy(eLodgedCase.getLastUpdatedBy());
				lodgedCase.setLastUpdatedOn(eLodgedCase.getLastUpdatedOn());
				lodgedCase.setStage(eLodgedCase.getStage());
				lodgedCase.setEmployeeId(eLodgedCase.getEmployeeId());
				lodgedCase.setOnBehalfEmployee(eLodgedCase.getOnBehalfEmployee());
				lodgedCase.setEmployeeFirstName(eLodgedCase.getEmployeeFirstName());
				lodgedCase.setEmployeeLastName(eLodgedCase.getEmployeeLastName());
				//other employee details also cant changed from front end
				lodgedCase.setEndDate(eLodgedCase.getEndDate());
				lodgedCase.setStartDate(eLodgedCase.getStartDate());
		
		// Change Case Status
			if(eLodgedCase.getCaseStatusId() != lodgedCase.getCaseStatusId())
			{
				lodgedCase.setLastUpdatedBy(loggedUserId);
				lodgedCase.setLastUpdatedOn(today);
				lodgedCase.setStage(caseTypeStatusOutcomeMapService.findByCaseSatusPerCaseSubCase(lodgedCase.getCaseSubCaseId(), lodgedCase.getCaseStatusId()).get(0).getStage());
			}
			
			
		//Change in followUpcase and Parent case
		if(lodgedCase.getFollowUpCase() != eLodgedCase.getFollowUpCase()){
			if(lodgedCaseService.findByCaseNumber(lodgedCase.getFollowUpCase()) != null){
			lodgedCase.setParentCaseId(lodgedCaseService.findByCaseNumber(lodgedCase.getFollowUpCase()).getParentCaseId());
			}
			else
			{
				return new ResponseEntity<String>("ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			}
		
			
		lodgedCase =  lodgedCaseService.update(lodgedCase);
		return new ResponseEntity<String>(successMessage,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping(value = "/CaseParticipants")
	public ResponseEntity<?> createParicipant(@RequestBody CaseParticipants caseParticipant,HttpServletRequest request)  {
		String loggedUserId =  request.getUserPrincipal().getName();
		Date today = new Date();
		
		caseParticipant.setCreatedBy(loggedUserId);
		caseParticipant.setCreatedOn(today);
		caseParticipant.setUpdatedBy(loggedUserId);
		caseParticipant.setUpdatedOn(today);
		caseParticipant.setParticipantName(usersService.findByIdFromSF(caseParticipant.getParticipantId()).getName());
		caseParticipant.setStage(lodgedCaseService.findById(caseParticipant.getLodgedCaseId()).getStage());
		caseParticipant =  caseParticipantsService.create(caseParticipant);
		return new ResponseEntity<>(caseParticipant.getId(),HttpStatus.CREATED);
		
			
	}
	
	@PutMapping(value = "/CaseParticipants")
	public ResponseEntity<?> updateParicipant(@RequestBody CaseParticipants caseParticipant,HttpServletRequest request)  {
		String loggedUserId =  request.getUserPrincipal().getName();
		Date today = new Date();
		CaseParticipants eCaseParticipant = caseParticipantsService.findById(caseParticipant.getId());
		caseParticipant.setCreatedOn(eCaseParticipant.getCreatedOn());
		caseParticipant.setCreatedBy(eCaseParticipant.getCreatedBy());
		caseParticipant.setLodgedCaseId(eCaseParticipant.getLodgedCaseId());
		caseParticipant.setRoleId(eCaseParticipant.getRoleId());
		caseParticipant.setUpdatedBy(loggedUserId);
		caseParticipant.setUpdatedOn(today);
		caseParticipant.setStage(eCaseParticipant.getStage());
		caseParticipant.setParticipantName(usersService.findByIdFromSF(caseParticipant.getParticipantId()).getName());
		caseParticipant =  caseParticipantsService.update(caseParticipant);
		return new ResponseEntity<>(successMessage,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping(value = "/CaseResults")
	public ResponseEntity<?> createOutcome(@RequestBody CaseResults caseResults,HttpServletRequest request)  {
		String loggedUserId =  request.getUserPrincipal().getName();
		CaseResults eResult;
		Date today = new Date();
		caseResults.setCreatedBy(loggedUserId);
		caseResults.setCreatedOn(today);
		
		if(caseResults.getAccepted()){
			eResult = caseResultsService.findByLodgedCaseAccepted(caseResults.getLodgedCaseId(), true, caseResults.getCaseStatusId());
			if(eResult != null)
			{return new ResponseEntity<>("Error",HttpStatus.CONFLICT);}
			caseResults.setAcceptedOn(today);
			caseResults.setAcceptedBy(loggedUserId);
		}
		else
		{caseResults.setAcceptedOn(null);}
		
		 eResult = caseResultsService.findByLodgedCaseStatusOutcome(caseResults.getLodgedCaseId(),caseResults.getOutcomeId(),caseResults.getCaseStatusId());
		if(eResult == null){
		caseResults =  caseResultsService.create(caseResults);
		return new ResponseEntity<>(caseResults.getId(),HttpStatus.CREATED);
		}
		else
		{return new ResponseEntity<>("Error",HttpStatus.CONFLICT);}
			
	}
	
	@PutMapping(value = "/CaseResults")
	public ResponseEntity<?> updateOutcome(@RequestBody CaseResults caseResults,HttpServletRequest request)  {
		CaseResults eResult = caseResultsService.findByLodgedCaseAccepted(caseResults.getLodgedCaseId(), true, caseResults.getCaseStatusId());
		if(eResult != null)
		{return new ResponseEntity<>("Error",HttpStatus.CONFLICT);}
		String loggedUserId =  request.getUserPrincipal().getName();
		CaseResults ecaseResults = caseResultsService.findById(caseResults.getId());
		Date today = new Date();
		caseResults.setAccepted(true);
		caseResults.setAcceptedOn(today);
		caseResults.setAcceptedBy(loggedUserId);
		caseResults.setCaseStatusId(ecaseResults.getCaseStatusId());
		caseResults.setCreatedBy(ecaseResults.getCreatedBy());
		caseResults.setCreatedOn(ecaseResults.getCreatedOn());
		caseResults.setDescription(ecaseResults.getDescription());
		caseResults.setLodgedCaseId(ecaseResults.getLodgedCaseId());
		caseResults.setOutcomeId(ecaseResults.getOutcomeId());
		caseResults =  caseResultsService.update(caseResults);
		return new ResponseEntity<>(caseResults.getId(),HttpStatus.CREATED);
		
			
	}
	@SuppressWarnings("unchecked")
	@PostMapping(value="/Exporting/{sheet}")
	public ResponseEntity<?> ExportToExcel(@RequestBody Map<String, Object> [] pArray,@PathVariable("sheet") String sheet) throws IOException {
		ObjectMapper oMapper = new ObjectMapper();
		
		 XSSFWorkbook newWorkbook = new XSSFWorkbook();
		 CellStyle headStyle = newWorkbook.createCellStyle();
		 Font headerFont = newWorkbook.createFont();
		 headerFont.setBold(true);
		 headStyle.setFont(headerFont);
	     XSSFSheet newsheet = newWorkbook.createSheet(sheet);
	     int rowNum = 0;
	     int colNum = 0;
	     Map<String, Object> hmap = oMapper.convertValue(pArray[0], Map.class);
	     Row row = newsheet.createRow(rowNum++);
	     for (Object hvalue : hmap.values()) {
	    	 logger.debug("key:"+ hvalue);
				 	Cell cell = row.createCell(colNum++);
			    	cell.setCellValue((String)hvalue);
			    	cell.setCellStyle(headStyle);
				
	    }
	     
		for(int i = 1; i < pArray.length; i++)
		{	
			 row = newsheet.createRow(rowNum++);
			Map<String, Object> map = oMapper.convertValue(pArray[i], Map.class);
			colNum = 0;
			for (Object value : map.values()) {
				logger.debug("value outside:"+ value);
					
					 	Cell cell = row.createCell(colNum++);
				    	cell.setCellValue((String)value);
		       
		    }
		}
			int i=0;
		   for (@SuppressWarnings("unused") String keys : hmap.keySet()) {
			   newsheet.autoSizeColumn(i++);
					
		    }
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    newWorkbook.write(baos);
	    newWorkbook.close();
	    byte[] xls = baos.toByteArray();
	    return ResponseEntity.ok()
	    		.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	    		.body(xls);
		
	}

}
