/**
 * 
 */
package com.money.atm.view;

import com.money.atm.model.ChangeBean;
import com.money.atm.technical.constant.UserChoiceEnum;
import com.money.atm.technical.exception.functional.OperationCancelledException;

/**
 * @author hloos
 *
 */
public interface IUserDisplay {
	
	String NAME = "UserDisplay";
	
	/**
	 * Display the customer menu.
	 */
	UserChoiceEnum displayMenu();
	
	/**
	 * Ask to the customer the amount he desired to cash out.
	 * @return the desired amount
	 */
	int getDesiredAmount() throws OperationCancelledException;
	
	/**
	 * Display the customer money.
	 * @param money
	 */
	void displayWithdrawal(ChangeBean money);
	
	/**
	 * Display the cash left in the ATM.
	 * @param money
	 */
	void displayATMTreasury(ChangeBean money);
	
	/**
	 * Display an error.
	 * @param message
	 */
	void displayError(String message);

	/**
	 * Display a message indicating that the ATM is shutting down.
	 * @param message
	 */
	void displayShutDown();
}
