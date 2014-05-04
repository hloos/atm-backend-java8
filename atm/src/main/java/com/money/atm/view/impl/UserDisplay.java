package com.money.atm.view.impl;

import java.util.Objects;

import javax.inject.Named;

import com.money.atm.model.ChangeBean;
import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.constant.AnswerYesNoEnum;
import com.money.atm.technical.constant.UserChoiceEnum;
import com.money.atm.technical.exception.functional.OperationCancelledException;
import com.money.atm.technical.properties.MessageGenericEnum;
import com.money.atm.technical.properties.MessageUXEnum;
import com.money.atm.technical.properties.ParameterEnum;
import com.money.atm.view.IUserDisplay;

/**
 * Display of the ATM.
 * @author hloos
 */
@Named(IUserDisplay.NAME)
public class UserDisplay extends AbstractDisplay implements IUserDisplay {

	@Override
	public UserChoiceEnum displayMenu() {
		UserChoiceEnum userChoice = null;
		displayMessage(MessageUXEnum.USER_MENU);
		
		while(Objects.isNull(userChoice)) {
			int userOperation = getUserInput().getUserNumberInput();
			userChoice = UserChoiceEnum.getUserChoice(userOperation);
			
			if (Objects.isNull(userChoice)) {
				displayMessage(MessageUXEnum.INPUT_NOTVALID_ENTRY);
			}
		}
		return userChoice;
	}

	@Override
	public int getDesiredAmount() throws OperationCancelledException {
		boolean isConfirmed = false;
		int amount = 0;
		
		while(!isConfirmed) {
			
			displayMessage(MessageUXEnum.INPUT_ASK_AMOUNT);
			amount = getUserInput().getUserNumberInput();
			
			displayMessage(MessageUXEnum.INPUT_CONFIRMATION_AMOUNT, amount);
			AnswerYesNoEnum answer = getUserInput().getUserConfirmationInput();
			
			if(AnswerYesNoEnum.CANCEL.equals(answer)) {
				throw new OperationCancelledException(ContextFactory.getMessage(
						MessageGenericEnum.CANCELLATION));
			} else if(Objects.nonNull(answer)) {
				isConfirmed = answer.isOK();
			}
		}

		return amount;
	}
	
	@Override
	public void displayWithdrawal(ChangeBean money) {
		displayMessage(MessageUXEnum.WITHDRAW_PLEASE);
		money.getNotes().forEach(
				(k,v)->displayMessage(MessageUXEnum.WITHDRAW_RESULT, new Integer[]{v, k.getValue()}));
		displayMessage(MessageUXEnum.WITHDRAW_GOODBYE);
		waitBeforeDisplayMenu();
	}

	@Override
	public void displayATMTreasury(ChangeBean money) {
		displayMessage(MessageUXEnum.TREASURY_PRESENT);
		money.getNotes().forEach(
				(k,v)->displayMessage(MessageUXEnum.TREASURY_STATUS, new Integer[]{v, k.getValue()}));
		waitBeforeDisplayMenu();
	}

	@Override
	public void displayError(String message) {
		System.err.println(message);
	}
	
	@Override
	public void displayShutDown(){
		displayMessage(MessageUXEnum.SHUT_DOWN_STATUS);
	}
	
	/**
	 * Wait before displaying the menu.
	 */
	private void waitBeforeDisplayMenu() {
		try {
			Thread.sleep(Long.valueOf(ContextFactory.getMessage(ParameterEnum.TIMETOWAIT)));
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
}
