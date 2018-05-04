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
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_MAP_CASE_ROLE_MATRIX\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "CaseTypeRoleMap.findAll", query = "SELECT ct FROM CaseTypeRoleMap ct"),
		@NamedQuery(name = "CaseTypeRoleMap.findByRoleId", query = "SELECT ct FROM CaseTypeRoleMap ct WHERE ct.roleId = :roleId"),
		@NamedQuery(name = "CaseTypeRoleMap.findByCaseSubCaseId", query = "SELECT ct FROM CaseTypeRoleMap ct WHERE ct.caseSubCaseId = :caseSubCaseId"),
		@NamedQuery(name = "CaseTypeRoleMap.findByStageCaseSubCaseId", query = "SELECT ct FROM CaseTypeRoleMap ct WHERE ct.caseSubCaseId = :caseSubCaseId AND ct.stage = :stage"),
		@NamedQuery(name = "CaseTypeRoleMap.findById", query = "SELECT ct FROM CaseTypeRoleMap ct WHERE ct.caseSubCaseId = :caseSubCaseId AND ct.stage = :stage AND ct.roleId = :roleId")
})
public class CaseTypeRoleMap {
	@Id
	@Column(name = "\"ROLE_MATRIX.ID\"", columnDefinition = "INTEGER")
	private Integer roleId;
	
	@Id
	@Column(name = "\"CASE_SUB_CASE.ID\"", columnDefinition = "INTEGER")
	private Integer caseSubCaseId;
	
	@Id
	@Column(name = "\"STAGE\"", columnDefinition = "INTEGER")
	private Integer stage;
	
	@Column(name = "\"START_DATE\"", columnDefinition = "SECONDDATE")
	private Date sDate;
	
	@Column(name = "\"END_DATE\"", columnDefinition = "SECONDDATE")
	private Date eDate;
	
	@ManyToOne
	@JoinColumn(name="\"ROLE_MATRIX.ID\"",referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseRole caseRole;

	public Integer getCaseSubCaseId() {
		return caseSubCaseId;
	}


	public void setCaseSubCaseId(Integer caseSubCaseId) {
		this.caseSubCaseId = caseSubCaseId;
	}


	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleeId(Integer roleId) {
		this.roleId = roleId;
	}


	public Integer getStage() {
		return stage;
	}


	public void setStage(Integer stage) {
		this.stage = stage;
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


	public CaseRole getCaseRole() {
		return caseRole;
	}


	public void setCaseRole(CaseRole caseRole) {
		this.caseRole = caseRole;
	}

}
