package com.techelevator;

public class ChangeMachine {

		private double getCashIn;
		private int qtrCounter = 0;
		private int dimeCounter = 0;
		private int nickleCounter = 0;
		private int pennieCounter = 0;
	
	
		private double changeCalculator;
		private double balance;
		
		
		public double getCashIn(double getCashIn) {
			return this.balance = getCashIn;
		}
		
		public double getBalance() {
			return this.balance;
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
		
		
		public double getChangeCalculator(double balance) {
			while (balance >= 25) {
				balance = balance - 25;
				this.qtrCounter++;
			}
			while (balance >= 10) {
				balance = balance - 10;
				this.dimeCounter++;
			}
			while (balance >= 5) {
				balance = balance - 5; 
				this.nickleCounter++;
			}
			
			while (balance < 5 && balance > 0) {
				balance = balance - 1;
				this.pennieCounter++;
			}
			
			return balance = 0;
		}
		}







	
	
	