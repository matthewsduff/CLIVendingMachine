package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
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
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE };
	
	
	
	List<String> seperateItemLists = null;
	
	List<String> typeList = new ArrayList<String>();
	Map locationMap = new LinkedHashMap();
	Map priceMap = new LinkedHashMap();
	List<Snack> loadedMachine = new ArrayList<Snack>();
	
	File itemList = new File("vendingmachine.csv"); {
		
	try (Scanner fileScan = new Scanner(itemList)) { 
		while (fileScan.hasNextLine()) {
			
			String itemString = fileScan.nextLine();
			String[] item = itemString.split("\\|");
			
			seperateItemLists = Arrays.asList(item); 
		
			Double price = Double.parseDouble(item[2]);
			Snack snack = new Snack(item[0],item[1],price,item[3]);
			
			loadedMachine.add(snack);
		
		}
		
		//System.out.println(loadedMachine.get(0).getItemName());
		//System.out.println(loadedMachine.get(0).getItemLocation());
		
		
		
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
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				for (int i = 0; i < loadedMachine.size(); i++) {
					System.out.print(loadedMachine.get(i).getItemLocation() + " ");
					System.out.print(loadedMachine.get(i).getItemName() + " ");
					System.out.print("$" + loadedMachine.get(i).getItemPrice() + " ");
					System.out.println("QTY:" + loadedMachine.get(i).getItemQty());
					
				}
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			}
		}
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
