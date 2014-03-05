package com.emRoXRIPRAP.logger;

import android.os.Parcel;
import android.os.Parcelable;

public class Entry {


	private int id;
	private String date;
	private String addressMain;
	private String addressSecondary;
	private String laborHours;
	private String laborRate;
	private String materialCost;
	private String materialMarkup;
	private String workerName;
	private String total;

	//constructor
	public Entry(){
		
	}
	//constructor
	public Entry(String date, String addressMain, String addressSecondary, String laborHours, 
					String laborRate, String materialCost, String materialMarkup, String workerName, String total){
//		this.id= System.currentTimeMillis() + (long)Math.random();
//		Log.d("LOG LOG LOG entry constructor() ", "ID: " + this.id);
		this.date = date;
		this.addressMain = addressMain;
		this.addressSecondary = addressSecondary;
		this.laborHours = laborHours;
		this.laborRate = laborRate;
		this.materialCost = materialCost;
		this.materialMarkup = materialMarkup;
		this.workerName = workerName;
		this.total = total;
		
		return;
		
	}
	public int getId(){
		return id;
	}
	public String getDate() {
		return date;
	}
	public String getAddressMain() {
		return addressMain;
	}
	public String getAddressSecondary() {
		return addressSecondary;
	}
	public String getLaborHours() {
		return laborHours;
	}
	public String getLaborRate() {
		return laborRate;
	}
	public String getMaterialCost() {
		return materialCost;
	}
	public String getMaterialMarkup() {
		return materialMarkup;
	}
	public String getWorkerName() {
		return workerName;
	}
	public String getTotal() {
		return total;
	}


	public void setId(int l) {
		this.id = l;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setAddressMain(String addressMain) {
		this.addressMain = addressMain;
	}
	public void setAddressSecondary(String addressSecondary) {
		this.addressSecondary = addressSecondary;
	}
	public void setLaborHours(String laborHours) {
		this.laborHours = laborHours;
	}
	public void setLaborRate(String laborRate) {
		this.laborRate = laborRate;
	}
	public void setMaterialCost(String materialCost) {
		this.materialCost = materialCost;
	}
	public void setMaterialMarkup(String materialMarkup) {
		this.materialMarkup = materialMarkup;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public void setTotal(String total) {
		this.total = total;
	}




	



}
