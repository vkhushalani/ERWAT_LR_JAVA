package com.erwat.lr.helper;

import com.erwat.lr.model.CaseParticipants;
import com.erwat.lr.model.CaseTypeRoleMap;

public class LodgedCaseParticipants {
	
	private CaseParticipants participant;
	private CaseTypeRoleMap role;
	private String operation;
	
	public CaseParticipants getParticipant() {
		return participant;
	}
	public void setParticipant(CaseParticipants participant) {
		this.participant = participant;
	}
	public CaseTypeRoleMap getRole() {
		return role;
	}
	public void setRole(CaseTypeRoleMap role) {
		this.role = role;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
