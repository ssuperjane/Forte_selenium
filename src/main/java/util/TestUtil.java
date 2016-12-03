package util;

import org.apache.log4j.Logger;
import pages.wikipedia.WikiFrontPage;

public class TestUtil {
	public final static Logger LOGGER = Logger.getLogger(WikiFrontPage.class);
	public static void sleep (long milliseconds) {
		try {
		Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		LOGGER.debug("message - awoke the thread from sleep!", e);
		}
	}
}