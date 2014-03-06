package com.emRoXRIPRAP.logger;


public class Entry {


	private int id;
	private String date;
	private String addressMain;
	private String laborHours;
	private String laborRate;
	private String materialCost;
	private String materialMarkup;
	private String total;
	private String work;
	private String isEntered;

	//constructor
	public Entry(){
		
	}
	//constructor
	public Entry(String date, String isEntered, String addressMain, String laborHours, 
					String laborRate, String materialCost, String materialMarkup,String total, String work){

		this.date = date;
		this.isEntered = isEntered;
		this.addressMain = addressMain;
		this.laborHours = laborHours;
		this.laborRate = laborRate;
		this.materialCost = materialCost;
		this.materialMarkup = materialMarkup;
		this.total = total;
		this.work = work;
		
		return;
		
	}
	public int getId(){
		return id;
	}
	public String getDate() {
		return date;
	}
	public String getIsEntered(){
		return isEntered;
	}
	public String getAddressMain() {
		return addressMain;
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
	public String getTotal() {
		return total;
	}
	public String getWork(){
		return work;
	}


	public void setId(int l) {
		this.id = l;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setIsEntered(String isEntered){
		this.isEntered = isEntered;
	}
	public void setAddressMain(String addressMain) {
		this.addressMain = addressMain;
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
	public void setTotal(String total) {
		this.total = total;
	}
	public void setWork(String work){
		this.work = work;
	}




	



}
