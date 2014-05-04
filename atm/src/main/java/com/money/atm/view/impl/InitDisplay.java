/**
 * 
 */
package com.money.atm.view.impl;

import javax.inject.Named;

import com.money.atm.model.ChangeBean;
import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.constant.NoteTypeEnum;
import com.money.atm.technical.properties.MessageUXEnum;
import com.money.atm.view.IInitDisplay;

/**
 * Manage the display of the ATM.
 * @author hloos
 */
@Named(IInitDisplay.NAME)
public class InitDisplay extends AbstractDisplay implements IInitDisplay {

	@Override
	public ChangeBean initializationATM() {
		displayMessage(MessageUXEnum.INIT_START);
		ChangeBean treasory = ContextFactory.getBean(ChangeBean.class);
		
		for (NoteTypeEnum noteType : NoteTypeEnum.values()) {
			displayMessage(MessageUXEnum.INPUT_ASK_TREASURY, noteType.getValue());
			
			int nbNotes = getUserInput().getUserNumberInput();

			treasory.putNotes(noteType, nbNotes);
		}
		
		return treasory;
	}

	@Override
	public void confirmInitializationATM(ChangeBean treasuryBean) {
		treasuryBean.getNotes().forEach(
				(k,v) -> displayMessage(MessageUXEnum.CONFIRMATION_INIT, new Object[]{v, k.getValue()}));
	}

	@Override
	public void displayInitOver() {
		displayMessage(MessageUXEnum.INIT_STOP);
	}
}
