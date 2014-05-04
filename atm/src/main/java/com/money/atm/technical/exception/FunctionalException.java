/**
 * 
 */
package com.money.atm.technical.exception;

/**
 * FunctionalException 
 * @author hloos
 *
 */
public abstract class FunctionalException extends Exception {

	/**
	 * SSerialUID
	 */
	private static final long serialVersionUID = -1848571741623380839L;

	public FunctionalException() {
		super();
	}
	
	public FunctionalException(String message) {
		super(message);
	}

	public FunctionalException(String message, Throwable cause) {
		super(message, cause);
	}

	public FunctionalException(Throwable cause) {
		super(cause);
	}
}
