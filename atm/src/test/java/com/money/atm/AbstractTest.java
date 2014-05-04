/**
 * 
 */
package com.money.atm;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.money.atm.technical.ConfigurationContainer;

/**
 * @author hloos
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigurationContainer.class, loader=AnnotationConfigContextLoader.class)
public abstract class AbstractTest {

	public static final String SCANNED_MODEL = "com.money.atm.model";
	public static final String SCANNED_TECHNICAL = "com.money.atm.technical";

	protected ApplicationContext context = 
	          new AnnotationConfigApplicationContext(SCANNED_MODEL, SCANNED_TECHNICAL);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
}
