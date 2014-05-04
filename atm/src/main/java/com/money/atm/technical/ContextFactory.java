package com.money.atm.technical;

import java.util.List;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.money.atm.technical.properties.IProperties;

/**
 * Give access to the targeted element of the bean of the IOC container,<br/> 
 * POJOS and internationalized messages.
 * @author hloos
 */
public final class ContextFactory {

	public static final String SCANNED_PACKAGE = "com.money.atm";

	private static ApplicationContext context = 
	          new AnnotationConfigApplicationContext(SCANNED_PACKAGE);
	
	private static ResourceBundleMessageSource ressource;
	
	/**
	 * Constructor.
	 */
	private ContextFactory() {
		//can't be set up
	}
	
	/**
	 * Get an object from spring for a given class.
	 * @param clazz
	 * @return object
	 */
	public static <T> T getBean(Class<T> clazz) {
		return (T) context.getBean(clazz);
	}
	/**
	 * Get an object from spring for a given name.
	 * @param className
	 * @return an object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String className) {
		return (T) context.getBean(className);
	}
	
	/**
	 * Get a message from a property file.
	 * @param messageKey
	 * @return the message
	 */
	public static String getMessage(IProperties messageKey) {
		return getResource().getMessage(messageKey.getKey(), null, Locale.getDefault());
	}
	
	/**
	 * Get a message from a property file.
	 * @param messageKey
	 * @param arg
	 * @return the message
	 */
	public static String getMessage(IProperties messageKey, Object arg) {
		return getResource().getMessage(messageKey.getKey(), new Object[]{arg}, Locale.getDefault());
	}
	
	/**
	 * Get a message from a property file.
	 * @param messageKey
	 * @param args
	 * @return the message
	 */
	public static String getMessage(IProperties messageKey, Object[] args) {
		return getResource().getMessage(messageKey.getKey(), args, Locale.getDefault());
	}
	
	/**
	 * Get a message from a property file.
	 * @param messageKey
	 * @return the message
	 */
	public static String getMessage(IProperties messageKey, List<Object> argList) {
		return getResource().getMessage(messageKey.getKey(), argList.toArray(), Locale.getDefault());
	}
	
	/**
	 * @return the ressourcebundle
	 */
	private static ResourceBundleMessageSource getResource() {
		if(ressource == null) 
			ressource = getBean(ResourceBundleMessageSource.class);
		return ressource;
	}
}
