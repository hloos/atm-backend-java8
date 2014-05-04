/**
 * 
 */
package com.money.atm.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.NonNull;

import com.money.atm.dao.IMoneyReserveDAO;
import com.money.atm.model.ChangeBean;
import com.money.atm.model.operation.TreasuryATMEnquiryBean;
import com.money.atm.service.ITreasuryService;
import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.constant.NoteTypeEnum;

/**
 * Implementation of the service related to the treasury management.
 * @author hloos
 */
@Named
public class TreasuryService implements ITreasuryService {

	@Inject	private IMoneyReserveDAO moneyReserve;
	
	/* (non-Javadoc)
	 * @see com.money.atm.service.ITreasuryService#initializeTreasury(com.money.atm.model.ChangeBean)
	 */
	@Override
	public void initializeTreasury(@NonNull ChangeBean treasuryBean) {
		treasuryBean.getNotes().forEach((k, v) -> moneyReserve.createNotesInReserve(k, v));
	}

	/* (non-Javadoc)
	 * @see com.money.atm.service.ITreasuryService#getTreasuryStatus()
	 */
	@Override
	public TreasuryATMEnquiryBean getTreasuryStatus() {
		TreasuryATMEnquiryBean treasury = ContextFactory.getBean(TreasuryATMEnquiryBean.class);
		
		for(NoteTypeEnum noteType : NoteTypeEnum.values()) {
			treasury.getTreasury().putNotes(noteType, moneyReserve.getNumberOfNotes(noteType));
		}
		
		return treasury;
	}
}
