/**
 * 
 */
package com.money.atm.technical.properties;

/**
 * Enum representing all keys of the MessageGeneric properties file.
 * @author hloos
 */
public enum MessageGenericEnum implements IProperties {
	GENERIC_ERROR("front.generic.error"), 
	CANCELLATION("front.generic.cancellation"),
	NOT_ENOUGH_CHANGE("exception.notenoughchange.error"),
	TYPENOTE_NOTEXISTING("exception.noteTypeNotExisting.error");

	private String key;
	
	/** Constructor. */
	private MessageGenericEnum(String key) {
		this.key = key;
	}
	
	/**
	 * @return value
	 */
	@Override
	public String getKey() {
		return key;
	}

}
