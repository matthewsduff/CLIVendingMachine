package com.techelevator;


import com.techelevator.Snack;
import com.techelevator.PurchasedSnacks;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineCLITest {

	String itemName1 = "Potato Snacks";
	String locationTest1 = "A1";
	double itemPriceTest1 = 3.50;
	int itemQtyTest1 = 5;
	
	String itemName2 = "100 Thousand Grand Bar";
	String locationTest2 = "C1";
	double itemPriceTest2 = 10.00;
	int itemQtyTest2 = 5;
	
	int balanceTest = 0;
	
	
	
	List<Snack> loadedMachine = new ArrayList<Snack>(); // creating the loaded vending machine
	List<PurchasedSnacks> purchasedItems = new ArrayList<PurchasedSnacks>(); // creating empty vending machine
	
	vendingSalesClassForTest vendingTest1 = new vendingSalesClassForTest(itemName1, locationTest1, itemPriceTest1, itemQtyTest1);{
		
	}
	vendingSalesClassForTest vendingTest2 = new vendingSalesClassForTest(itemName2, locationTest2, itemPriceTest2, itemQtyTest2);{
		
	}
	
	//loadedMachine.add.(vendingTest1());
	//purchasedItems.add(vendingTest2());
	
}
