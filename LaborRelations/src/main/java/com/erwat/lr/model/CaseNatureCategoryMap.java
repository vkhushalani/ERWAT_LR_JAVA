package com.erwat.lr.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_MAP_CATEGORY_NATURE\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "CaseNatureCategoryMap.findAll", query = "SELECT ct FROM CaseNatureCategoryMap ct"),
		@NamedQuery(name = "CaseNatureCategoryMap.findByCaseCategoryId", query = "SELECT ct FROM CaseNatureCategoryMap ct WHERE ct.caseCategoryId = :caseCategoryId"),
		@NamedQuery(name = "CaseNatureCategoryMap.findByCaseNatureId", query = "SELECT ct FROM CaseNatureCategoryMap ct WHERE ct.natureId = :natureId"),
		@NamedQuery(name = "CaseNatureCategoryMap.findById", query = "SELECT ct FROM CaseNatureCategoryMap ct WHERE ct.natureId = :natureId AND ct.caseCategoryId = :caseCategoryId")
		
})
public class CaseNatureCategoryMap {
	
	@Id
	@Column(name = "\"CASE_CATEGORY.ID\"", columnDefinition = "INTEGER")
	private Integer caseCategoryId;
	
	@Id
	@Column(name = "\"CASE_NATURE.ID\"", columnDefinition = "INTEGER")
	private Integer natureId;
	
	@Column(name = "\"START_DATE\"", columnDefinition = "SECONDDATE")
	private Date sDate;
	
	@Column(name = "\"END_DATE\"", columnDefinition = "SECONDDATE")
	private Date eDate;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"CASE_NATURE.ID\"" ,referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseNature caseNature;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"CASE_CATEGORY.ID\"" ,referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseCategory caseCategory;

	public Integer getCaseCategoryId() {
		return caseCategoryId;
	}


	public void setCaseCategoryId(Integer caseId) {
		this.caseCategoryId = caseId;
	}


	public Date geteDate() {
		return eDate;
	}


	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}


	public Date getsDate() {
		return sDate;
	}


	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}



	public Integer getNatureId() {
		return natureId;
	}


	public void setNatureId(Integer natureId) {
		this.natureId = natureId;
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
