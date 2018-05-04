package com.erwat.lr.model;

import java.util.Date;

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
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRD_BUSINESS_RULE\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "BusinessRule.findAll", query = "SELECT br FROM BusinessRule br "),
		@NamedQuery(name = "BusinessRule.findByCaseId", query = "SELECT br FROM BusinessRule br WHERE br.caseTypeId = :caseId")
		
})
public class BusinessRule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"ID\"", columnDefinition = "INTEGER")
	private Integer id;
	
	@Column(name = "\"CASE_TYPE.ID\"",columnDefinition = "INTEGER")
     private Integer caseTypeId;
	
	@Transient
	private String caseTypeDescription;
	
	@Column(name = "\"GRIEVANCE_HANDLE_DAYS\"", columnDefinition = "INTEGER")
	private Integer handleDays;
	
	@Column(name = "\"MEETING_TO_HAPPEN_DAYS\"", columnDefinition = "INTEGER")
	private Integer meetingDays;
	
	@Column(name = "\"NOTICE_BEFORE_DAYS\"", columnDefinition = "INTEGER")
	private Integer noticeDays;

	@Column(name = "\"START_DATE\"", columnDefinition = "SECONDDATE")
	private Date sDate;
	
	@Column(name = "\"END_DATE\"", columnDefinition = "SECONDDATE")
	private Date eDate;

	@ManyToOne
	@JoinColumn(name="\"CASE_TYPE.ID\"",referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseTypes caseType;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseTypeId() {
		return caseTypeId;
	}

	public void setCaseTypeId(Integer caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	public String getCaseTypeDescription() {
		return caseTypeDescription;
	}

	public void setCaseTypeDescription(String caseTypeDescription) {
		this.caseTypeDescription = caseTypeDescription;
	}

	public Integer getHandleDays() {
		return handleDays;
	}

	public void setHandleDays(Integer handleDays) {
		this.handleDays = handleDays;
	}

	public Integer getMeetingDays() {
		return meetingDays;
	}

	public void setMeetingDays(Integer meetingDays) {
		this.meetingDays = meetingDays;
	}

	public Integer getNoticeDays() {
		return noticeDays;
	}

	public void setNoticeDays(Integer noticeDays) {
		this.noticeDays = noticeDays;
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
}
