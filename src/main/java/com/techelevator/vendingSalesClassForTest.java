package com.techelevator;

public class vendingSalesClassForTest {

	private String itemName = "";
	private String locationTest = "";
	private double itemPriceTest = 0;
	private int itemQtyTest = 0;
	private int balanceTest = 0;
	
	
	vendingSalesClassForTest (String itemName, String locationTest, double itemPriceTest, int itemQtyTest){
		
		this.itemName = itemName;
		this.locationTest = locationTest;
		this.itemPriceTest = itemPriceTest;
		this.itemQtyTest = itemQtyTest;
	}


	public String getItemName() {
		return itemName;
	}


	public String getLocationTest() {
		return locationTest;
	}


	public double getItemPriceTest() {
		return itemPriceTest;
	}


	public int getItemQtyTest() {
		return itemQtyTest;
	}
	public int getBalanceTest() {
		return balanceTest;
	}


	
}
