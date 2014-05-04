/**
 * 
 */
package com.money.atm.dao;

import static org.junit.Assert.assertEquals;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.money.atm.AbstractTest;
import com.money.atm.dao.impl.MoneyReserveDAO;
import com.money.atm.technical.constant.NoteTypeEnum;
import com.money.atm.technical.exception.technical.DataAccessException;

public class IMoneyReserveDAOTest extends AbstractTest {

	private Map<NoteTypeEnum, Integer> reserve = new EnumMap<>(NoteTypeEnum.class);
	private IMoneyReserveDAO moneyReserve = new MoneyReserveDAO();
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		ReflectionTestUtils.setField(moneyReserve, "reserve", reserve);
	}

	/**
	 * Test method for {@link com.money.atm.dao.IMoneyReserveDAO#createNotesInReserve(com.money.atm.technical.constant.NoteTypeEnum, int)}.
	 */
	@Test
	public void testCreateNotesInReserve() {
		reserve.clear();
		moneyReserve.createNotesInReserve(NoteTypeEnum.FIFTY_NOTE, 45);
		moneyReserve.createNotesInReserve(NoteTypeEnum.TWENTY_NOTE, 15);
		assertEquals(2, reserve.size());
		assertEquals(new Integer(45), reserve.get(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(new Integer(15), reserve.get(NoteTypeEnum.TWENTY_NOTE));
	}
	
	//The Lombok annotation @NonNull send a NPE with the name of the null parameter as message.
	@Test(expected = NullPointerException.class)
	public void testCreateNotesInReserve_NullParameter() {
		moneyReserve.createNotesInReserve(null, 15);
	}

	/**
	 * Test method for {@link com.money.atm.dao.IMoneyReserveDAO#getNumberOfNotes(com.money.atm.technical.constant.NoteTypeEnum)}.
	 */
	@Test
	public void testGetNumberOfNotes() {
		reserve.clear();
		reserve.put(NoteTypeEnum.FIFTY_NOTE, 70);
		reserve.put(NoteTypeEnum.TWENTY_NOTE, 12);
		assertEquals(2, reserve.size());
		assertEquals(70, moneyReserve.getNumberOfNotes(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(12, moneyReserve.getNumberOfNotes(NoteTypeEnum.TWENTY_NOTE));
	}

	/**
	 * Test method for {@link com.money.atm.dao.IMoneyReserveDAO#getAvailableChange()}.
	 */
	@Test
	public void testGetAvailableChange() {
		reserve.clear();
		reserve.put(NoteTypeEnum.FIFTY_NOTE, 100);
		reserve.put(NoteTypeEnum.TWENTY_NOTE, 2);
		assertEquals(5040, moneyReserve.getAvailableChange().geValueOfContainedChange());
		assertEquals(2, moneyReserve.getAvailableChange().getNotes().size());
		assertEquals(new Integer(100), moneyReserve.getAvailableChange().getNotes().get(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(new Integer(2), moneyReserve.getAvailableChange().getNotes().get(NoteTypeEnum.TWENTY_NOTE));
	}
	
	/**
	 * Test method for {@link com.money.atm.dao.IMoneyReserveDAO#getAvailableChange()}.
	 */
	@Test
	public void testGetAvailableChange_OneOnlyAvailableNote() {
		reserve.clear();
		reserve.put(NoteTypeEnum.FIFTY_NOTE, 100);
		reserve.put(NoteTypeEnum.TWENTY_NOTE, 0);
		assertEquals(5000, moneyReserve.getAvailableChange().geValueOfContainedChange());
		assertEquals(1, moneyReserve.getAvailableChange().getNotes().size());
		assertEquals(new Integer(100), moneyReserve.getAvailableChange().getNotes().get(NoteTypeEnum.FIFTY_NOTE));
	}

	/**
	 * Test method for {@link com.money.atm.dao.IMoneyReserveDAO#updateNumberNotesInReserve(java.util.Map)}.
	 * @throws DataAccessException 
	 */
	@Test
	public void testUpdateNumberNotesInReserve() throws DataAccessException {
		reserve.clear();
		reserve.put(NoteTypeEnum.FIFTY_NOTE, 100);
		reserve.put(NoteTypeEnum.TWENTY_NOTE, 2);
		
		Map<NoteTypeEnum, Integer> newReserve = new EnumMap<>(NoteTypeEnum.class);
		newReserve.put(NoteTypeEnum.FIFTY_NOTE, 50);
		newReserve.put(NoteTypeEnum.TWENTY_NOTE, 15);
		
		moneyReserve.updateNumberNotesInReserve(newReserve);
		assertEquals(2, reserve.size());
		assertEquals(new Integer(50), reserve.get(NoteTypeEnum.FIFTY_NOTE));
		assertEquals(new Integer(15), reserve.get(NoteTypeEnum.TWENTY_NOTE));
	}
	
	//The Lombok annotation @NonNull send a NPE with the name of the null parameter as message.
	@Test(expected = NullPointerException.class)
	public void testUpdateNumberNotesInReserve_NullParameter() throws DataAccessException {
		moneyReserve.updateNumberNotesInReserve(null);
	}
	
	/**
	 * Test method for {@link com.money.atm.dao.IMoneyReserveDAO#updateNumberNotesInReserve(java.util.Map)}.
	 * @throws DataAccessException 
	 */
	@Test(expected = DataAccessException.class)
	public void testUpdateNumberNotesInReserve_DataException() throws DataAccessException {
		reserve.clear();
		reserve.put(NoteTypeEnum.FIFTY_NOTE, 100);
		
		Map<NoteTypeEnum, Integer> newReserve = new EnumMap<>(NoteTypeEnum.class);
		newReserve.put(NoteTypeEnum.FIFTY_NOTE, 50);
		newReserve.put(NoteTypeEnum.TWENTY_NOTE, 15);
		
		moneyReserve.updateNumberNotesInReserve(newReserve);
	}

}
