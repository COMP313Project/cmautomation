package com.cmautomation.spring.service;

import java.util.List;

import com.cmautomation.spring.entity.QACheckList;

public interface QACheckListService {

	public List<QACheckList> getQACheckList();

	public void saveQACheckList(QACheckList qaCheckList);

	public QACheckList getQACheckListDetail(int qaComp_Id);

	public void deleteQaCheckList(int qaComp_Id);



}
