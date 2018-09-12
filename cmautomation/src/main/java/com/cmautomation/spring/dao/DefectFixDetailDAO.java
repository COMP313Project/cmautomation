package com.cmautomation.spring.dao;

import java.util.List;

import com.cmautomation.spring.entity.DefectFixDetail;

public interface DefectFixDetailDAO {

	List<DefectFixDetail> getDefectList();
	
	List<DefectFixDetail> getNotDeployedDefectList();


	public void saveDefectFixDetail(DefectFixDetail theDefectFixDetail);

	public DefectFixDetail getDefectFixDetail(int defectId);

	public void deleteDefectFixDetail(int defectId);

	public List<DefectFixDetail> searchDefects(String theSearchName);

}
