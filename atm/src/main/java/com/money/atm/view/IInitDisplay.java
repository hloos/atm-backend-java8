/**
 * 
 */
package com.money.atm.view;

import com.money.atm.model.ChangeBean;

/**
 * @author hloos
 *
 */
public interface IInitDisplay {

	String NAME = "InitDisplay";

	/**
	 * Initialize the treasury of the ATM.
	 */
	ChangeBean initializationATM();
	
	/**
	 * Confirm the correct initialization
	 * @param treasuryATM 
	 */
	void confirmInitializationATM(ChangeBean treasuryATM);
	
	/**
	 * Display the end of the init.
	 */
	void displayInitOver();
}
