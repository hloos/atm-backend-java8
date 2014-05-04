package com.money.atm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.money.atm.AbstractTest;
import com.money.atm.dao.IMoneyReserveDAO;
import com.money.atm.dao.impl.MoneyReserveDAO;
import com.money.atm.model.ChangeBean;
import com.money.atm.model.operation.TreasuryATMEnquiryBean;
import com.money.atm.service.impl.TreasuryService;
import com.money.atm.technical.constant.NoteTypeEnum;

public class ITreasuryServiceTest extends AbstractTest{
	
	private IMoneyReserveDAO moneyReserve = new MoneyReserveDAO();
	private ITreasuryService treasuryService = new TreasuryService();
	
	@Mock private IMoneyReserveDAO moneyReserveMocked = new MoneyReserveDAO();
	@InjectMocks private TreasuryService treasuryServiceMock;
	
	ChangeBean changeBean = this.context.getBean(ChangeBean.class);
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		ReflectionTestUtils.setField(treasuryService, "moneyReserve", moneyReserve);
	}

	@Test
	public void testInitializeTreasury() {
		changeBean.getNotes().put(NoteTypeEnum.FIFTY_NOTE, 55);
		changeBean.getNotes().put(NoteTypeEnum.TWENTY_NOTE, 22);	
		
		treasuryService.initializeTreasury(changeBean);
		
		assertEquals(55, moneyReserve.getNumberOfNotes(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(22, moneyReserve.getNumberOfNotes(NoteTypeEnum.TWENTY_NOTE));
	}
	//The Lombok annotation @NonNull send a NPE with the name of the null parameter as message.
	@Test(expected = NullPointerException.class)
	public void testInitializeTreasury_NullParameter() {
		treasuryService.initializeTreasury(null);
	}
	
	@Test
	public void testInitializeTreasuryToEmpty() {
		changeBean.getNotes().put(NoteTypeEnum.FIFTY_NOTE, 0);
		changeBean.getNotes().put(NoteTypeEnum.TWENTY_NOTE, 0);	
		
		treasuryService.initializeTreasury(changeBean);
		
		assertEquals(0, moneyReserve.getNumberOfNotes(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(0, moneyReserve.getNumberOfNotes(NoteTypeEnum.TWENTY_NOTE));
	}
	
	@Test
	public void testInitializeTreasury_OnlyOneNoteType() {
		changeBean.getNotes().put(NoteTypeEnum.TWENTY_NOTE, 25);	
		
		treasuryService.initializeTreasury(changeBean);
		
		assertEquals(0, moneyReserve.getNumberOfNotes(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(25, moneyReserve.getNumberOfNotes(NoteTypeEnum.TWENTY_NOTE));
	}

	@Test
	public void testGetTreasuryStatus() {
		Mockito.doReturn(35).when(this.moneyReserveMocked).getNumberOfNotes(NoteTypeEnum.FIFTY_NOTE);
		Mockito.doReturn(15).when(this.moneyReserveMocked).getNumberOfNotes(NoteTypeEnum.TWENTY_NOTE);
		
		TreasuryATMEnquiryBean treasuryResult = treasuryServiceMock.getTreasuryStatus();
		
		assertNotNull(treasuryResult);
		assertNotNull(treasuryResult.getTreasury());
		assertNotNull(treasuryResult.getTreasury().getNotes());
		assertEquals(2, treasuryResult.getTreasury().getNotes().size());
		assertEquals(new Integer(35), treasuryResult.getTreasury().getNotes().get(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(new Integer(15), treasuryResult.getTreasury().getNotes().get(NoteTypeEnum.TWENTY_NOTE));
	}

	@Test
	public void testGetEmptyTreasuryStatus() {
		Mockito.doReturn(0).when(this.moneyReserveMocked).getNumberOfNotes(NoteTypeEnum.FIFTY_NOTE);
		Mockito.doReturn(0).when(this.moneyReserveMocked).getNumberOfNotes(NoteTypeEnum.TWENTY_NOTE);
		
		TreasuryATMEnquiryBean treasuryResult = treasuryServiceMock.getTreasuryStatus();
		
		assertNotNull(treasuryResult);
		assertNotNull(treasuryResult.getTreasury());
		assertNotNull(treasuryResult.getTreasury().getNotes());
		assertEquals(2, treasuryResult.getTreasury().getNotes().size());
		assertEquals(new Integer(0), treasuryResult.getTreasury().getNotes().get(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(new Integer(0), treasuryResult.getTreasury().getNotes().get(NoteTypeEnum.TWENTY_NOTE));
	}
	
	@Test
	public void testGetUniqueNoteTreasuryStatus() {
		Mockito.doReturn(10).when(this.moneyReserveMocked).getNumberOfNotes(NoteTypeEnum.TWENTY_NOTE);
		
		TreasuryATMEnquiryBean treasuryResult = treasuryServiceMock.getTreasuryStatus();
		
		assertNotNull(treasuryResult);
		assertNotNull(treasuryResult.getTreasury());
		assertNotNull(treasuryResult.getTreasury().getNotes());
		assertEquals(2, treasuryResult.getTreasury().getNotes().size());
		assertEquals(new Integer(0), treasuryResult.getTreasury().getNotes().get(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(new Integer(10), treasuryResult.getTreasury().getNotes().get(NoteTypeEnum.TWENTY_NOTE));
	}

}
