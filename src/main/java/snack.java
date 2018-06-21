
public class snack {

		private String itemLocation;
		private String itemName;
		private String itemPrice;
		private String itemType;
		private int itemQty;
		
		public snack(String itemLocation, String itemName, String itemPrice, String itemType, int itemQty ) {
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

		public String getItemPrice() {
			return itemPrice;
		}

		public String getItemType() {
			return itemType;
		}

		public int getItemQty() {
			return itemQty;
		}
			
		

}