package framework;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

public class FrameworkBase {

	final static Logger LOGGER = Logger.getLogger(FrameworkBase.class);

	@Before
	public void setup() {
		LOGGER.info("Test starting...");
	}

	@After
	public void teardown() {

		DriverManager.quitDriver();

		LOGGER.info("==========================================");
	}
	
}
