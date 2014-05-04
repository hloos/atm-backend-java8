package com.money.atm.technical.exception.functional;

import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.exception.FunctionalException;
import com.money.atm.technical.properties.MessageGenericEnum;

/**
 * Exception thrown when change is missing to complete the operation.
 * @author hloos
 */
public class NotEnoughChangeException extends FunctionalException {

	/**
	 * SerailUID
	 */
	private static final long serialVersionUID = -1957608524957062443L;

	public NotEnoughChangeException() {
		super(ContextFactory.getMessage(MessageGenericEnum.NOT_ENOUGH_CHANGE));
	}
	
	public NotEnoughChangeException(String message) {
		super(message);
	}

	public NotEnoughChangeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughChangeException(Throwable cause) {
		super(cause);
	}
}
