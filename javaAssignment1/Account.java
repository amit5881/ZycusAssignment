package javaAssignment1;

public class Account {

	public Account() {

	}

	public double interest(int amount) {
		double interest;
		if (amount >= 0 && amount <= 1000) {
			interest = 0;
		} else if (amount >= 1001 && amount <= 5000) {
			interest = (2.5 * amount) / 100;
		} else if (amount >= 5001 && amount <= 10000) {
			interest = (5 * amount) / 100;
		} else {
			interest = (8 * amount) / 100;
		}
		return interest;
	}

	public int interestType(double interest) {
		// int noInterestCount=0;
		// if(interest==0){
		// noInterestCount++;
		// }
		// return noInterestCount;
		return ++AccountTest.noInterestCount;
	}
}
