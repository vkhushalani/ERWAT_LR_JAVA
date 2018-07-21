package com.erwat.lr.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRR_CASE_OUTCOMES\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "CaseResults.findAll", query = "SELECT cp FROM CaseResults cp"),
		@NamedQuery(name = "CaseResults.findByLodgedCaseId", query = "SELECT cp FROM CaseResults cp WHERE cp.lodgedCaseId = :lodgedCaseId"),
		@NamedQuery(name = "CaseResults.findByLodgedCaseStatusId", query = "SELECT cp FROM CaseResults cp WHERE cp.lodgedCaseId = :lodgedCaseId AND cp.caseStatusId = :caseStatusId"),
		@NamedQuery(name = "CaseResults.findByLodgedCaseAccepted", query = "SELECT cp FROM CaseResults cp WHERE cp.lodgedCaseId = :lodgedCaseId AND cp.accepted = :accepted AND cp.caseStatusId = :statusId"),
		@NamedQuery(name = "CaseResults.findByLodgedCaseStatusOutcome", query = "SELECT cp FROM CaseResults cp WHERE cp.lodgedCaseId = :lodgedCaseId AND cp.outcomeId = :outcomeId AND cp.caseStatusId = :statusId")
})
public class CaseResults {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"ID\"", columnDefinition = "INTEGER")
	private Integer id;
	
	@Column(name = "\"LODGED_CASES.ID\"",columnDefinition = "INTEGER")
    private Integer lodgedCaseId;
	
	@Column(name = "\"CASE_STATUS.ID\"",columnDefinition = "INTEGER")
    private Integer caseStatusId;

	@Column(name = "\"OUTCOMES.ID\"",columnDefinition = "INTEGER")
    private Integer outcomeId;
	
	@Column(name = "\"CREATED_BY\"",columnDefinition = "VARCHAR(32)")
    private String createdBy;
	
	@Transient
	private String createdByName;
	
	@Column(name = "\"CREATED_ON\"",columnDefinition = "SECONDDATE")
    private Date createdOn;
	
	@Column(name = "\"ACCEPTED\"",columnDefinition = "BOOLEAN")
    private Boolean accepted;
	
	@Column(name = "\"ACCEPTED_ON\"",columnDefinition = "SECONDDATE")
    private Date acceptedOn;
	
	@Column(name = "\"DESCRIPTION\"",columnDefinition = "VARCHAR(256)")
    private String description;
	
	@Column(name = "\"ACCEPTED_BY\"",columnDefinition = "VARCHAR(32)")
    private String acceptedBy;
	
	@Transient
	private String acceptedByName;
	
	@Transient
	private Boolean checkEdit;
	
	@Transient
	private Users acceptedByUser;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"LODGED_CASES.ID\"" ,referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private LodgedCase lodgedCase;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"CASE_STATUS.ID\"" ,referencedColumnName="\"ID\"",insertable=false, updatable=false) 
	private CaseStatus caseStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"OUTCOMES.ID\"" ,referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseOutcome caseOutcome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLodgedCaseId() {
		return lodgedCaseId;
	}

	public void setLodgedCaseId(Integer lodgedCaseId) {
		this.lodgedCaseId = lodgedCaseId;
	}

	public Integer getCaseStatusId() {
		return caseStatusId;
	}

	public void setCaseStatusId(Integer caseStatusId) {
		this.caseStatusId = caseStatusId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
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

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public Date getAcceptedOn() {
		return acceptedOn;
	}

	public void setAcceptedOn(Date acceptedOn) {
		this.acceptedOn = acceptedOn;
	}

	public String getAcceptedBy() {
		return acceptedBy;
	}

	public void setAcceptedBy(String acceptedBy) {
		this.acceptedBy = acceptedBy;
	}

	public Boolean getCheckEdit() {
		return checkEdit;
	}

	public void setCheckEdit(Boolean checkEdit) {
		this.checkEdit = checkEdit;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public String getAcceptedByName() {
		return acceptedByName;
	}

	public void setAcceptedByName(String acceptedByName) {
		this.acceptedByName = acceptedByName;
	}

	public Users getAcceptedByUser() {
		return acceptedByUser;
	}

	public void setAcceptedByUser(Users acceptedByUser) {
		this.acceptedByUser = acceptedByUser;
	}


}
