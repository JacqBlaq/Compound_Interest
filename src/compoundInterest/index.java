package compoundInterest;
import java.awt.Font;
import java.awt.Image;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * Jacquelyn Gboyor
 * 09/08/2018
 * Compound Interest HW
 */

/*
 * The terms in the formula are:

A is the amount of money in the account after the specified number of years.

P is the principal amount that was originally deposited into the account.

r is the annual interest rate.

n is the number of times per year that the interest is compounded.

t is the specified number of years.

Write a program that makes the calculation for you. 
The program should ask the user to input the following:

The amount of principal originally deposited into the account
The annual interest rate paid by the account
The number of times per year that the interest is compounded 
(For example, if interest is compounded monthly, enter 12. If interest is compounded quarterly, enter 4.)
The number of years the account will be left to earn interest
Once the input data has been entered, the program should calculate and display 
the amount of money that will be in the account after the specified number of years.
 */

public class index {

	private static Compound_Interest cpInterest = new Compound_Interest();
	private static DecimalFormat df2 = new DecimalFormat(".##");

	public static void main(String[] args) {
		
		ImageIcon icon = new ImageIcon("src/images/girl-with-calculator.png");
		Image image = icon.getImage().getScaledInstance(140, 274, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		String[] options = {"Maybe Later...", "Now!"};
		
		int decision = JOptionPane.showOptionDialog(
				cpInterest, 
				"<html><b><font size=5>Welcome to Jackie\'s Compound Interest Calculator!</font></b><br>"
				+ "<i>Would you like to calculate your compound interest now or later?<i></html>",
                "Compound Interest Calculator", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, 
                icon, 
                options, 
                null
                		);


		if (decision == 1) {
			cpInterest.getDepositedAmount();
			cpInterest.getInterestRate();
			cpInterest.getCompound();
			cpInterest.getYearsLeft();
			displayResults();
			System.exit(0);
        } else {
        	cpInterest.dispose();
        	ImageIcon goodbye = new ImageIcon("src/images/goodbye.png");
    		Image goodbyeIcon = goodbye.getImage().getScaledInstance(300, 194, java.awt.Image.SCALE_SMOOTH);
    		goodbye = new ImageIcon(goodbyeIcon);
            JOptionPane.showMessageDialog(cpInterest, "<html><font size=5>GOODBYE!</font><br> Have a nice day.</html>", null, JOptionPane.PLAIN_MESSAGE, goodbye);
            System.exit(0);
        }//End else
		
	}//End Main
	
	public static void displayResults() {
		double principalAmount = cpInterest.principalAmount;
		double interestRate = cpInterest.annualInterestRate;
		int compounded = cpInterest.timesCompounded;
		String compoundInterval = cpInterest.compoundInterval;
		int yearsLeft = cpInterest.yearsLeft;
		
		double results = principalAmount * Math.pow(1 + ((interestRate/100)/compounded), (compounded*yearsLeft));
		
		ImageIcon money = new ImageIcon("src/images/moneybag.png");
		Image moneyIcon = money.getImage().getScaledInstance(200, 208, java.awt.Image.SCALE_SMOOTH);
		money = new ImageIcon(moneyIcon);
		
        JOptionPane.showMessageDialog(
        		cpInterest, 
        		"<html><font size=5>Account Balance after " + yearsLeft + " years:</font> "
        				+ "<br><b>Principal Amount:</b> " + principalAmount
        				+ "<br><b>Interest Rate:</b> " + interestRate + "%"
        				+ "<br><b>Compound Interval:</b> " + compoundInterval
        				+ "<br><b>Years Planned to Save: </b>" + yearsLeft
        				+ "<br><br><font size=5>Results: $" + df2.format(results) + "</font>"
        		+ "</html>", 
        		null, 
        		JOptionPane.PLAIN_MESSAGE, 
        		money);

		
	}

}//End class
