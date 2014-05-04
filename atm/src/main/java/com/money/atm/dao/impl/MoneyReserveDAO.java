/**
 * 
 */
package com.money.atm.dao.impl;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Named;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import com.money.atm.dao.IMoneyReserveDAO;
import com.money.atm.model.ChangeBean;
import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.constant.NoteTypeEnum;
import com.money.atm.technical.exception.technical.DataAccessException;
import com.money.atm.technical.properties.MessageGenericEnum;

/**
 * Dao to access to the reserve of money available in the ATM.<br/>
 * @author hloos
 */
@Named(IMoneyReserveDAO.NAME)
@Slf4j
public class MoneyReserveDAO implements IMoneyReserveDAO {

	//environment unithread because of executable application -> no need to be thread-safe
	private final Map<NoteTypeEnum, Integer> reserve = new EnumMap<>(NoteTypeEnum.class);
	
	/* (non-Javadoc)
	 * @see com.money.atm.dao.IMoneyReserve#createNotesInReserve(com.money.atm.technical.constant.NoteTypeEnum, int)
	 */
	@Override
	public void createNotesInReserve(@NonNull NoteTypeEnum noteType, int numberOfNotesToAdd) {
		reserve.put(noteType, numberOfNotesToAdd);
	}

	/* (non-Javadoc)
	 * @see com.money.atm.dao.IMoneyReserve#getNumberOfNotes(com.money.atm.technical.constant.NoteTypeEnum)
	 */
	@Override
	public int getNumberOfNotes(@NonNull NoteTypeEnum noteType) {
		Integer numberNotes = reserve.get(noteType);
		return Objects.isNull(numberNotes) ? 0 : numberNotes;
	}

	/* (non-Javadoc)
	 * @see com.money.atm.dao.IMoneyReserve#getAvailableChange()
	 */
	@Override
	public ChangeBean getAvailableChange() {
		ChangeBean availableChange = ContextFactory.getBean(ChangeBean.class);
		reserve.keySet().stream().filter(k -> reserve.get(k)>0)
			.forEach(k -> availableChange.putNotes(k, reserve.get(k)));
		return availableChange;
	}	
	
	/* (non-Javadoc)
	 * @see com.money.atm.dao.IMoneyReserve#updateNumberNotesInReserve(java.util.Map)
	 */
	@Override
	public void updateNumberNotesInReserve(@NonNull Map<NoteTypeEnum, Integer> noteTypeNbMap) throws DataAccessException {
		for(NoteTypeEnum noteType : noteTypeNbMap.keySet()) {
			if(!reserve.containsKey(noteType)) {
				String message = ContextFactory.getMessage(MessageGenericEnum.TYPENOTE_NOTEXISTING);
				log.error(message);
				throw new DataAccessException(message);
			}
		}
		
		noteTypeNbMap.forEach((noteType, number) -> reserve.replace(noteType, number));
	}
}
