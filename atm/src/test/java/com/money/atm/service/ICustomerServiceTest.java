package com.money.atm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.money.atm.AbstractTest;
import com.money.atm.dao.IMoneyReserveDAO;
import com.money.atm.model.ChangeBean;
import com.money.atm.model.operation.MoneyEnquiryBean;
import com.money.atm.service.impl.CustomerService;
import com.money.atm.technical.constant.NoteTypeEnum;
import com.money.atm.technical.exception.TechnicalException;
import com.money.atm.technical.exception.functional.NotEnoughChangeException;

public class ICustomerServiceTest extends AbstractTest {

	@Mock private IMoneyReserveDAO moneyReserve;
	
	@InjectMocks private CustomerService customerService;
	
	private MoneyEnquiryBean moneyEnquiry = this.context.getBean(MoneyEnquiryBean.class);
	private ChangeBean changeBean = this.context.getBean(ChangeBean.class);
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testWithdrawMoney() throws NotEnoughChangeException, TechnicalException {
		moneyEnquiry.setSumRequested(120);
		changeBean.putNotes(NoteTypeEnum.FIFTY_NOTE, 4);
		changeBean.putNotes(NoteTypeEnum.TWENTY_NOTE, 6);
		Mockito.stub(this.moneyReserve.getAvailableChange()).toReturn(
				changeBean);
		
		MoneyEnquiryBean moneyResult = this.customerService.withdrawMoney(moneyEnquiry);
		
		assertNotNull(moneyResult);
		assertEquals(2, moneyResult.getUserChange().getNotes().keySet().size());
		assertEquals(new Integer(2), moneyResult.getUserChange().getNotes().get(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(new Integer(1), moneyResult.getUserChange().getNotes().get(NoteTypeEnum.TWENTY_NOTE));
	}
	
	//The Lombok annotation @NonNull send a NPE with the name of the null parameter as message.
	@Test(expected = NullPointerException.class)
	public void testWithdrawMoney_NullParameter() throws NotEnoughChangeException, TechnicalException {
		this.customerService.withdrawMoney(null);
	}

	@Test
	public void testWithdrawMoney_OnlyTwentyNotesSuit() throws NotEnoughChangeException, TechnicalException {
		moneyEnquiry.setSumRequested(120);
		changeBean.putNotes(NoteTypeEnum.FIFTY_NOTE, 1);
		changeBean.putNotes(NoteTypeEnum.TWENTY_NOTE, 7);
		Mockito.stub(this.moneyReserve.getAvailableChange()).toReturn(
				changeBean);
		
		MoneyEnquiryBean moneyResult = this.customerService.withdrawMoney(moneyEnquiry);
		
		assertNotNull(moneyResult);
		assertEquals(1, moneyResult.getUserChange().getNotes().keySet().size());
		assertEquals(new Integer(6), moneyResult.getUserChange().getNotes().get(NoteTypeEnum.TWENTY_NOTE));
	}
	
	@Test
	public void testWithdrawMoney_EnoughOfFiftyNotes() throws NotEnoughChangeException, TechnicalException {
		moneyEnquiry.setSumRequested(150);
		changeBean.putNotes(NoteTypeEnum.FIFTY_NOTE, 5);
		changeBean.putNotes(NoteTypeEnum.TWENTY_NOTE, 7);
		Mockito.stub(this.moneyReserve.getAvailableChange()).toReturn(
				changeBean);
		
		MoneyEnquiryBean moneyResult = this.customerService.withdrawMoney(moneyEnquiry);
		
		assertNotNull(moneyResult);
		assertEquals(1, moneyResult.getUserChange().getNotes().keySet().size());
		assertEquals(new Integer(3), moneyResult.getUserChange().getNotes().get(NoteTypeEnum.FIFTY_NOTE));
	}
	
	@Test
	public void testWithdrawMoney_NotUseAMaxOfFiftyNotes() throws NotEnoughChangeException, TechnicalException {
		moneyEnquiry.setSumRequested(540);
		changeBean.putNotes(NoteTypeEnum.FIFTY_NOTE, 9);
		changeBean.putNotes(NoteTypeEnum.TWENTY_NOTE, 8);
		Mockito.stub(this.moneyReserve.getAvailableChange()).toReturn(
				changeBean);
		
		MoneyEnquiryBean moneyResult = this.customerService.withdrawMoney(moneyEnquiry);
		
		assertNotNull(moneyResult);
		assertEquals(2, moneyResult.getUserChange().getNotes().keySet().size());
		assertEquals(new Integer(8), moneyResult.getUserChange().getNotes().get(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(new Integer(7), moneyResult.getUserChange().getNotes().get(NoteTypeEnum.TWENTY_NOTE));
	}
	
	@Test(expected = NotEnoughChangeException.class)
	public void testWithdrawMoney_NotEnoughChangeException() throws NotEnoughChangeException, TechnicalException {
		moneyEnquiry.setSumRequested(130);
		changeBean.putNotes(NoteTypeEnum.FIFTY_NOTE, 2);
		changeBean.putNotes(NoteTypeEnum.TWENTY_NOTE, 1);
		Mockito.stub(this.moneyReserve.getAvailableChange()).toReturn(
				changeBean);
		
		this.customerService.withdrawMoney(moneyEnquiry);
	}
	
	@Test(expected = NotEnoughChangeException.class)
	public void testWithdrawMoney_NotEnoughChangeToSatisfyCombinationNotes() throws NotEnoughChangeException, TechnicalException {
		moneyEnquiry.setSumRequested(180);
		changeBean.putNotes(NoteTypeEnum.FIFTY_NOTE, 4);
		changeBean.putNotes(NoteTypeEnum.TWENTY_NOTE, 3);
		Mockito.stub(this.moneyReserve.getAvailableChange()).toReturn(
				changeBean);
		
		this.customerService.withdrawMoney(moneyEnquiry);
	}
}
