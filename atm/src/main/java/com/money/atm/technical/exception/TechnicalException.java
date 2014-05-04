/**
 * 
 */
package com.money.atm.technical.exception;

import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.properties.MessageGenericEnum;

/**
 * Technical exception.
 * @author hloos
 */
public class TechnicalException extends Exception {

	/**
	 * SerialUID.
	 */
	private static final long serialVersionUID = -6462579226181080028L;

	public TechnicalException() {
		super(ContextFactory.getMessage(MessageGenericEnum.GENERIC_ERROR));
	}
	
	public TechnicalException(String message) {
		super(message);
	}

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnicalException(Throwable cause) {
		super(cause);
	}
}
