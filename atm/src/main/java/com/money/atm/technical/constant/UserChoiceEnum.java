/**
 * 
 */
package com.money.atm.technical.constant;


/**
 * Enum representing the choice of user.
 * @author hloos
 *
 */
public enum UserChoiceEnum {

	WITHDRAW(1), 
	ADMIN_TREASURY(2),
	ADMIN_TURNOFF(3);
	
	private int userChoice;
	
	/** Constructor. */
	private UserChoiceEnum(int userChoice) {
		this.userChoice = userChoice;
	}
	
	/**
	 * Get the enum corresponding to the userChoice.
	 * @param userChoice
	 */
	public static UserChoiceEnum getUserChoice(int userChoice) {
		UserChoiceEnum result = null;
		
		for(UserChoiceEnum choiceEnum : UserChoiceEnum.values()) {
			if(choiceEnum.getUserChoice() == userChoice) {
				result = choiceEnum;
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * @return the userChoice value
	 */
	private int getUserChoice() {
		return userChoice;
	}
}
