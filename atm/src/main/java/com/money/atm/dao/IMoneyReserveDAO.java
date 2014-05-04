/**
 * 
 */
package com.money.atm.dao;

import java.util.Map;

import com.money.atm.model.ChangeBean;
import com.money.atm.technical.constant.NoteTypeEnum;
import com.money.atm.technical.exception.technical.DataAccessException;

/**
 * Inferface of the reserve of money available in the ATM.<br/>
 * @author hloos
 */
public interface IMoneyReserveDAO {

	public static final String NAME = "MoneyReserve";
	
	/**
	 * Add notes to the reserve.
	 * @param noteType
	 * @param numberOfNotesToAdd
	 */
	void createNotesInReserve(NoteTypeEnum noteType, int numberOfNotesToAdd);
	
	/**
	 * Get the number of note corresponding to the type of note<br/>
	 * in parameter present in the reserve.
	 * @param noteType
	 * @return the number of notes present in the reserve
	 */
	int getNumberOfNotes(NoteTypeEnum noteType);
	
	/**
	 * Get the available change in the reserve.
	 * @return the available change
	 */
	ChangeBean getAvailableChange();
	
	/**
	 * Update the number of a list of types of note in the reserve.
	 * @param noteType
	 * @param numberOfNotes
	 * @throws DataAccessException
	 */
	void updateNumberNotesInReserve(Map<NoteTypeEnum, Integer> noteTypeNbMap) throws DataAccessException;
}
