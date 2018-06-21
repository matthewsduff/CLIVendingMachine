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

public class Items {
	public static void main(String[] args) throws FileNotFoundException {

		
		
	File itemList = new File("vendingmachine.csv"); {

		List<String> seperateItemLists = null;
		
		List<String> typeList = new ArrayList<String>();
		Map locationMap = new LinkedHashMap();
		Map priceMap = new LinkedHashMap();
		List<Snack> loadedMachine = new ArrayList<Snack>();
		
	try (Scanner fileScan = new Scanner(itemList)) { 
		while (fileScan.hasNextLine()) {
			
			String itemString = fileScan.nextLine();
			String[] item = itemString.split("\\|");
			
			seperateItemLists = Arrays.asList(item); 
		
			Double price = Double.parseDouble(item[2]);
			Snack snack = new Snack(item[0],item[1],price,item[3]);
			
			loadedMachine.add(snack);
		
		}
		
		System.out.println(loadedMachine.get(0).getItemName());
		System.out.println(loadedMachine.get(0).getItemLocation());
		
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}}
}