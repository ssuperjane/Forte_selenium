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

	public void printAllElementsWeightAndName() {
		By allElementsLocator = new ("div.title-box-odopod > a");
		By automicWeightLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr[4]/td[1]/span/a/span/span[2]");
		By automicNameLocator = new By.ByXPath("//*[@id=\"collapsibleTable0\"]/tbody/tr[4]/td[1]/span/a/span/span[1]");

		List<WebElement> elementList = driver.findElements(allElementsLocator);

		LOGGER.info();
		final String elementHorizontalLogSpacer = "========================================";
		LOGGER.info(elementHorizontalLogSpacer);

		for(int i=0; i < elementList.size(); i++) {
		LOGGER.info ("Movie # " + (i+1));

		WebElement currentMovie = elementList.get(i);
		actionBuilder.moveToElement(currentMovie).perform();

		TestUtil.sleep(500);

		WebElement currMovieTitle = currentMovie.findElement(automicNameLocator);
		String automicName = currMovieTitle.getText();
		WebElement curreMovieLength = currentMovie.findElement(automicWeightLocator);
		String movieLength = curreMovieLength.getText();
		LOGGER.into (automicWeight + "-" + automicName);
		LOGGER.info(elementHorizontalLogSpacer);
		}
	}

}
