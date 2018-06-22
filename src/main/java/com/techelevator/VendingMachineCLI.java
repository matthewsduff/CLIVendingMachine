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
	private static final String MAIN_MENU_OPTION_FINISH_TRANSACTION = "Enjoy your snacks";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_FEED_MONEY, MAIN_MENU_OPTION_FINISH_TRANSACTION };

	List<String> seperateItemLists = null; // creating list that will populate from the csv file
	List<String> typeList = new ArrayList<String>(); // creating list that will populate from the csv file

	List<Snack> loadedMachine = new ArrayList<Snack>(); // creating the loaded vending machine
	List<PurchasedSnacks> purchasedItems = new ArrayList<PurchasedSnacks>(); // creating empty vending machine
	double usersCash = 0;
	DecimalFormat df = new DecimalFormat("0.00");
	File itemList = new File("vendingmachine.csv");
	{

		try (Scanner fileScan = new Scanner(itemList)) { // starts scanner to go through csv file
			while (fileScan.hasNextLine()) { // checking for next line in csv

				String itemString = fileScan.nextLine(); // creates string from csv line
				String[] item = itemString.split("\\|"); // splits it on the "|"s

				seperateItemLists = Arrays.asList(item); // adds the split items to list

				Double price = Double.parseDouble(item[2]); // parses the string price into a double
				Snack snack = new Snack(item[0], item[1], price, item[3]); // loads the snack class constructor
				PurchasedSnacks pSnack = new PurchasedSnacks(item[0], item[1], price, item[3]); // loads purchased
																								// constructor

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
			} else if (choice.equals(MAIN_MENU_OPTION_FEED_MONEY)) {

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

					//if (!newItem.equals(loadedMachine.get(i).getItemLocation())) {
						
					//	notFound=true;
				//	}
					if (newItem.equals(loadedMachine.get(i).getItemLocation())) {

						if (loadedMachine.get(i).getItemQty() == 0) {
							System.out.println("SOLD OUT");
							System.out.println("Remaing balance = $" + df.format(usersCash));
						}
						
						if (loadedMachine.get(i).getItemPrice() > usersCash) {
							System.out.println("Insufficient funds");
							System.out.println("Remaing balance = $" + df.format(usersCash));
						}

						if (loadedMachine.get(i).getItemQty() > 0 && usersCash > loadedMachine.get(i).getItemPrice()) {
							loadedMachine.get(i).getSoldItem();
							purchasedItems.get(i).getPurchaseItem();
							usersCash = usersCash - purchasedItems.get(i).getItemPrice();
							System.out.println("Remaing balance = $" + df.format(usersCash));
							
						}

					}

				}
				//if (notFound == true) {
				//	System.out.println("Ivalid location");
				//	notFound = false;
				//}
			}
			else if (choice.equals(MAIN_MENU_OPTION_FINISH_TRANSACTION)) {
				int qtrCounter = 0;
				int dimeCounter = 0;
				int nickleCounter = 0;
				int pennieCounter = 0;
				
				String chipSound = "Crunch Crunch, Yum!";
				String candySound = "Munch Munch, MMMmmmm!";
				String drinkSound = "Glug Glug, AHHhhh!";
				String gumSound = "Chew Chew, Yum!";
				
				System.out.println("Your change is $" + df.format(usersCash));
				
				usersCash = usersCash * 100;
				
				while (usersCash >= 25) {
					usersCash = usersCash - 25;
					qtrCounter++;
				}
				while (usersCash >= 10) {
					usersCash = usersCash - 10;
					dimeCounter++;
				}
				while (usersCash >= 5) {
					usersCash = usersCash - 5; //	TODO Need to figure out why the machine isnt giving the right amount of nickels
					
					nickleCounter++;
				}
				while (usersCash < 5 && usersCash > 0) {
					usersCash = usersCash - 1;
					pennieCounter++;
				}
				
				System.out.print("Quarters " + qtrCounter + " |");
				System.out.print(" Dimes " + dimeCounter + " |");
				System.out.print(" Nickles " + nickleCounter + " |");
				System.out.print(" Pennies " + pennieCounter);
				System.out.println();
				System.out.println("Enjoy your Snacks!");
				
				
				for (int i = 0; i < purchasedItems.size(); i++) {
					
					for (int j = 0; j <= purchasedItems.get(i).getItemQty(); j++) {
					if (purchasedItems.get(i).getItemQty() > 0) {
						
						if (purchasedItems.get(i).getItemType().equals("Chip")) {
							System.out.println(chipSound);
							
						}
						if (purchasedItems.get(i).getItemType().equals("Candy")) {
							System.out.println(candySound);
							
						}
						if (purchasedItems.get(i).getItemType().equals("Drink")) {
							System.out.println(drinkSound);
							
						}
						if (purchasedItems.get(i).getItemType().equals("Gum")) {
							System.out.println(gumSound);
							
						}
						
						purchasedItems.get(i).getConsumeItem();
					}
					}
					
				}
				
				
				
				
				

			
		}}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
