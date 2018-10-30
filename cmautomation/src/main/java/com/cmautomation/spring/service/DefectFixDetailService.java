package com.cmautomation.spring.service;

import java.util.List;

import com.cmautomation.spring.entity.DefectFixDetail;

public interface DefectFixDetailService {

	public List<DefectFixDetail> getDefectList();
	
	public List<DefectFixDetail> getNotDeployedDefectList();

	public void saveDefectFixDetail(DefectFixDetail theDefectFixDetail);

	public DefectFixDetail getDefectFixDetail(int defectId);

	public void deleteDefectFixDetail(int defectId);

	public List<DefectFixDetail> searchDefects(String theSearchName);

}
