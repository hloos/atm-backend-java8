/**
 * 
 */
package com.money.atm.technical.properties;

/**
 * Parameters to configure the application.
 * @author hloos
 */
public enum ParameterEnum implements IProperties {
	
	TIMETOWAIT("front.beforeMenuDisplay.wait");
	
	private String key;
	
	/** Constructor. */
	private ParameterEnum(String key) {
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
