package com.techelevator;

public class ChangeMachine {

	 static double getCashIn;
	 static int qtrCounter = 0;
	 static int dimeCounter = 0;
	 static int nickleCounter = 0;
	 static int pennieCounter = 0;
	 static double cashIn = 0;
	 static double changeOut = 0;
	
	
	public int getQtrCounter() {
		return ChangeMachine.qtrCounter;
	}
	public int getDimeCounter() {
		return ChangeMachine.dimeCounter;
	}
	public int getNickleCounter() {
		return ChangeMachine.nickleCounter;
	}
	public int getPennieCounter() {
		return ChangeMachine.pennieCounter;
	}
	public double getCashIn() {
		return ChangeMachine.cashIn;
	}
	public static double getChangeOut() {
		
		while (cashIn >= 25) {
			cashIn = cashIn - 25;
			qtrCounter++;
		}
		while (cashIn >= 10) {
			cashIn = cashIn - 10;
			dimeCounter++;
		}
		while (cashIn >= 5) {
			cashIn = cashIn - 5; 
			nickleCounter++;
		}
		
		while (cashIn < 5 && cashIn > 0) {
			cashIn = cashIn - 1;
			pennieCounter++;
			cashIn = 0;
		}
		
		return changeOut = cashIn;
	}
	
}
