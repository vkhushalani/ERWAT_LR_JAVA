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
@Table(name = "\"com.nga.erwat.lr.v1.db::Table.LRR_CASE_PARTICIPANTS\"", schema = "ERWAT_LR_SCHEMA")
@NamedQueries({ 
		@NamedQuery(name = "CaseParticipants.findAll", query = "SELECT cp FROM CaseParticipants cp	 "),
		@NamedQuery(name = "CaseParticipants.findByLodgedCaseId", query = "SELECT cp FROM CaseParticipants cp WHERE cp.lodgedCaseId = :lodgedCaseId	 "),
		@NamedQuery(name = "CaseParticipants.findByLodgedCaseRoleStage", query = "SELECT cp FROM CaseParticipants cp WHERE cp.lodgedCaseId = :lodgedCaseId	AND cp.roleId = :roleId AND cp.stage = :stage "),
		@NamedQuery(name = "CaseParticipants.findByLodgedCaseStage", query = "SELECT cp FROM CaseParticipants cp WHERE cp.lodgedCaseId = :lodgedCaseId AND cp.stage = :stage "),
		@NamedQuery(name = "CaseParticipants.findByParticipantId", query = "SELECT cp FROM CaseParticipants cp WHERE cp.participantId = :participantId")
})
public class CaseParticipants {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "\"ID\"", columnDefinition = "INTEGER")
	private Integer id;
	
	@Column(name = "\"LODGED_CASES.ID\"",columnDefinition = "INTEGER")
    private Integer lodgedCaseId;
	
	@Column(name = "\"CREATED_BY\"",columnDefinition = "VARCHAR(32)")
    private String createdBy;
	
	@Transient
	private String createdByName;
	
	@Column(name = "\"CREATED_ON\"",columnDefinition = "SECONDDATE")
    private Date createdOn;
	
	@Column(name = "\"UPDATED_BY\"",columnDefinition = "VARCHAR(32)")
    private String updatedBy;
	
	@Transient
	private String updatedByName;
	
	@Column(name = "\"UPDATED_ON\"",columnDefinition = "SECONDDATE")
    private Date updatedOn;
	
	@Column(name = "\"ROLE_MATRIX.ID\"",columnDefinition = "INTEGER")
    private Integer roleId;
	
	@Column(name = "\"DESCRIPTION\"",columnDefinition = "VARCHAR(256)")
    private String description;
	
	@Column(name = "\"PARTICIPANT_ID\"",columnDefinition = "VARCHAR(32)")
    private String participantId;
	
	@Column(name = "\"PARTICIPANT_NAME\"",columnDefinition = "VARCHAR(32)")
    private String participantName;
	
	@Transient
	private Users participantUser;
	
	@Column(name = "\"TYPE\"",columnDefinition = "VARCHAR(11)")
    private String type;
	
	@Column(name = "\"STAGE\"",columnDefinition = "INTEGER")
    private Integer stage;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"LODGED_CASES.ID\"" ,referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private LodgedCase lodgedCase;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "\"ROLE_MATRIX.ID\"" ,referencedColumnName="\"ID\"",insertable=false, updatable=false)
	private CaseRole caseRole;

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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public CaseRole getCaseRole() {
		return caseRole;
	}

	public void setCaseRole(CaseRole caseRole) {
		this.caseRole = caseRole;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public Users getParticipantUser() {
		return participantUser;
	}

	public void setParticipantUser(Users participantUser) {
		this.participantUser = participantUser;
	}

}
