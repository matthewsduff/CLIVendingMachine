package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	
	
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_FEED_MONEY = "Feed Money into vending Machine";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_FEED_MONEY };

	
	
	List<String> seperateItemLists = null; //  creating list that will populate from the csv file
	List<String> typeList = new ArrayList<String>(); //creating list that will populate from the csv file
	
	List<Snack> loadedMachine = new ArrayList<Snack>(); // creating the loaded vending machine
	List<PurchasedSnacks> purchasedItems = new ArrayList<PurchasedSnacks>(); // creating empty vending machine
	double usersCash = 0;
	DecimalFormat df = new DecimalFormat("0.00");
	File itemList = new File("vendingmachine.csv");
	{

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

			// System.out.println(loadedMachine.get(0).getItemName());
			// System.out.println(loadedMachine.get(0).getItemLocation());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (int i = 0; i < loadedMachine.size(); i++) {
					System.out.print(loadedMachine.get(i).getItemLocation() + " ");
					System.out.print(loadedMachine.get(i).getItemName() + " ");
					System.out.print("$" + loadedMachine.get(i).getItemPrice() + " ");
					System.out.println("QTY:" + loadedMachine.get(i).getItemQty());

				}
			}
			else if (choice.equals(MAIN_MENU_OPTION_FEED_MONEY)) {
				
				
				Scanner userCashInput = new Scanner(System.in);
				System.out.println("Please feed dollars into the vending machine");
				System.out.println("you may only enter whole dollar amounts i.e. 1, 5, 10, or 20");
				
				while (!userCashInput.hasNext("[1, 2, 5, 10, 20]")) { // dollar validation
					userCashInput.next();
					System.out.println("you may only enter whole dollar amounts i.e. 1, 5, 10, or 20");
				
				
				}
				usersCash = usersCash + Double.parseDouble(userCashInput.next());
				System.out.println("Cash in $" + df.format(usersCash));
				
			}
			
			else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				
			
			boolean notFound = false;
				System.out.println("enter item to purchase");
				Scanner userInput = new Scanner(System.in);
				String newItem = userInput.nextLine();
				for (int i = 0; i < loadedMachine.size(); i++) {
					
					if (newItem.equals(loadedMachine.get(i).getItemLocation())){
						System.out.println("Item found");
						if(loadedMachine.get(i).getItemQty() > 0 && loadedMachine.get(i).getItemPrice() > 0 )
						{
							loadedMachine.get(i).getSoldItem();
							purchasedItems.get(i).getPurchaseItem();
						}
						if (!newItem.equals(loadedMachine.get(i).getItemLocation())){
							System.out.println("Location not found, please select again");
						}
						System.out.println(loadedMachine.get(i).getItemQty());
						System.out.println(purchasedItems.get(i).getItemQty());
						
						usersCash = usersCash - purchasedItems.get(i).getItemPrice(); 
						System.out.println("$"+df.format(usersCash));
					}
					
				}
				
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
