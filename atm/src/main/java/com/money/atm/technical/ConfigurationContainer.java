package com.money.atm.technical;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Generate beans in the container.
 * @author hloos
 */
@Configuration
public class ConfigurationContainer {

	private String[] baseNames = new String[] {
			"MessageUX",
			"MessageGeneric",
			"Parameter"
	};
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
		bundle.setBasenames(baseNames);
		return bundle;
	}
}
