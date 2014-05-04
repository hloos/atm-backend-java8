/**
 * 
 */
package com.money.atm.technical.exception.technical;

import com.money.atm.technical.exception.TechnicalException;

/**
 * Exception thrown in the persistence layer.
 * @author hloos
 */
public class DataAccessException extends TechnicalException {

	/**
	 * SerialUID.
	 */
	private static final long serialVersionUID = -5894182923819943679L;

	public DataAccessException() {
		super();
	}
	
	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
