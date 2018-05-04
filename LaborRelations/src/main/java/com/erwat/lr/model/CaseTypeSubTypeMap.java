package com.erwat.lr.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_MAP_CASE_SUB_CASE\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "CaseTypeSubTypeMap.findAll", query = "SELECT ct FROM CaseTypeSubTypeMap ct"),
		@NamedQuery(name = "CaseTypeSubTypeMap.findByCaseSubCaseId", query = "SELECT ct FROM CaseTypeSubTypeMap ct WHERE ct.subCaseId = :subCaseId AND ct.caseId = :caseId "),
		@NamedQuery(name = "CaseTypeSubTypeMap.findByCaseId", query = "SELECT ct FROM CaseTypeSubTypeMap ct WHERE ct.caseId = :caseId"),
		@NamedQuery(name = "CaseTypeSubTypeMap.findBySubCaseId", query = "SELECT ct FROM CaseTypeSubTypeMap ct WHERE ct.subCaseId = :subCaseId")
		
})
public class CaseTypeSubTypeMap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"ID\"", columnDefinition = "INTEGER")
	private Integer id;
	
	@Column(name = "\"CASE_TYPE.ID\"", columnDefinition = "INTEGER")
	private Integer caseId;
	
	
	@Column(name = "\"SUB_CASE.ID\"", columnDefinition = "INTEGER")
	private Integer subCaseId;
	
	@Column(name = "\"START_DATE\"", columnDefinition = "SECONDDATE")
	private Date sDate;
	
	@Column(name = "\"END_DATE\"", columnDefinition = "SECONDDATE")
	private Date eDate;

	@Column(name = "\"STAGE\"", columnDefinition = "INTEGER")
	private Integer stage;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"CASE_TYPE.ID\"", referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseTypes caseType;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"SUB_CASE.ID\"", referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private SubCaseType subCaseType;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "caseSubCase",targetEntity=LodgedCase.class,fetch=FetchType.LAZY)
	private List<LodgedCase> lCases;

	public Integer getSubCaseId() {
		return subCaseId;
	}


	public void setSubCaseId(Integer subCaseId) {
		this.subCaseId = subCaseId;
	}


	public Integer getCaseId() {
		return caseId;
	}


	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public CaseTypes getCaseType() {
		return caseType;
	}


	public void setCaseType(CaseTypes caseType) {
		this.caseType = caseType;
	}


	public SubCaseType getSubCaseType() {
		return subCaseType;
	}


	public void setSubCaseType(SubCaseType subCaseType) {
		this.subCaseType = subCaseType;
	}


	public Integer getStage() {
		return stage;
	}


	public void setStage(Integer stage) {
		this.stage = stage;
	}

}
