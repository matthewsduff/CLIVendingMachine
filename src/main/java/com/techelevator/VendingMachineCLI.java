package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_FEED_MONEY = "Feed Money into vending Machine";
	private static final String MAIN_MENU_OPTION_FINISH_TRANSACTION = "Enjoy your snacks";
	private static final String MAIN_MENU_OPTION_CHECK_BALANCE = "Check Balance";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_FEED_MONEY, MAIN_MENU_OPTION_FINISH_TRANSACTION };

	List<String> seperateItemLists = null; // creating list that will populate from the csv file
	List<String> typeList = new ArrayList<String>(); // creating list that will populate from the csv file

	List<Snack> loadedMachine = new ArrayList<Snack>(); // creating the loaded vending machine
	List<PurchasedSnacks> purchasedItems = new ArrayList<PurchasedSnacks>(); // creating empty vending machine

	double usersCash = 0;
	double startingCash = usersCash;

	DecimalFormat df = new DecimalFormat("0.00");
	File itemList = new File("vendingmachine.csv");

	SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
	Date now = new Date();
	String strDate = dtf.format(now);

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
			double usersChoice;
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (int i = 0; i < loadedMachine.size(); i++) {
					System.out.print(loadedMachine.get(i).getItemLocation() + " ");
					System.out.print(loadedMachine.get(i).getItemName() + " ");
					System.out.print("$" + loadedMachine.get(i).getItemPrice() + " ");
					System.out.println("QTY:" + loadedMachine.get(i).getItemQty());
					System.out.println();
					System.out.println("Current Balance $ " + df.format(usersCash));

				}
			} else if (choice.equals(MAIN_MENU_OPTION_FEED_MONEY)) {

				Scanner userCashInput = new Scanner(System.in);
				System.out.println("Please feed dollars into the vending machine");
				System.out.println("you may only enter whole dollar amounts i.e. 1, 2, 5, 10, or 20");

				while (!userCashInput.hasNext("1|2|5|10|20")) { // dollar validation
					userCashInput.nextLine();
					System.out.println("you may only enter whole dollar amounts i.e. 1, 2, 5, 10, or 20");

				}
				usersChoice = Double.parseDouble(userCashInput.next());
				usersCash = usersCash + usersChoice;
				System.out.println("Cash in $" + df.format(usersCash));

				try (FileWriter writer = new FileWriter("log.txt", true)) {
					writer.write(strDate + " FEED MONEY:\t$" + df.format(usersChoice) + "\t" + "$"
							+ df.format(usersCash) + "\n");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				boolean notFound = false;
				System.out.println("enter item to purchase");
				Scanner userInput = new Scanner(System.in);
				String newItem = userInput.nextLine();
				double usersStart = usersCash;

				for (int i = 0; i < loadedMachine.size(); i++) {

					 //if (!newItem.equals(loadedMachine.get(i).getItemLocation())) {

					// notFound=true;
					//}
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

							try (FileWriter writer = new FileWriter("log.txt", true)) {
								writer.write(strDate + " " + loadedMachine.get(i).getItemName() + " "
										+ loadedMachine.get(i).getItemLocation() + " " + "$" + df.format(usersStart)
										+ "\t" + "$" + df.format(usersCash) + "\n");
								writer.flush();
								writer.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					}

				}

			} else if (choice.equals(MAIN_MENU_OPTION_FINISH_TRANSACTION)) {
				
				double totalSales = 0;
				double itemSale = 0;
				try (FileWriter writer = new FileWriter("TotalSales.txt",true)) {
					
					writer.write("SALES REPORT: " + strDate );
					writer.write("\r");
					writer.write("\r");
					writer.flush();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for (int i = 0; i < purchasedItems.size(); i++) {
					
					itemSale = (purchasedItems.get(i).getItemQty() * purchasedItems.get(i).getItemPrice());
					totalSales += itemSale;
					
					try (FileWriter writer = new FileWriter("TotalSales.txt", true)) {
						writer.write(purchasedItems.get(i).getItemName() + " | " + purchasedItems.get(i).getItemQty() + "\r");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				try (FileWriter writer = new FileWriter("TotalSales.txt",true)) {
					writer.write("\r");
					writer.write("** TOTAL SALES ** $"+df.format(totalSales));
					writer.write("\r");
					writer.write("\r");
					writer.flush();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
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
				
				usersCash = Math.round(usersCash);
				

				while (usersCash >= 25) {
					usersCash = usersCash - 25;
					qtrCounter++;
				}
				while (usersCash >= 10) {
					usersCash = usersCash - 10;
					dimeCounter++;
				}
				while (usersCash >= 5) {
					usersCash = usersCash - 5; 
					nickleCounter++;
				}
				System.out.println(usersCash);
				while (usersCash < 5 && usersCash > 0) {
					usersCash = usersCash - 1;
					pennieCounter++;
					usersCash = 0;
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

			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
