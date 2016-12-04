package framework;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.PropertiesLoader;

public class DriverManager {
	
	final static Logger LOGGER = Logger.getLogger(DriverManager.class);
	
	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver> ();
	
	public static WebDriver getDriver() {
		
		WebDriver driver = driverThreadLocal.get();
		
		if (driver == null) {
			startDriver();
		}
		driver = driverThreadLocal.get();
		return driver;
	}
	
	public static void quitDriver() {
		
		WebDriver driver = driverThreadLocal.get();
		driverThreadLocal.set(null);
		
		if (driver != null) {
			LOGGER.info("Closing the driver");
			driver.quit();
			driver=null;
		} else {
			LOGGER.debug("Could not close driver - was null!");
		}
		driver = null;
	}
	
	private static void startDriver() {

		String message = null;

		WebDriver driver;
		PropertiesLoader props = new PropertiesLoader();
		String browser = props.getProperty("browser");

		LOGGER.info("Starting driver...");
		
		LOGGER.trace("browser from properties: " + browser);

		switch (browser) {
		case "firefox":
			message = "Firefox driver requested";
			driver = new FirefoxDriver();
			break;

		case "chrome":
			message = "Chrome driver requested";
			System.setProperty("webdriver.chrome.driver", "/Users/jhoon/Library/tools/chromedriver");
			driver = new ChromeDriver();
			break;

		default:
			message = "Unknown browser requested " + browser;
			LOGGER.fatal(message);
			throw new RuntimeException(message);
		}

		LOGGER.info(message);
		driver.manage().window().maximize();
		
		driverThreadLocal.set(driver);
	}

}
