/**
 * 
 */
package com.money.atm.model.operation;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import com.money.atm.model.ChangeBean;

/**
 * Event representing a enquiry of the notes left in the ATM.
 * @author hloos
 */
@Named
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@ToString
@EqualsAndHashCode
public class TreasuryATMEnquiryBean {
	
	@Inject private ChangeBean treasury;

	/**
	 * @return the treasury
	 */
	public ChangeBean getTreasury() {
		return treasury;
	}

	/**
	 * @param treasury the treasury to set
	 */
	public void setTreasury(ChangeBean treasury) {
		this.treasury = treasury;
	}
}
