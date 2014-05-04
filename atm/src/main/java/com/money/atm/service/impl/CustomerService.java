/**
 * 
 */
package com.money.atm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import com.money.atm.dao.IMoneyReserveDAO;
import com.money.atm.model.ChangeBean;
import com.money.atm.model.operation.MoneyEnquiryBean;
import com.money.atm.service.ICustomerService;
import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.constant.NoteTypeEnum;
import com.money.atm.technical.exception.TechnicalException;
import com.money.atm.technical.exception.functional.NotEnoughChangeException;
import com.money.atm.technical.exception.technical.DataAccessException;
import com.money.atm.technical.helper.ValidationHelper;

/**
 * Implementation of the service related to the treasury management.
 * @author hloos
 */
@Named
@Slf4j
public class CustomerService implements ICustomerService {

	@Inject private IMoneyReserveDAO moneyReserve;
	
	/* (non-Javadoc)
	 * @see com.money.atm.service.ICustomerService#withdrawMoney(com.money.atm.model.operation.MoneyEnquiryBean)
	 */
	@Override
	public MoneyEnquiryBean withdrawMoney(@NonNull MoneyEnquiryBean moneyEnquiry) 
			throws NotEnoughChangeException, TechnicalException {
		int desiredAmount = moneyEnquiry.getSumRequested();
		ChangeBean availableChange = moneyReserve.getAvailableChange();
		ValidationHelper.validateElementNonNull(availableChange, log, IMoneyReserveDAO.class);
		
		if(isEnoughAvailableChange(desiredAmount, availableChange)){
			ChangeBean userChange = calculateChange(desiredAmount, availableChange);
			moneyEnquiry.setUserChange(userChange);
		} else {
			NotEnoughChangeException notEnoughChangeException = new NotEnoughChangeException();
			log.info(notEnoughChangeException.getMessage());
			throw notEnoughChangeException;
		}
		
		updateTreasury(moneyEnquiry, availableChange);

		return moneyEnquiry;
	}

	/**
	 * Check if there is enough of change to complete the user request.
	 * @param desiredAmount
	 * @param availableChange
	 * @return true if there is enough, false otherwise
	 */
	private boolean isEnoughAvailableChange(int desiredAmount, ChangeBean availableChange) {
		int availableAmount = 0;
		
		if(Objects.nonNull(availableChange)) {
			for(NoteTypeEnum noteType : availableChange.getNotes().keySet()) {
				availableAmount += noteType.getValue() * availableChange.getNotes().get(noteType);
			}
		}
			
		return availableAmount >= desiredAmount;
	}
	/**
	 * Calculate the change to give to the user.
	 * @param desiredAmount
	 * @param availableChange
	 * @return the user change
	 * @throws NotEnoughChangeException 
	 */
	private ChangeBean calculateChange(int desiredAmount,
			@NonNull ChangeBean availableChange) throws NotEnoughChangeException {
		Map<NoteTypeEnum, Integer> changeMap = availableChange.getNotes();
		
		//we sort the types of note by their value in the decreasing order
		List<NoteTypeEnum> noteDecreasingValueList = changeMap.keySet().stream()
				.sorted((noteType1, noteType2) -> noteType2.getValue() - noteType1.getValue())
				.collect(Collectors.toList());
		
		ChangeBean changeResult = 
				calculateForTheNoteType(desiredAmount, noteDecreasingValueList, availableChange);
		
		if(Objects.isNull(changeResult)) {
			NotEnoughChangeException notEnoughChangeException = new NotEnoughChangeException();
			log.info(notEnoughChangeException.getMessage());
			throw notEnoughChangeException;
		} else {
			return changeResult;
		}
	}
	
	/**
	 * Recursive method to calculate the necessary change to complete the desiredAmount.
	 * @param pLeftAmount
	 * @param pLeftAvailableNoteType
	 * @param availableChange
	 * @return the change to give
	 */
	private ChangeBean calculateForTheNoteType(int pLeftAmount, 
			List<NoteTypeEnum> pLeftAvailableNoteType, ChangeBean availableChange) {
		ChangeBean changeToAdd = null;
		
		List<NoteTypeEnum> leftAvailableNoteType = new ArrayList<>(pLeftAvailableNoteType);
		NoteTypeEnum currentTypeNote = leftAvailableNoteType.get(0);
		int currentTypeNoteValue = currentTypeNote.getValue();
		int nbCurrentTypeNote = availableChange.getNotes().get(currentTypeNote);
		leftAvailableNoteType.remove(0);

		int leftAmount = pLeftAmount;
		int nbIdealMaxCurrentNoteType = (leftAmount - leftAmount % currentTypeNoteValue) / currentTypeNoteValue;
		int nbRealMaxCurrentNoteType = (nbCurrentTypeNote - nbIdealMaxCurrentNoteType >= 0) ? 
						nbIdealMaxCurrentNoteType : nbCurrentTypeNote;
		
		for (int nb = nbRealMaxCurrentNoteType; nb >= 0; nb--) {
			if(nb * currentTypeNoteValue == leftAmount) {
				changeToAdd = ContextFactory.getBean(ChangeBean.class);
				changeToAdd.putNotes(currentTypeNote, nb);
				break;
			} else if(!leftAvailableNoteType.isEmpty()){
				int newLeftAmount = leftAmount - nb * currentTypeNoteValue;
				changeToAdd = calculateForTheNoteType(newLeftAmount, leftAvailableNoteType, availableChange);
				
				if(Objects.nonNull(changeToAdd)) {
					int reachableSum = nb * currentTypeNoteValue + changeToAdd.geValueOfContainedChange();
					if(leftAmount == reachableSum) {
						if(nb > 0)
							changeToAdd.putNotes(currentTypeNote, nb);
						break;
					} 
				}
			}
		}
		
		return changeToAdd;
	}
	
	/**
	 * Update the treasury status with the current withdrawal.
	 * @param moneyEnquiry
	 * @param availableChange 
	 */
	private void updateTreasury(MoneyEnquiryBean moneyEnquiry, ChangeBean availableChange) throws TechnicalException {
		Map<NoteTypeEnum, Integer> newAvailableChange = availableChange.getNotes();
		Map<NoteTypeEnum, Integer> userChange = moneyEnquiry.getUserChange().getNotes();
		
		for(NoteTypeEnum noteType : userChange.keySet()){
			newAvailableChange.replace(
					noteType, newAvailableChange.get(noteType) - userChange.get(noteType));
		}
		
		try {
			moneyReserve.updateNumberNotesInReserve(newAvailableChange);
		} catch (DataAccessException e) {
			log.error(e.getMessage());
			throw new TechnicalException();
		}

	}
}
