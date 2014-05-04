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
 * Event representing a money enquiry.
 * @author hloos
 */
@Named
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@ToString
@EqualsAndHashCode
public class MoneyEnquiryBean {

	private int sumRequested;
	@Inject private ChangeBean userChange;
	
	/**
	 * @return the sumRequested
	 */
	public int getSumRequested() {
		return sumRequested;
	}
	/**
	 * @param sumRequested the sumRequested to set
	 */
	public void setSumRequested(int sumRequested) {
		this.sumRequested = sumRequested;
	}
	/**
	 * @return the notesToCashOut
	 */
	public ChangeBean getUserChange() {
		return userChange;
	}
	/**
	 * @param notesToCashOut the notesToCashOut to set
	 */
	public void setUserChange(ChangeBean userChange) {
		this.userChange = userChange;
	}
}
