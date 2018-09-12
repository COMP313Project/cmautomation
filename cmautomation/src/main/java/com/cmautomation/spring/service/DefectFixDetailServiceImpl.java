package com.cmautomation.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmautomation.spring.dao.DefectFixDetailDAO;
import com.cmautomation.spring.entity.DefectFixDetail;

@Service
public class DefectFixDetailServiceImpl implements DefectFixDetailService {

	@Autowired
	private DefectFixDetailDAO defectFixDetailDAO;
	
	// to get the list of defects
	@Override
	@Transactional
	public List<DefectFixDetail> getDefectList() {
		
		return defectFixDetailDAO.getDefectList();
	}
	
	// to get the list of defects
		@Override
		@Transactional
		public List<DefectFixDetail> getNotDeployedDefectList(){
			
			return defectFixDetailDAO.getNotDeployedDefectList();
		}

	
	
	// save a new entry
	@Override
	@Transactional
	public void saveDefectFixDetail(DefectFixDetail theDefectFixDetail) {
		
		defectFixDetailDAO.saveDefectFixDetail(theDefectFixDetail);
		
	}

	// get the defect fix detail back to the form for update
	@Override
	@Transactional
	public DefectFixDetail getDefectFixDetail(int defectId) {
		
		return defectFixDetailDAO.getDefectFixDetail(defectId);
	}

	@Override
	@Transactional
	public void deleteDefectFixDetail(int defectId) {
		
		defectFixDetailDAO.deleteDefectFixDetail(defectId);
		
	}

	@Override
	@Transactional
	public List<DefectFixDetail> searchDefects(String theSearchName) {
		
		return defectFixDetailDAO.searchDefects(theSearchName);
	}

}
