package pages.wikipedia;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.AbstractPage;
import util.TestUtil;

public class ResultPage extends AbstractPage {

	By header = By.cssSelector("#firstHeading");
	
	public ResultPage() {
		super();
	}
	
	@Override
	public void verifyPage(){
		try {
			wdWait.until(ExpectedConditions
					.visibilityOfElementLocated(header));
		} catch (Exception e) {
			String message = "Error waiting for header";
			LOGGER.error(message, e);
			Assert.fail(message);
		}
	}
	
	public int getElementCount() {
		By allElementsLocator = new By.ByCssSelector("#collapsibleTable0 > tbody > tr:nth-child(4) > td:nth-child(2) > span > a");

		List<WebElement> elementList = driver.findElements(allElementsLocator);
		if(elementList != null) {
			LOGGER.info(elementList.size());
			return elementList.size();
		}
		return -1;
		
	}

	public void printAllElementsWeightAndName() {
		By allElementsLocator = new By.ByCssSelector("#collapsibleTable0 > tbody > tr:nth-child(4) > td:nth-child(2) > span > a");
		By automicWeightLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr[4]/td[1]/span/a/span/span[2]");
		By automicNameLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr[4]/td[1]/span/a/span/span[1]");

		List<WebElement> elementList = driver.findElements(allElementsLocator);

		LOGGER.info(elementList);
		final String elementHorizontalLogSpacer = "========================================";
		LOGGER.info(elementHorizontalLogSpacer);

		for(int i=0; i < elementList.size(); i++) {
		LOGGER.info ("Element # " + (i+1));

		WebElement currentElement = elementList.get(i);
		actionBuilder.moveToElement(currentElement).perform();

		TestUtil.sleep(500);

		WebElement currElementName = currentElement.findElement(automicNameLocator);
		String automicName = currElementName.getText();
		WebElement curreElementWeight = currentElement.findElement(automicWeightLocator);
		String automicWeight = curreElementWeight.getText();
		LOGGER.info (automicWeight + "-" + automicName);
		LOGGER.info(elementHorizontalLogSpacer);
		}
	}

}
