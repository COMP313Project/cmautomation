package com.cmautomation.spring.dao;

import java.util.List;

import com.cmautomation.spring.entity.QACheckList;
import com.cmautomation.spring.entity.QACompositeKeyId;

public interface QACheckListDAO {

	List<QACheckList> getQACheckList();

	public void saveQACheckList(QACheckList qaCheckList);

	public QACheckList getQACheckListDetail(int qaComp_Id);

	public void deleteQaCheckList(int qaComp_Id);

}
