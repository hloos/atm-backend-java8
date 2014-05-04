/**
 * 
 */
package com.money.atm.technical.constant;

import org.apache.commons.lang.StringUtils;

/**
 * Enum representing a Yes/No answer.
 * @author hloos
 */
public enum AnswerYesNoEnum {
	YES("yes", true), NO("no", false), CANCEL("cancel", false);
	
	private String value;
	private boolean bool;
	
	/** Constructor. */
	private AnswerYesNoEnum(String value, boolean bool){
		this.value = value;
		this.bool = bool;
	}
	
	/** 
	 * @return the corresponding AnswerYesNoEnum 
	 */
	public static AnswerYesNoEnum getYesNoEnum(String answer) {
		AnswerYesNoEnum result = null;
		
		if (StringUtils.isNotBlank(answer)) {
			for(AnswerYesNoEnum answerEnum : AnswerYesNoEnum.values()) {
				if(answerEnum.getValue().equals(answer)) {
					result = answerEnum;
					break;
				}
			}
		}
		return result;
	}
	
	/** @return the boolean equivalent to the answer. */	
	public boolean isOK(){
		return bool;
	}
	
	/** @return the value */
	private String getValue() {
		return value;
	}
}
