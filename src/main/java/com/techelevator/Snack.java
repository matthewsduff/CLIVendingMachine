package com.techelevator;

public class Snack {

		private String itemLocation;
		private String itemName;
		private double itemPrice;
		private String itemType;
		private int itemQty;
		
		public Snack(String itemLocation, String itemName, double itemPrice, String itemType) {
			this.itemLocation = itemLocation;
			this.itemName = itemName;
			this.itemPrice = itemPrice;
			this.itemType = itemType;
			this.itemQty = 5;
		}

		public String getItemLocation() {
			return itemLocation;
		}

		public String getItemName() {
			return itemName;
		}

		public double getItemPrice() {
			return itemPrice;
		}

		public String getItemType() {
			return itemType;
		}

		public int getItemQty() {
			return itemQty;
		}
			
		

}