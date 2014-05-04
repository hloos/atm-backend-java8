/**
 * 
 */
package com.money.atm.technical.constant;

/**
 * Enum representing the different type of notes available in the ATM.
 * @author hloos
 */
public enum NoteTypeEnum {
	FIFTY_NOTE(50), TWENTY_NOTE(20);
	
	private int value;
	
	/** Constructor. */
	private NoteTypeEnum(int value) {
		this.value = value;
	}
	
	/**
	 * @return the value of the note
	 */
	public int getValue() {
		return value;
	}
}
