package com.venkatesh.data.sorter.beans;

import java.util.Date;

public class ModelBean extends AbstractBean{
	
	private float dieselReceived;
	private float dieselConsumed;
	private float dieselBalance;
	
	
	public float getDieselReceived() {
		return dieselReceived;
	}
	public void setDieselReceived(float dieselReceived) {
		this.dieselReceived = dieselReceived;
	}
	public float getDieselConsumed() {
		return dieselConsumed;
	}
	public void setDieselConsumed(float dieselConsumed) {
		this.dieselConsumed = dieselConsumed;
	}
	public float getDieselBalance() {
		return dieselBalance;
	}
	public void setDieselBalance(float dieselBalance) {
		this.dieselBalance = dieselBalance;
	}
	
	
}
