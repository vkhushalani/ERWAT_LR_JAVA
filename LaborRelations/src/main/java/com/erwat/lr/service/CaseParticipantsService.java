package com.erwat.lr.service;

import java.util.List;

import com.erwat.lr.model.CaseParticipants;

public interface CaseParticipantsService {
	public List<CaseParticipants> findAll();
	public CaseParticipants update(CaseParticipants item);
	public CaseParticipants create(CaseParticipants item);
	public CaseParticipants findById(Integer id);
	public List<CaseParticipants> findByLodgedCaseId(Integer lodgedCaseId);
	public CaseParticipants findByLodgedCaseRoleStage(Integer lodgedCaseId, Integer roleId,Integer stage);
	public List<CaseParticipants> findByLodgedCaseStage(Integer lodgedCaseId,Integer stage);
	public List<CaseParticipants> findByParticipantId (String participantId);
	public void deleteByObject(CaseParticipants item);
	public void deleteById(Integer id);
}
