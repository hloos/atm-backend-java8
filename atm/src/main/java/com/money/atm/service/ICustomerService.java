/**
 * 
 */
package com.money.atm.service;

import com.money.atm.model.operation.MoneyEnquiryBean;
import com.money.atm.technical.exception.TechnicalException;
import com.money.atm.technical.exception.functional.NotEnoughChangeException;

/**
 * Service related to the treasury management.
 * @author hloos
 */
public interface ICustomerService {

	String NAME = "CustomerService";
	
	/**
	 * Withdraw the desired sum.
	 * @param moneyEnquiry
	 * @return the customerChange
	 * @throws NotEnoughChangeException
	 */
	MoneyEnquiryBean withdrawMoney(MoneyEnquiryBean moneyEnquiry) 
			throws NotEnoughChangeException, TechnicalException;
}
