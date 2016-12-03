package page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import framework.DriverManager;
import util.PropertiesLoader;

public abstract class AbstractPage {
	
	static protected PropertiesLoader props = new PropertiesLoader();

	protected Logger LOGGER;
	protected String url;
	protected WebDriver driver;
	protected WebDriverWait wdWait;
	protected Actions actionBuilder;
	
	protected AbstractPage() {
		LOGGER = Logger.getLogger(this.getClass());
		driver = DriverManager.getDriver();
		wdWait = new WebDriverWait (driver,5);
		actionBuilder = new Actions(driver);
	}
	
	public void openPage() {
		if (url == null) {
			String errorMessage = "AbstractPage url needs to be initiated - it was null";
			throw new RuntimeException(errorMessage);
		}
		
		LOGGER.info("Opening page " + url);
		driver.get(url);
		
		LOGGER.info("Calling verifyPage()");
		verifyPage();
		
	}
	abstract protected void verifyPage();
}
