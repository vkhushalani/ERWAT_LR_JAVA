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
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_MAP_CASE_CATEGORY_NATURE\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "CaseTypeNatureCategoryMap.findAll", query = "SELECT ct FROM CaseTypeNatureCategoryMap ct"),
		@NamedQuery(name = "CaseTypeNatureCategoryMap.findByCaseSubCaseId", query = "SELECT ct FROM CaseTypeNatureCategoryMap ct WHERE ct.caseSubCaseId = :caseSubCaseId"),
		@NamedQuery(name = "CaseTypeNatureCategoryMap.findByCaseSubCaseCategory", query = "SELECT ct FROM CaseTypeNatureCategoryMap ct WHERE ct.caseSubCaseId = :caseSubCaseId AND ct.caseCategoryId = :caseCategoryId"),
		@NamedQuery(name = "CaseTypeNatureCategoryMap.findByCaseSubCaseNature", query = "SELECT ct FROM CaseTypeNatureCategoryMap ct WHERE ct.caseSubCaseId = :caseSubCaseId AND ct.natureId = :natureId")
})
public class CaseTypeNatureCategoryMap {
	@Id
	@Column(name = "\"CASE_NATURE.ID\"", columnDefinition = "INTEGER")
	private Integer natureId;

	@Id
	@Column(name = "\"CASE_CATEGORY.ID\"", columnDefinition = "INTEGER")
	private Integer caseCategoryId;
	
	@Id
	@Column(name = "\"CASE_SUB_CASE.ID\"", columnDefinition = "INTEGER")
	private Integer caseSubCaseId;
	
	@Column(name = "\"START_DATE\"", columnDefinition = "SECONDDATE")
	private Date sDate;
	
	@Column(name = "\"END_DATE\"", columnDefinition = "SECONDDATE")
	private Date eDate;
	
	@ManyToOne
	@JoinColumn(name="\"CASE_NATURE.ID\"",referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseNature caseNature;
	
	@ManyToOne
	@JoinColumn(name="\"CASE_CATEGORY.ID\"",referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseCategory caseCategory;

	public Integer getNatureId() {
		return natureId;
	}

	public void setNatureId(Integer natureId) {
		this.natureId = natureId;
	}


	public Integer getCaseSubCaseId() {
		return caseSubCaseId;
	}

	public void setCaseSubCaseId(Integer caseSubCaseId) {
		this.caseSubCaseId = caseSubCaseId;
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

	public Integer getCaseCategoryId() {
		return caseCategoryId;
	}

	public void setCaseCategoryId(Integer caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}

	public CaseNature getCaseNature() {
		return caseNature;
	}

	public void setCaseNature(CaseNature caseNature) {
		this.caseNature = caseNature;
	}

	public CaseCategory getCaseCategory() {
		return caseCategory;
	}

	public void setCaseCategory(CaseCategory caseCategory) {
		this.caseCategory = caseCategory;
	}


}
