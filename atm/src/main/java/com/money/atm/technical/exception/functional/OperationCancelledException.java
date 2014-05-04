/**
 * 
 */
package com.money.atm.technical.exception.functional;

import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.exception.FunctionalException;
import com.money.atm.technical.properties.MessageGenericEnum;

/**
 * Exception thrown when a user cancels the operation.
 * @author hloos
 */
public class OperationCancelledException extends FunctionalException {

	/** SerialUID */
	private static final long serialVersionUID = -1723655432688846140L;

	public OperationCancelledException() {
		super(ContextFactory.getMessage(MessageGenericEnum.CANCELLATION));
	}
	
	public OperationCancelledException(String message) {
		super(message);
	}

	public OperationCancelledException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationCancelledException(Throwable cause) {
		super(cause);
	}
}
