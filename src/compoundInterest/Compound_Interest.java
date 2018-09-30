package compoundInterest;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.util.Arrays;
import java.awt.Image;

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

public class Compound_Interest extends JFrame{

	//Declare variables
	public double principalAmount = 0.0;
	public double annualInterestRate = 0;
	public int timesCompounded = 0;
	public String compoundInterval = "";
	public int yearsLeft = 0;
	private JFrame frame = new JFrame();
	private ImageIcon uhOh = new ImageIcon();
	
	
	/*********************************************************/
	/*
	 * This method is called when I need to validate that the user's input is in the correct
	 * format.
	 */
	private boolean isNumber (String value) {
		boolean result = true;
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException e) {
			System.out.println(value + " is not a number");
			result = false;
		}
		return result;
	}//End isDouble Method
	
	/*********************************************************/
	
	private ImageIcon getUhOhIcon() {
		//Icon used when something goes wrong
		ImageIcon uhOh = new ImageIcon("src/images/coyote.png");
		Image uhOhImage = uhOh.getImage().getScaledInstance(150, 220, java.awt.Image.SCALE_SMOOTH);
		uhOh = new ImageIcon(uhOhImage);
		
		return uhOh;
	}//End of UhOhIcon method
	
	/*********************************************************/
	private boolean checkIfCancelled(String input) {
		boolean result = false;
		String [] options = {"no", "yes"};
		
		ImageIcon question = new ImageIcon("src/images/questionmark.png");
		Image questionImage = question.getImage().getScaledInstance(140, 207, java.awt.Image.SCALE_SMOOTH);
		question = new ImageIcon(questionImage);
		
		int option = JOptionPane.showOptionDialog(
				 frame, 
				 "Are you sure you'd like to cancel?", 
				 "Cancel?", 
				 JOptionPane.YES_NO_OPTION, 
				 JOptionPane.INFORMATION_MESSAGE, 
				 question, 
				 options, 
				 null
				 );
		
		switch(option) {
		case 0: 
			result = false;
			break;
		case 1:
			ImageIcon goodbye = new ImageIcon("src/images/goodbye.png");
    		Image goodbyeIcon = goodbye.getImage().getScaledInstance(300, 194, java.awt.Image.SCALE_SMOOTH);
    		goodbye = new ImageIcon(goodbyeIcon);
    		
			JOptionPane.showMessageDialog(frame, "Sad to see you go...", null, JOptionPane.PLAIN_MESSAGE, goodbye);
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.dispose();
		    System.exit(0);
			break;
		}//end switch
		
		return result;
	}//End checkIfNull method
	
	/*********************************************************/

	public void getDepositedAmount() {
		String depositedAmount = "";
		boolean isDouble = true;
		boolean cancel = false;
		//Icon used for initial investment
		ImageIcon icon = new ImageIcon("src/images/money-bank.png");
		Image image = icon.getImage().getScaledInstance(100, 98, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		
		uhOh = getUhOhIcon(); //Image used when there's an oppsie
		
		depositedAmount = (String) JOptionPane.showInputDialog(
						frame,
						"What will your initial investment amount be?", 
						"Initial Investment!", 
						JOptionPane.INFORMATION_MESSAGE, 
						icon,
						null,"");
		
		
		while(depositedAmount == null) {
			cancel = checkIfCancelled(depositedAmount);
			depositedAmount = (String) JOptionPane.showInputDialog(
					frame,
					"What will your initial investment amount be?", 
					"Initial Investment!", 
					JOptionPane.INFORMATION_MESSAGE, 
					icon,
					null,"");
			if(depositedAmount == null) {
				cancel = checkIfCancelled(depositedAmount);
			}//end if
		}//End if
		
		//not null or empty validation check 
		while (depositedAmount.isEmpty()) {
			depositedAmount = (String) JOptionPane.showInputDialog(
					frame,
					"Seems you forgot to enter an initial deposit amount. :( \n"
					+ "What will your initial investment amount be?", 
					"Initial Investment!", 
					JOptionPane.INFORMATION_MESSAGE, 
					uhOh,
					null,"");
			
			if(depositedAmount == null) {
				cancel = checkIfCancelled(depositedAmount);
			}//end if
		}//End while
		
		if (!cancel) {
			//not null or empty validation check 
			while (depositedAmount.isEmpty()) {
				depositedAmount = (String) JOptionPane.showInputDialog(
						frame,
						"Seems you forgot to enter an initial deposit amount. :( \n"
						+ "What will your initial investment amount be?", 
						"Initial Investment!", 
						JOptionPane.INFORMATION_MESSAGE, 
						uhOh,
						null,"");
				
			}//End while
			
			depositedAmount = depositedAmount.trim();
			
			//number format validation
			isDouble = isNumber(depositedAmount);
			
			//While loop to 
			while (isDouble == false) {
				depositedAmount = (String) JOptionPane.showInputDialog(
						frame,
						"<html><b>You might\'ve accidently entered non-numerical characters.</b> <br>"
						+ "Correct entry examples:<i> \"200.98\", \"3986\" </i><br>"
						+ "Incorrect entry examples:<i> \"$340\", \"234,000\", \"6dwj6\" </i><br><br>"
						+ "What will your initial investment amount be? </html>", 
						"Initial Investment!", 
						JOptionPane.INFORMATION_MESSAGE, 
						uhOh,
						null,"");
				depositedAmount = depositedAmount.trim();
				isDouble = isNumber(depositedAmount);
			}//End while
		}
		
		principalAmount = Double.parseDouble(depositedAmount);
		//return principalAmount;
	}//End getDepostedAmount Method
		
	/*********************************************************/ 
	
	public void getInterestRate () {
		String interestRate = "";
		boolean cancel = false;
		boolean isDouble = true;

		//Icon used for initial investment
		ImageIcon percentageIcon = new ImageIcon("src/images/percentage.png");
		Image percentageImage = percentageIcon.getImage().getScaledInstance(120, 135, java.awt.Image.SCALE_SMOOTH);
		percentageIcon = new ImageIcon(percentageImage);
		
		uhOh = getUhOhIcon();
		
		interestRate = (String) JOptionPane.showInputDialog(
				frame,
				"What percentage is your annual interest rate?", 
				"Interest Rate!", 
				JOptionPane.INFORMATION_MESSAGE, 
				percentageIcon,
				null,"");
		
		while(interestRate == null) {
			cancel = checkIfCancelled(interestRate);
			interestRate = (String) JOptionPane.showInputDialog(
					frame,
					"What percentage is your annual interest rate?", 
					"Interest Rate!", 
					JOptionPane.INFORMATION_MESSAGE, 
					percentageIcon,
					null,"");
		}//End if
		
		while (interestRate.isEmpty()) {
			interestRate = (String) JOptionPane.showInputDialog(
					frame,
					"Seems you forgot to enter an interest rate. \n"
					+ "What is your annual interest rate?", 
					"Interest Rate!", 
					JOptionPane.INFORMATION_MESSAGE, 
					uhOh,
					null,"");
			interestRate =interestRate.trim();
			while(interestRate == null) {
				cancel = checkIfCancelled(interestRate);
			}//End if
			
		}//End while
		
		if (!cancel) {
			
			while (interestRate.isEmpty()) {
				interestRate = (String) JOptionPane.showInputDialog(
						frame,
						"Seems you forgot to enter an interest rate. \n"
						+ "What is your annual interest rate?", 
						"Interest Rate!", 
						JOptionPane.INFORMATION_MESSAGE, 
						uhOh,
						null,"");
				interestRate =interestRate.trim();

				if(interestRate == null) {
					cancel = checkIfCancelled(interestRate);
				}//End if
				
			}//End while
			
			isDouble = isNumber(interestRate);
			
			while (isDouble == false) {
				interestRate  = (String) JOptionPane.showInputDialog(
						frame,
						"Please only enter numerical characters. \n"
						+ "What is your annual interest rate?", 
						"Interest Rate!", 
						JOptionPane.INFORMATION_MESSAGE, 
						uhOh,
						null,"");
				interestRate =interestRate.trim();
				isDouble = isNumber(interestRate);
			}//End while
			
		}//End if
		
		annualInterestRate = Double.parseDouble(interestRate);
		//return annualInterestRate;
		
	}//End getInterestRate method
	
	/*********************************************************/
	
	public int getCompound () {
		String compoundTimes = "";
		boolean cancel = false;
		int compounded = 0;
		String [] options = {"Annually", "SemiAnnually", "Quarterly", "Monthly", "Daily"};

		//Icon used for initial investment
		ImageIcon compoundIcon = new ImageIcon("src/images/compound-interest.png");
		Image compoundImage = compoundIcon.getImage().getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH);
		compoundIcon = new ImageIcon(compoundImage);
		
		uhOh = getUhOhIcon();
		
		compoundTimes = (String) JOptionPane.showInputDialog(
				frame,
				"How many times per year will interest will be compounded?", 
				"Compound It!", 
				JOptionPane.QUESTION_MESSAGE, 
				compoundIcon,
				options,options[0]);
		
		while(compoundTimes == null) {
			cancel = checkIfCancelled(compoundTimes);
			compoundTimes = (String) JOptionPane.showInputDialog(
					frame,
					"How many times per year will interest will be compounded?", 
					"Compound It!", 
					JOptionPane.QUESTION_MESSAGE, 
					compoundIcon,
					options,options[0]);
		}//End if
		
		if (!cancel) {
			switch (compoundTimes.trim()) {
			case "Annually":
				compounded = 1;
				break;
			case "SemiAnnually":
				compounded = 2;
				break;
			case "Quarterly":
				compounded = 4;
				break;
			case "Monthly":
				compounded = 12;
				break;
			case "Daily":
				compounded = 365;
				break;
			}//End switch
		}//End if
		
		compoundInterval = compoundTimes;
		timesCompounded = compounded;

		return timesCompounded;
	}//End getCompund Method
	
	/*********************************************************/

	public void getYearsLeft() {
		String yearsToGo = "";
		int [] yearsRange = java.util.stream.IntStream.rangeClosed(0, 100).toArray();
		boolean isInt = false;
		boolean cancel = true;
		String yearsString = Arrays.toString(yearsRange);
		String [] yearsArray = yearsString.substring(1, yearsString.length()-1).split(",");

		

		//Icon used for calender
		ImageIcon compoundIcon = new ImageIcon("src/images/calender.png");
		Image compoundImage = compoundIcon.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		compoundIcon = new ImageIcon(compoundImage);
		
		uhOh = getUhOhIcon();
		
		yearsToGo = (String) JOptionPane.showInputDialog(
				frame,
				"How many years will the account be left to earn interest?", 
				"Years!", 
				JOptionPane.QUESTION_MESSAGE, 
				compoundIcon,
				yearsArray,yearsArray[0]);
		
		isInt = isNumber(yearsToGo);

		while (yearsToGo == null) {
			cancel = checkIfCancelled(yearsToGo);
			yearsToGo = (String) JOptionPane.showInputDialog(
					frame, "How many times per year will interest will be compounded?", 
					"Compound It!", 
					JOptionPane.INFORMATION_MESSAGE, 
					compoundIcon,
					yearsArray,yearsArray[0]);

		}//End while
		
		if (!cancel) {
			
			yearsToGo = (String) JOptionPane.showInputDialog(
					frame, "How many times per year will interest will be compounded?", 
					"Compound It!", 
					JOptionPane.INFORMATION_MESSAGE, 
					compoundIcon,
					yearsArray,yearsArray[0]);
		}
		
		yearsLeft = Integer.parseInt(yearsToGo.trim());
		//return yearsLeft;
		
	}//End getYearsLeft method
		

}//End Class










