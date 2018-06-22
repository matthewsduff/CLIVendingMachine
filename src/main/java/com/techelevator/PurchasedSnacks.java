package com.techelevator;

public class PurchasedSnacks {

		private String itemLocation;
		private String itemName;
		private double itemPrice;
		private String itemType;
		private int itemQty;
		private int purchaseItem;
		private int consumeItem;
		
		
		
		public PurchasedSnacks(String itemLocation, String itemName, double itemPrice, String itemType) {
			this.itemLocation = itemLocation;
			this.itemName = itemName;
			this.itemPrice = itemPrice;
			this.itemType = itemType;
			this.itemQty = 0;
		}
		
		public int getPurchaseItem() {
			return this.itemQty = this.itemQty+1;
		}
		public int getConsumeItem() {
			return this.itemQty = this.itemQty-1;
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