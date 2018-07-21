package com.erwat.lr.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRR_LODGED_CASES\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "LodgedCase.findAll", query = "SELECT lc FROM LodgedCase lc	 "),
		@NamedQuery(name = "LodgedCase.findByCaseNumber", query = "SELECT lc FROM LodgedCase lc	WHERE lc.caseNumber = :caseNumber "),
		@NamedQuery(name = "LodgedCase.findByParentCaseId", query = "SELECT lc FROM LodgedCase lc	WHERE lc.parentCaseId = :parentCaseId "),
		@NamedQuery(name = "LodgedCase.findByEmployeeId", query = "SELECT lc FROM LodgedCase lc	WHERE lc.employeeId = :employeeId "),
		@NamedQuery(name = "LodgedCase.findByNatureId", query = "SELECT lc FROM LodgedCase lc	WHERE lc.natureId = :natureId "),
		@NamedQuery(name = "LodgedCase.findByCaseSubCaseId", query = "SELECT lc FROM LodgedCase lc	WHERE lc.caseSubCaseId = :caseSubCaseId ")
		
})
public class LodgedCase {

	@Id
	@Column(name = "\"ID\"", columnDefinition = "INTEGER")
	private Integer id;
	
	@Column(name = "\"CASE_NUMBER\"",columnDefinition = "VARCHAR(64)")
    private String caseNumber;
	
	@Column(name = "\"CASE_STATUS.ID\"",columnDefinition = "INTEGER")
    private Integer caseStatusId;
	
	@Column(name = "\"FOLLOW_UP_CASE_NUMBER\"",columnDefinition = "VARCHAR(64)")
    private String followUpCase;
	
	@Column(name = "\"PARENT_CASE_ID\"",columnDefinition = "INTEGER")
    private Integer parentCaseId;
	
	@Column(name = "\"STAGE\"",columnDefinition = "INTEGER")
	private Integer stage;
	
	@Column(name = "\"CREATED_BY\"",columnDefinition = "VARCHAR(32)")
	private String createdBy;
	
	@Transient
	private String createdByName;
	
	@Column(name = "\"CREATED_ON\"",columnDefinition = "SECONDDATE")
	private Date createdOn ;
	
	@Column(name = "\"LAST_UPDATED_BY\"",columnDefinition = "VARCHAR(32)")
	private String lastUpdatedBy;
	
	@Transient
	private String lastUpdatedByName;
	
	@Column(name = "\"LAST_UPDATED_ON\"",columnDefinition = "SECONDDATE")
	private Date lastUpdatedOn;
	
	@Column(name = "\"ON_BEHALF_EMPLOYEE\"",columnDefinition = "VARCHAR(32)")
	private String onBehalfEmployee;
	
	@Transient
	private String onBehalfEmployeeName;
	
	@Transient
	private Users onBehalfEmployeeUser;
	
	@Column(name = "\"EMPLOYEE_ID\"",columnDefinition = "VARCHAR(32)")
	private String employeeId;
	
	@Column(name = "\"EMPLOYEE_LAST_NAME\"",columnDefinition = "VARCHAR(32)")
	private String employeeLastName;
	
	@Column(name = "\"EMPLOYEE_FIRST_NAME\"",columnDefinition = "VARCHAR(32)")
	private String employeeFirstName;
	
	@Transient
	private Users employeeUser;
	
	@Column(name = "\"EMPLOYEE_COMPANY\"",columnDefinition = "VARCHAR(64)")
	private String employeeCompany;
	
	@Column(name = "\"EMPLOYEE_DEPARTMENT\"",columnDefinition = "VARCHAR(32)")
	private String employeeDepartment;
	
	@Column(name = "\"JOB_TITLE\"",columnDefinition = "VARCHAR(32)")
	private String jobTitle;
	
	@Column(name = "\"MANAGER_ID\"",columnDefinition = "VARCHAR(32)")
	private String managerId;
	
	@Column(name = "\"MANAGER_NAME\"",columnDefinition = "VARCHAR(32)")
	private String managerName;
	
	@Column(name = "\"SR_MANAGER_ID\"",columnDefinition = "VARCHAR(32)")
	private String srManagerId;
	
	@Column(name = "\"SR_MANAGER_NAME\"",columnDefinition = "VARCHAR(32)")
	private String srManagerName;
	
	@Column(name = "\"CASE_SUB_CASE.ID\"",columnDefinition = "INTEGER")
    private Integer caseSubCaseId;
	
	@Column(name = "\"NATURE.ID\"",columnDefinition = "INTEGER")
    private Integer natureId;
	
	@Column(name = "\"EXTERNAL_CASE_REF_NO\"",columnDefinition = "VARCHAR(32)")
	private String externalCaseRefNo;
	
	@Column(name = "\"ATTACHMENT_ID\"",columnDefinition = "VARCHAR(2048)")
	private String attachmentId;
	
	@Column(name = "\"DESCRIPTION\"",columnDefinition = "VARCHAR(256)")
	private String description;
	
	@Column(name = "\"START_DATE\"",columnDefinition = "SECONDDATE")
	private Date startDate;
	
	@Column(name = "\"END_DATE\"",columnDefinition = "SECONDDATE")
	private Date endDate;
	
	@Transient
	private StatusBusinessRule statusBRule;
	
	@Transient
	private Integer cStatusSeq;
	
	@Transient
	private String caseCategoryText;
	
	@Transient
	private String group;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"CASE_STATUS.ID\"", referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseStatus caseStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"NATURE.ID\"" ,referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseNature caseNature;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"CASE_SUB_CASE.ID\"" ,referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseTypeSubTypeMap caseSubCase;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lodgedCase",targetEntity=CaseParticipants.class,fetch=FetchType.LAZY)
	private List<CaseParticipants> participants;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lodgedCase",targetEntity=CaseResults.class,fetch=FetchType.LAZY)
	private List<CaseResults> results;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public Integer getCaseStatusId() {
		return caseStatusId;
	}

	public void setCaseStatusId(Integer caseStatusId) {
		this.caseStatusId = caseStatusId;
	}

	public String getFollowUpCase() {
		return followUpCase;
	}

	public void setFollowUpCase(String followUpCase) {
		this.followUpCase = followUpCase;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeCompany() {
		return employeeCompany;
	}

	public void setEmployeeCompany(String employeeCompany) {
		this.employeeCompany = employeeCompany;
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getSrManagerId() {
		return srManagerId;
	}

	public void setSrManagerId(String srManagerId) {
		this.srManagerId = srManagerId;
	}

	public String getSrManagerName() {
		return srManagerName;
	}

	public void setSrManagerName(String srManagerName) {
		this.srManagerName = srManagerName;
	}

	public Integer getCaseSubCaseId() {
		return caseSubCaseId;
	}

	public void setCaseSubCaseId(Integer caseSubCaseId) {
		this.caseSubCaseId = caseSubCaseId;
	}

	public Integer getNatureId() {
		return natureId;
	}

	public void setNatureId(Integer natureId) {
		this.natureId = natureId;
	}

	public String getExternalCaseRefNo() {
		return externalCaseRefNo;
	}

	public void setExternalCaseRefNo(String externalCaseRefNo) {
		this.externalCaseRefNo = externalCaseRefNo;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getParentCaseId() {
		return parentCaseId;
	}

	public void setParentCaseId(Integer parentCaseId) {
		this.parentCaseId = parentCaseId;
	}

	public CaseStatus getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(CaseStatus caseStatus) {
		this.caseStatus = caseStatus;
	}

	public CaseNature getCaseNature() {
		return caseNature;
	}

	public void setCaseNature(CaseNature caseNature) {
		this.caseNature = caseNature;
	}

	public CaseTypeSubTypeMap getCaseSubCase() {
		return caseSubCase;
	}

	public void setCaseSubCase(CaseTypeSubTypeMap caseSubCase) {
		this.caseSubCase = caseSubCase;
	}

	public List<CaseParticipants> getParticipants() {
		return participants;
	}

	public void setParticipants(List<CaseParticipants> participants) {
		this.participants = participants;
	}

	public List<CaseResults> getResults() {
		return results;
	}

	public void setResults(List<CaseResults> results) {
		this.results = results;
	}

	public StatusBusinessRule getStatusBRule() {
		return statusBRule;
	}

	public void setStatusBRule(StatusBusinessRule statusBRule) {
		this.statusBRule = statusBRule;
	}

	public String getCaseCategoryText() {
		return caseCategoryText;
	}

	public void setCaseCategoryText(String caseCategoryText) {
		this.caseCategoryText = caseCategoryText;
	}

	public String getOnBehalfEmployee() {
		return onBehalfEmployee;
	}

	public void setOnBehalfEmployee(String onBehalfEmployee) {
		this.onBehalfEmployee = onBehalfEmployee;
	}

	public Integer getcStatusSeq() {
		return cStatusSeq;
	}

	public void setcStatusSeq(Integer cStatusSeq) {
		this.cStatusSeq = cStatusSeq;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}

	public void setLastUpdatedByName(String lastUpdatedByName) {
		this.lastUpdatedByName = lastUpdatedByName;
	}

	public String getOnBehalfEmployeeName() {
		return onBehalfEmployeeName;
	}

	public void setOnBehalfEmployeeName(String onBehalfEmployeeName) {
		this.onBehalfEmployeeName = onBehalfEmployeeName;
	}

	public Users getOnBehalfEmployeeUser() {
		return onBehalfEmployeeUser;
	}

	public void setOnBehalfEmployeeUser(Users onBehalfEmployeeUser) {
		this.onBehalfEmployeeUser = onBehalfEmployeeUser;
	}

	public Users getEmployeeUser() {
		return employeeUser;
	}

	public void setEmployeeUser(Users employeeUser) {
		this.employeeUser = employeeUser;
	}
	
}
