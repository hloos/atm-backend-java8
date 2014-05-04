/**
 * 
 */
package com.money.atm.technical.helper;

import java.util.Objects;

import org.slf4j.Logger;

import com.money.atm.technical.exception.TechnicalException;

/**
 * @author hloos
 *
 */
public class ValidationHelper {

	/** Constructor. */
	private ValidationHelper() {
		//Class util
	}
	
	/**
	 * Validate the input parameters and unexpected output results.
	 * @param object
	 * @param log
	 * @throws TechnicalException
	 */
	public static void validateElementNonNull(Object object, Logger log, @SuppressWarnings("rawtypes") Class clazz) throws TechnicalException {
		if(Objects.isNull(object)){
			log.error("Unexpected null element received from the class : "+clazz.getSimpleName());
			throw new TechnicalException();
		}
	}
}
