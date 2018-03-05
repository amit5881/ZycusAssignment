package javaAssignment1;

import java.util.Scanner;

public class AccountTest {

	static int noInterestCount = 0;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Account ac = new Account();

		System.out.print("Enter no of Account (N): ");
		int n = s.nextInt();
		int totalInterest = 0;
		int lowestInterest = 0;
		int highestInterest = 0;
		// int zeroInterestAccount = 0;
		for (int i = 1; i <= n; i++) {
			System.out.print("Enter the amount for account number " + i + ": ");
			int amount = s.nextInt();
			int interest = (int) ac.interest(amount);
			if (lowestInterest > interest) {
				lowestInterest = interest;
			}
			if (highestInterest < interest) {
				highestInterest = interest;
			}
			// zeroInterestAccount += ac.interestType(interest);
			if (interest == 0) {
				ac.interestType(interest);
			}
			totalInterest += interest;
			System.out.println("The interest for account number " + i + " is $" + interest);
		}

		float averageInterest = (float) totalInterest / n;

		System.out.println();
		System.out.println("-------------------Interest Details---------------------------");

		System.out.println("Total Interest: $" + totalInterest);
		System.out.println("Lowest Interest: $" + lowestInterest);
		System.out.println("Highest Interest: $" + highestInterest);
		System.out.println("Average Interest: $" + averageInterest);
		System.out.println("Number of accounts with no interest: " + noInterestCount);

		System.out.println("---------------------------------------------------------------");

		s.close();
	}

}
