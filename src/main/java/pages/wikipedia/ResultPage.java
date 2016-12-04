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
		By allElementsLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr/td/span");

		List<WebElement> elementList = driver.findElements(allElementsLocator);
		if(elementList != null) {
			LOGGER.info(elementList.size());
			return elementList.size();
		}
		return -1;
		
	}

	public void printAllElementsWeightAndName() {
		By allElementsLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr/td/span");
		By automicWeightLocator = new By.ByXPath("./a/span/span[2]");
		By automicNameLocator = new By.ByXPath("./a/span/span[1]");

		List<WebElement> elementList = driver.findElements(allElementsLocator);

		LOGGER.info(elementList);
		final String elementHorizontalLogSpacer = "========================================";
		LOGGER.info(elementHorizontalLogSpacer);

		for(int i=0; i < elementList.size(); i++) {
			WebElement currentElement = elementList.get(i);
			WebElement currElementName = currentElement.findElement(automicNameLocator);
			WebElement curreElementWeight = currentElement.findElement(automicWeightLocator);

			String automicName = currElementName.getText();
			String automicWeight = curreElementWeight.getText();
			LOGGER.debug("Element # " + (i+1));			
			LOGGER.info(automicWeight + "-" + removeSpecialChar(automicName));
			LOGGER.debug(elementHorizontalLogSpacer);
		}
	}

	public int getSumOfElements() {
		int sum = 0;
		By automicWeightLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr/td/span/a/span/span[2]");
		List<WebElement> elementList = driver.findElements(automicWeightLocator);

		for(int i=0; i < elementList.size(); i++) {
			WebElement currentElement = elementList.get(i);
			String automicWeight = currentElement.getText();
			if(automicWeight!=null){
				sum += Integer.parseInt(automicWeight);
			}
		}
		LOGGER.info("Sum of Elements : " + sum);
		return sum;
	}

	public String findElementName(int weight) {
		By automicWeightLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr/td/span/a/span/span[2]");
		By automicNameLocator = new By.ByXPath("../span[1]");
		List<WebElement> elementList = driver.findElements(automicWeightLocator);

		for(int i=0; i < elementList.size(); i++) {
			WebElement currentElement = elementList.get(i);
			String automicWeight = currentElement.getText();
			if(automicWeight!=null){
				if(Integer.parseInt(automicWeight)==weight){
					WebElement nameElement = currentElement.findElement(automicNameLocator);
					LOGGER.info("Name of the element with the weight of [" + weight + "] is '" + removeSpecialChar(nameElement.getText()) + "' ");
					return removeSpecialChar(nameElement.getText());
				}
			}
		}
		return null;
	}
	
	private String removeSpecialChar(String org) {
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<org.length();i++) {
			char c = org.charAt(i);
			if( c >= 'A' && c <= 'z' ) {
				buf.append(c);
			}
		}
		return buf.toString();
	}

}
