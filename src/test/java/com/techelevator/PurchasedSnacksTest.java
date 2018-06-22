package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class PurchasedSnacksTest {

	String itemLocationTest = "B2";
	String itemNameTest = "Potato Skins";
	double itemPriceTest = 3.5;
	String itemTypeTest = "Chips";
	int itemQtyTest = 5;
	int purchaseItemTest;
	int consumeItemTest;

	
	
	PurchasedSnacks pSTest = new PurchasedSnacks(itemLocationTest, itemNameTest,itemPriceTest,itemTypeTest);
	{
		int itemQtyTest = 5;
	}
	
	@Test
	public void PurchasedSnackConstructorTest() {
		Assert.assertEquals("Testing Constructor",itemLocationTest, pSTest.getItemLocation());
		Assert.assertEquals("Testing Constructor",itemNameTest, pSTest.getItemName());
		Assert.assertEquals("Testing Constructor",itemPriceTest, pSTest.getItemPrice(), 02);
		Assert.assertEquals("Testing Constructor",itemTypeTest, pSTest.getItemType());
	}
	@Test
	public void PurchasedSnackPurchaseItemTest() {
		pSTest.getPurchaseItem();
		Assert.assertEquals(1, pSTest.getItemQty());
		pSTest.getPurchaseItem();
		pSTest.getPurchaseItem();
		Assert.assertEquals(3, pSTest.getItemQty());
		
	}
	@Test
	public void PurchasedSnackConsumeItemTest() {
		pSTest.getPurchaseItem();
		Assert.assertEquals(1, pSTest.getItemQty());
		pSTest.getPurchaseItem();
		pSTest.getPurchaseItem();
		Assert.assertEquals(3, pSTest.getItemQty());
		pSTest.getConsumeItem();
		Assert.assertEquals(2, pSTest.getItemQty());
		pSTest.getConsumeItem();
		pSTest.getConsumeItem();
		pSTest.getConsumeItem();
		pSTest.getConsumeItem();
		pSTest.getConsumeItem();
		pSTest.getConsumeItem();
		Assert.assertEquals(0, pSTest.getItemQty());
		
	}
	
	
}

