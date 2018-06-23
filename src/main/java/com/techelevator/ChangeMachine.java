package com.techelevator;

public class ChangeMachine {

		private double getCashIn;
		private int qtrCounter = 0;
		private int dimeCounter = 0;
		private int nickleCounter = 0;
		private int pennieCounter = 0;
		private double cashIn = 0;
		private double changeOut = 0;
		private double changeCalculator;
		
		
		public double getCashIn(double getCashIn) {
			return this.getCashIn;
		}
		public int getQtrCounter() {
			return this.qtrCounter;
		}
		public int getDimeCounter() {
			return this.dimeCounter;
		}
		public int getNickleCounter() {
			return this.nickleCounter;
		}
		public int getPennieCounter() {
			return this.pennieCounter;
		}
		public double getCashIn() {
			return this.cashIn;
		}
		public double getChangeOut() {
			return this.changeOut;
		}
		
		public double getChangeCalculator() {
			while (cashIn >= 25) {
				cashIn = cashIn - 25;
				this.qtrCounter++;
			}
			while (cashIn >= 10) {
				cashIn = cashIn - 10;
				this.dimeCounter++;
			}
			while (cashIn >= 5) {
				cashIn = cashIn - 5; 
				this.nickleCounter++;
			}
			
			while (cashIn < 5 && cashIn > 0) {
				cashIn = cashIn - 1;
				this.pennieCounter++;
			}
			
			return changeOut = cashIn;
		}
		}







	
	
	