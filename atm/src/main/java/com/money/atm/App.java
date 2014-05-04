/**
 * 
 */
package com.money.atm;

import com.money.atm.technical.ContextFactory;

/**
 * Entry point of the ATM application.
 * @author hloos
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ATMDevice atmDevice = ContextFactory.getBean(ATMDevice.class);
		atmDevice.start();
	}
}
