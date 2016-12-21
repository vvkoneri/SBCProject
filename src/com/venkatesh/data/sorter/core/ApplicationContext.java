package com.venkatesh.data.sorter.core;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

import com.venkatesh.data.sorter.beans.ModelBean;

public class ApplicationContext {
	
	private static final ApplicationContext context = new ApplicationContext();
	private  float openingBalance;
	private  LinkedList<ModelBean> beanList = new LinkedList<ModelBean>();
	
	private ApplicationContext() {
		
	}
	
	public static ApplicationContext getInstance() {
		return context;
	}
	
	public  void fillOpeningBalance(float value) {
		openingBalance = value;
	}
	
	public float getOpeningBalance() {
		return this.openingBalance;
	}
	
	public void addBean(ModelBean bean) {
		beanList.add(bean);
		sort();
	}
	
	public void updateBeanList(LinkedList<ModelBean> beanList) {
		this.beanList = beanList;
		sort();
	}
	
	public  LinkedList<ModelBean> getBeanList() {
		return beanList;
	}
	
	public  void sort() {
		Collections.sort(beanList, new Comparator<ModelBean>(){
			
			public int compare(ModelBean bean1, ModelBean bean2) {
			
					if(bean1.getEntryDate().getTime() < bean2.getEntryDate().getTime()) {
						return -1;
					} else if(bean1.getEntryDate().getTime() > bean2.getEntryDate().getTime()) {
						return 1;
					} else {
						return 0;
					}
			}
		});
	}
	
	public LinkedList<ModelBean> getTheBeansGreaterThanSpecifiedDate(Date entryDate) {
		
		LinkedList<ModelBean> localbeanList = new LinkedList<ModelBean>();
		
		for(ModelBean bean  : beanList) {
			if(bean.getEntryDate().getTime() > entryDate.getTime())
				localbeanList.add(bean);
		}
		
		return sortById(localbeanList); 
	}
	
	public LinkedList<ModelBean> getTheBeansByDate(Date entryDate) {
		
		LinkedList<ModelBean> localbeanList = new LinkedList<ModelBean>();
		
		for(ModelBean bean  : beanList) {
			if(bean.getEntryDate().equals(entryDate))
				localbeanList.add(bean);
		}
		
		return sortById(localbeanList); 
	}
	
	public LinkedList<ModelBean> sortById(LinkedList<ModelBean> thisbeanList) {
		
		Collections.sort(thisbeanList, new Comparator<ModelBean>(){
			
			public int compare(ModelBean bean1, ModelBean bean2) {
			
					if(bean1.getSiNo() < bean2.getSiNo()) {
						return -1;
					} else if(bean1.getSiNo() > bean2.getSiNo()) {
						return 1;
					} else {
						return 0;
					}
			}
		});
		
		return thisbeanList;
		
	}
	
	
	public void printList() {
		
		for(ModelBean bean : beanList) {
			System.out.println(bean.getSiNo() + 
					" " + bean.getEntryDate() + 
					" " + bean.getDieselReceived() + 
					" " + bean.getDieselConsumed() + 
					" " + bean.getDieselBalance());
		}
	}

}
