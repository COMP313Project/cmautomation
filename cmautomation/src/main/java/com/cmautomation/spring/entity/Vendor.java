package com.cmautomation.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="vendor")
public class Vendor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vendor_Id")
	private int vendor_Id;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name="vendorName")
	private String vendorName;
	
	@NotNull(message="required")
	@Size(min=1,message="required")
	@Column(name = "description")
	private String description;
	

	@OneToMany(mappedBy="vendor",cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<DefectFixDetail> defectFixDetail;
	
	public Vendor() {
		
	}


	public Vendor(int vendor_Id, String vendorName, List<DefectFixDetail> defectFixDetail) {
		
		this.vendor_Id = vendor_Id;
		this.vendorName = vendorName;
		this.defectFixDetail = defectFixDetail;
	}


	public void addDefectFixDetail(DefectFixDetail theDefectFixDetail) {
		if(defectFixDetail==null) {
			defectFixDetail=new ArrayList<>();
			}
		defectFixDetail.add(theDefectFixDetail);
	}
	public int getVendor_Id() {
		return vendor_Id;
	}


	public void setVendor_Id(int vendor_Id) {
		this.vendor_Id = vendor_Id;
	}


	public String getVendorName() {
		return vendorName;
	}


	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public List<DefectFixDetail> getDefectFixDetail() {
		return defectFixDetail;
	}

	public void setDefectFixDetail(List<DefectFixDetail> defectFixDetail) {
		this.defectFixDetail = defectFixDetail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defectFixDetail == null) ? 0 : defectFixDetail.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((vendorName == null) ? 0 : vendorName.hashCode());
		result = prime * result + vendor_Id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendor other = (Vendor) obj;
		if (defectFixDetail == null) {
			if (other.defectFixDetail != null)
				return false;
		} else if (!defectFixDetail.equals(other.defectFixDetail))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (vendorName == null) {
			if (other.vendorName != null)
				return false;
		} else if (!vendorName.equals(other.vendorName))
			return false;
		if (vendor_Id != other.vendor_Id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Vendor [vendorid=" + vendor_Id + ", vendorName=" + vendorName + ", Description "+description+"]";
	}
	

}
