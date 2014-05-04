package com.money.atm;

import javax.inject.Inject;
import javax.inject.Named;

import com.money.atm.model.ChangeBean;
import com.money.atm.model.operation.MoneyEnquiryBean;
import com.money.atm.model.operation.TreasuryATMEnquiryBean;
import com.money.atm.service.ICustomerService;
import com.money.atm.service.ITreasuryService;
import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.constant.UserChoiceEnum;
import com.money.atm.technical.exception.FunctionalException;
import com.money.atm.technical.exception.TechnicalException;
import com.money.atm.view.IInitDisplay;
import com.money.atm.view.IUserDisplay;

/**
 * Class representing the ATM device.
 * @author hloos
 *
 */
@Named
public class ATMDevice {

	@Inject	private ICustomerService customerService;
	@Inject	private ITreasuryService treasuryService;
	@Inject	private IInitDisplay initDisplay;
	@Inject	private IUserDisplay userDisplay;

	/**
	 * Start the ATM device.
	 */
	public void start() {
		boolean exit = false;
		ChangeBean treasuryATM = initDisplay.initializationATM();
		treasuryService.initializeTreasury(treasuryATM);
		initDisplay.confirmInitializationATM(treasuryATM);
		initDisplay.displayInitOver();
		
		while(!exit) {
			UserChoiceEnum userChoice = userDisplay.displayMenu();
			
			switch (userChoice) {
			case WITHDRAW:
				performWithdrawal();
				break;
			case ADMIN_TREASURY:
				displayATMTreasury();
				break;
			case ADMIN_TURNOFF:
				userDisplay.displayShutDown();
				exit = true;
			default:
				break;
			}
		}
	}

	/**
	 * Perform a customer withdraw.
	 */
	private void performWithdrawal() {
		try {
			int desiredAmount = userDisplay.getDesiredAmount();
			
			MoneyEnquiryBean moneyEnquiry = ContextFactory.getBean(MoneyEnquiryBean.class);
			moneyEnquiry.setSumRequested(desiredAmount);
			
			ChangeBean changeBean = customerService.withdrawMoney(moneyEnquiry).getUserChange();
			userDisplay.displayWithdrawal(changeBean);
		} catch (FunctionalException | TechnicalException e) {
			userDisplay.displayError(e.getMessage());
		}
	}
	
	/**
	 * Get and display the ATM treasury.
	 */
	private void displayATMTreasury() {
		TreasuryATMEnquiryBean treasuryStatus = treasuryService.getTreasuryStatus();
		userDisplay.displayATMTreasury(treasuryStatus.getTreasury());
	}
}
