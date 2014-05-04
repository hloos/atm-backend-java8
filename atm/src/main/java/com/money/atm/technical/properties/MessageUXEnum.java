/**
 * 
 */
package com.money.atm.technical.properties;

/**
 * Enum representing all keys of the MessageUX properties file.
 * @author hloos
 */
public enum MessageUXEnum implements IProperties {

	INIT_START("front.initialization.start"),
	INIT_STOP("front.initialization.stop"),

	INPUT_NOTNUMBER_ERROR("front.initialization.notNumberError"), 
	INPUT_NEGATIVENUMBER_ERROR("front.initialization.negativeNumberError"),
	INPUT_ASK_TREASURY("front.initialization.askForNotesNumber"),
	INPUT_CONFIRMATION_TREASURY("front.initialization.askForNotesNumber"),
	INPUT_NOTVALID_ENTRY("front.input.answerNotValid"),
	INPUT_ASK_AMOUNT("front.withdraw.askAmount"),
	INPUT_CONFIRMATION_AMOUNT("front.withdraw.confirmAmount"),
	
	CONFIRMATION_INIT("front.init.confirmation"),
	
	USER_MENU("front.user.menu"),
	
	WITHDRAW_PLEASE("front.withdraw.resultPlease"),
	WITHDRAW_RESULT("front.withdraw.result"),
	WITHDRAW_GOODBYE("front.withdraw.goodbye"),
	
	TREASURY_PRESENT("front.treasury.present"),
	TREASURY_STATUS("front.treasury.status"),
	
	SHUT_DOWN_STATUS("front.shutdown.status");	
		
	private String key;
	
	/** Constructor. */
	private MessageUXEnum(String key) {
		this.key = key;
	}
	
	/**
	 * @return the value of the note
	 */
	@Override
	public String getKey() {
		return key;
	}
}
