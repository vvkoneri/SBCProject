package com.venkatesh.data.sorter.beans;

import java.util.Date;

public class AbstractBean {

	protected int siNo;
	protected Date entryDate;
	
	public int getSiNo() {
		return siNo;
	}
	public void setSiNo(int siNo) {
		this.siNo = siNo;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
}
