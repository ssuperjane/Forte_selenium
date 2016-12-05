package pages.wikipedia;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.AbstractPage;

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
		By atomicWeightLocator = new By.ByXPath("./a/span/span[2]");
		By atomicNameLocator = new By.ByXPath("./a/span/span[1]");

		List<WebElement> elementList = driver.findElements(allElementsLocator);

		LOGGER.info(elementList);
		final String elementHorizontalLogSpacer = "========================================";
		LOGGER.info(elementHorizontalLogSpacer);

		for(int i=0; i < elementList.size(); i++) {
			WebElement currentElement = elementList.get(i);
			WebElement currElementName = currentElement.findElement(atomicNameLocator);
			WebElement curreElementWeight = currentElement.findElement(atomicWeightLocator);

			LOGGER.debug("Element # " + (i+1));
			LOGGER.info(formatter(currElementName.getText(), curreElementWeight.getText()));
			LOGGER.debug(elementHorizontalLogSpacer);
		}
	}

	public int getSumOfElements() {
		int sum = 0;
		By atomicWeightLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr/td/span/a/span/span[2]");
		List<WebElement> elementList = driver.findElements(atomicWeightLocator);

		for(int i=0; i < elementList.size(); i++) {
			WebElement currentElement = elementList.get(i);
			String weight = currentElement.getText();
			if(weight!=null){
				sum += Integer.parseInt(weight);
			}
		}
		LOGGER.info("Sum of Elements : " + sum);
		return sum;
	}

	public String findElementName(int weight) {
		By atomicWeightLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr/td/span/a/span/span[2]");
		By atomicNameLocator = new By.ByXPath("../span[1]");
		List<WebElement> elementList = driver.findElements(atomicWeightLocator);

		for(int i=0; i < elementList.size(); i++) {
			WebElement currentElement = elementList.get(i);
			String atomicWeight = currentElement.getText();
			if(atomicWeight!=null){
				if(Integer.parseInt(atomicWeight)==weight){
					WebElement nameElement = currentElement.findElement(atomicNameLocator);
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

	public void printElementRow() {
		By allRowsInTable = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr");
		By elementsInRow = new By.ByXPath("./td/span/a/span");

		List<WebElement> rows = driver.findElements(allRowsInTable);

		if(rows != null) {
			LOGGER.info(rows.size());
			final String elementHorizontalLogSpacer = "========================================";
			int cnt = 1;
			for(int i=0; cnt <= 7; i++) {
				WebElement currentElement = rows.get(i);

				try {
					List<WebElement> automicInRow = currentElement.findElements(elementsInRow);
					
					if(automicInRow.isEmpty()) {
						continue;
					}

					LOGGER.info(elementHorizontalLogSpacer);
					LOGGER.info("   ROW " + (cnt));
					LOGGER.info(elementHorizontalLogSpacer);

					printAtomicInfo(automicInRow);

					cnt++;
				} catch(NoSuchElementException exception) {
					LOGGER.info("NoSuchElementException");
				}
			}
		}
	}

	private void printAtomicInfo(List<WebElement> atomics) {
		for(int i=0; i < atomics.size(); i++) {
			WebElement currentElement = atomics.get(i);
			WebElement currElementName = currentElement.findElement(new By.ByXPath("./span[1]"));
			WebElement curreElementWeight = currentElement.findElement(new By.ByXPath("./span[2]"));

			String name = currElementName.getText();
			String weight = curreElementWeight.getText();

			LOGGER.info(formatter(name, weight));
		}
	}
	
	private String formatter(String name, String weight) {
		return String.format(" %1$s - %2$s ", weight, removeSpecialChar(name));
	}
}
