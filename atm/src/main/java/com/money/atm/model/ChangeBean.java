/**
 * 
 */
package com.money.atm.model;

import java.util.EnumMap;
import java.util.Map;

import javax.inject.Named;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import com.money.atm.technical.constant.NoteTypeEnum;

/**
 * Bean representing an amount of notes.
 * @author hloos
 */
@Named
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@ToString
@EqualsAndHashCode
public class ChangeBean {
	
	private Map<NoteTypeEnum, Integer> notes;
	
	/**
	 * @return the notes
	 */
	public Map<NoteTypeEnum, Integer> getNotes() {
		return (notes == null) ? notes = new EnumMap<>(NoteTypeEnum.class) : notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(Map<NoteTypeEnum, Integer> notes) {
		this.notes = notes;
	}

	/**
	 * Add notes for the requested sum.
	 * @param noteType
	 * @param notesNumber
	 */
	public void putNotes(NoteTypeEnum noteType, int notesNumber) {
		this.getNotes().put(noteType, notesNumber);
	}
	
	/**
	 * @return the value of the contained money.
	 */
	public int geValueOfContainedChange() {
		int totalValue = 0;
		for(NoteTypeEnum noteType : notes.keySet()) {
			totalValue += notes.get(noteType) * noteType.getValue();
		}
		return totalValue;
	}
}
