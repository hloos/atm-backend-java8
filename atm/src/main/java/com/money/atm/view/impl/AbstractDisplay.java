package com.money.atm.view.impl;

import javax.inject.Inject;

import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.properties.IProperties;

/**
 * Generic methods for the display.
 * @author hloos
 */
public abstract class AbstractDisplay {

	@Inject	private UserInput userInput;
	
	/**
	 * @return get the input of the user
	 */
	protected UserInput getUserInput() {
		return userInput;
	}
	
	/**
	 * Display the message corresponding to the parameter key.
	 * @param key
	 */
	protected void displayMessage(IProperties key){
		System.out.println(ContextFactory.getMessage(key));
	}
	
	/**
	 * Display the message corresponding to the parameter key with the parameter arg.
	 * @param key
	 * @param arg
	 */
	protected void displayMessage(IProperties key, Object arg){
		System.out.println(ContextFactory.getMessage(key, arg));
	}
	
	/**
	 * Display the message corresponding to the parameter key with the parameter args.
	 * @param key
	 * @param args
	 */
	protected void displayMessage(IProperties key, Object[] args){
		System.out.println(ContextFactory.getMessage(key, args));
	}
}
