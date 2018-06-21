package com.techelevator;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.Snack;
import com.techelevator.PurchasedSnacks;

public class Items {
	public static void main(String[] args) throws FileNotFoundException {

		
		
	File itemList = new File("vendingmachine.csv"); // import vending machine list.csv

		List<String> seperateItemLists = null; //  creating list that will populate from the csv file
		List<String> typeList = new ArrayList<String>(); //creating list that will populate from the csv file
		
		List<Snack> loadedMachine = new ArrayList<Snack>(); // creating the loaded vending machine
		List<PurchasedSnacks> purchasedItems = new ArrayList<PurchasedSnacks>(); // creating empty vending machine
		
	try (Scanner fileScan = new Scanner(itemList)) {  // starts scanner to go through csv file
		while (fileScan.hasNextLine()) { // checking for next line in csv
			
			String itemString = fileScan.nextLine(); // creates string from csv line
			String[] item = itemString.split("\\|"); // splits it on the "|"s
			
			seperateItemLists = Arrays.asList(item); // adds the split items to list
		
			Double price = Double.parseDouble(item[2]); // parses the string price into a double
			Snack snack = new Snack(item[0],item[1],price,item[3]); // loads the snack class constructor
			PurchasedSnacks pSnack = new PurchasedSnacks(item[0],item[1],price,item[3]); // loads purchased constructor
			
			loadedMachine.add(snack); // adds the snacks to the loaded machine
			purchasedItems.add(pSnack); // creates empty "sold" items list
		
		}
		
		double total = 10;
		System.out.println("enter item to purchase");
		Scanner userInput = new Scanner(System.in);
		String newItem = userInput.nextLine();
		for (int i = 0; i < loadedMachine.size(); i++) {
			if (newItem.equals(loadedMachine.get(i).getItemLocation())){
				System.out.println("Item found");
				System.out.println(loadedMachine.get(i).getItemQty());
				loadedMachine.get(i).getSoldItem();
				System.out.println(loadedMachine.get(i).getItemQty());
				System.out.println(loadedMachine.get(i).getItemName());
				System.out.println(loadedMachine.get(i).getItemQty());
				
				purchasedItems.get(i).getPurchaseItem();
				System.out.println(purchasedItems.get(i).getItemQty());
			total = total - purchasedItems.get(i).getItemPrice(); 
			System.out.println(total);
			}
			
		}
		
		
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	} 
	}
