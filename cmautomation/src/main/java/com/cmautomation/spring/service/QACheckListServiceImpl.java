package com.cmautomation.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmautomation.spring.dao.QACheckListDAO;
import com.cmautomation.spring.entity.QACheckList;
import com.cmautomation.spring.entity.QACompositeKeyId;
@Service
public class QACheckListServiceImpl implements QACheckListService {

	@Autowired
	private QACheckListDAO qaCheckListDAO;
	
	@Override
	@Transactional
	public List<QACheckList> getQACheckList() {
		
		
		return qaCheckListDAO.getQACheckList();
	}

	@Override
/*<<<<<<< HEAD
	public void saveQACheckList(QACheckList qaCheckList) {
		// TODO Auto-generated method stub
		
	}

=======*/
	@Transactional
	public void saveQACheckList(QACheckList qaCheckList) {
		
		qaCheckListDAO.saveQACheckList(qaCheckList);
	}

	@Override
	@Transactional
	public QACheckList getQACheckListDetail(int qaComp_Id) {
		
		return qaCheckListDAO.getQACheckListDetail(qaComp_Id);
	}

	@Override
	@Transactional
	public void deleteQaCheckList(int qaComp_Id) {
		
		qaCheckListDAO.deleteQaCheckList(qaComp_Id);
	}

	

//>>>>>>> e660ebc5d0930df05408e2f2bc519126dc2e8e43
}
