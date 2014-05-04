/**
 * 
 */
package com.money.atm.view.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Named;

import com.money.atm.technical.ContextFactory;
import com.money.atm.technical.constant.AnswerYesNoEnum;
import com.money.atm.technical.properties.MessageGenericEnum;
import com.money.atm.technical.properties.MessageUXEnum;

/**
 * Class getting the user input.
 * @author hloos
 */
@Named
public class UserInput {

	//private Scanner input = new Scanner(System.in);
	
	/**
	 * Get the input (Number format) of the user.
	 * 
	 * @return the user input
	 */
	public int getUserNumberInput() {
//		Scanner input = new Scanner(System.in);
//		while(!input.hasNextInt()) {
//			input = new Scanner(System.in);
//		}
//		int result = input.nextInt();
//		input.close();
//		return result;
		Integer input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (input == null || input < 0) {
			try {
				input = Integer.parseInt(br.readLine());
				
				if(input < 0)
					System.err.println(ContextFactory.getMessage(MessageUXEnum.INPUT_NEGATIVENUMBER_ERROR));
			} catch (NumberFormatException nfe) {
				System.err.println(ContextFactory.getMessage(MessageUXEnum.INPUT_NOTNUMBER_ERROR));
			} catch (IOException e) {
				System.err.println(ContextFactory.getMessage(MessageGenericEnum.GENERIC_ERROR));
				break;
			}
		}

		return input;
	}

	/**
	 * Get the input (AnswerYesNoEnum format) of the user.
	 * 
	 * @return the user input
	 */
	public AnswerYesNoEnum getUserConfirmationInput() {
		AnswerYesNoEnum result = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (result == null) {
			try {
				AnswerYesNoEnum userInput = AnswerYesNoEnum.getYesNoEnum(br.readLine());
				if(userInput == null) {
					System.err.println(ContextFactory.getMessage(MessageUXEnum.INPUT_NOTVALID_ENTRY));
				} else {
					result = userInput;
				}
			} catch (IOException e) {
				System.err.println(ContextFactory.getMessage(MessageGenericEnum.GENERIC_ERROR));
				break;
			}
		}

		return result;
	}
}
