package com.money.atm.service;

import com.money.atm.model.ChangeBean;
import com.money.atm.model.operation.TreasuryATMEnquiryBean;

/**
 * Service related to the treasury management.
 * @author hloos
 */
public interface ITreasuryService {

	String NAME = "TreasuryService";
	
	/**
	 * Add money to the ATM.
	 * @param noteType
	 * @param numberOfNotesToAdd
	 */
	void initializeTreasury(ChangeBean treasuryBean);

	
	/**
	 * Display how many notes are available in the ATM.
	 * @return the ATM treasury
	 */
	TreasuryATMEnquiryBean getTreasuryStatus();
}
