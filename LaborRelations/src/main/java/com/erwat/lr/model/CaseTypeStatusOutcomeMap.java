package com.erwat.lr.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_MAP_CASE_STATUS_OUTCOMES\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "CaseTypeStatusOutcomeMap.findAll", query = "SELECT ct FROM CaseTypeStatusOutcomeMap ct"),
		@NamedQuery(name = "CaseTypeStatusOutcomeMap.findById", query = "SELECT ct FROM CaseTypeStatusOutcomeMap ct WHERE ct.caseStatusId = :caseStatusId AND ct.caseSubCaseId = :caseSubCaseId AND ct.outcomeId = :outcomeId"),
		@NamedQuery(name = "CaseTypeStatusOutcomeMap.findByCaseStatusId", query = "SELECT ct FROM CaseTypeStatusOutcomeMap ct WHERE ct.caseStatusId = :caseStatusId"),
		@NamedQuery(name = "CaseTypeStatusOutcomeMap.findByCaseSubCaseId", query = "SELECT ct FROM CaseTypeStatusOutcomeMap ct WHERE ct.caseSubCaseId = :caseSubCaseId"),
		@NamedQuery(name = "CaseTypeStatusOutcomeMap.findByOutcomeId", query = "SELECT ct FROM CaseTypeStatusOutcomeMap ct WHERE ct.outcomeId = :outcomeId"),
		@NamedQuery(name = "CaseTypeStatusOutcomeMap.findStatusBySequence", query = "SELECT ct FROM CaseTypeStatusOutcomeMap ct WHERE ct.caseSubCaseId = :caseSubCaseId AND ct.sequence = :sequence"),
		@NamedQuery(name = "CaseTypeStatusOutcomeMap.findByCaseSatusPerCaseSubCase", query = "SELECT ct FROM CaseTypeStatusOutcomeMap ct WHERE ct.caseSubCaseId = :caseSubCaseId AND ct.caseStatusId = :caseStatusId")
})
public class CaseTypeStatusOutcomeMap {

	@Id
	@Column(name = "\"STATUS.ID\"", columnDefinition = "INTEGER")
	private Integer caseStatusId;
	
	@Id
	@Column(name = "\"CASE_SUB_CASE.ID\"", columnDefinition = "INTEGER")
	private Integer caseSubCaseId;
	
	@Id
	@Column(name = "\"OUTCOMES.ID\"", columnDefinition = "INTEGER")
	private Integer outcomeId;
	
	@Column(name = "\"START_DATE\"", columnDefinition = "SECONDDATE")
	private Date sDate;
	
	@Column(name = "\"END_DATE\"", columnDefinition = "SECONDDATE")
	private Date eDate;
	
	@Column(name = "\"STAGE\"", columnDefinition = "INTEGER")
	private Integer stage;
	
	@Column(name = "\"SQN\"", columnDefinition = "INTEGER")
	private Integer sequence;
	
	@Column(name = "\"BUSINESS_STATUS_RULE.ID\"", columnDefinition = "INTEGER")
	private Integer bStatusRuleId;
	
	@ManyToOne
	@JoinColumn(name="\"BUSINESS_STATUS_RULE.ID\"",referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private StatusBusinessRule statusBusinessRule;
	
	@ManyToOne
	@JoinColumn(name="\"STATUS.ID\"",referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseStatus caseStatus;
	
	@ManyToOne
	@JoinColumn(name="\"OUTCOMES.ID\"",referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseOutcome caseOutcome;
	
	public Integer getCaseStatusId() {
		return caseStatusId;
	}

	public void setCaseStatusId(Integer caseStatusId) {
		this.caseStatusId = caseStatusId;
	}

	public Integer getCaseSubCaseId() {
		return caseSubCaseId;
	}

	public void setCaseSubCaseId(Integer caseSubCaseId) {
		this.caseSubCaseId = caseSubCaseId;
	}

	public Integer getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
	}
	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public Integer getbStatusRuleId() {
		return bStatusRuleId;
	}

	public void setbStatusRuleId(Integer bStatusRuleId) {
		this.bStatusRuleId = bStatusRuleId;
	}

	public StatusBusinessRule getStatusBusinessRule() {
		return statusBusinessRule;
	}

	public void setStatusBusinessRule(StatusBusinessRule statusBusinessRule) {
		this.statusBusinessRule = statusBusinessRule;
	}

	public CaseStatus getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(CaseStatus caseStatus) {
		this.caseStatus = caseStatus;
	}

	public CaseOutcome getCaseOutcome() {
		return caseOutcome;
	}

	public void setCaseOutcome(CaseOutcome caseOutcome) {
		this.caseOutcome = caseOutcome;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

}
