package pages.wikipedia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.AbstractPage;
import util.TestUtil;

public class WikiFrontPage extends AbstractPage {

	public WikiFrontPage() {
		super();
		url=props.getProperty("wiki.front.page");
	}

	@Override
	protected void verifyPage() {
		By wikiLogo = new By.ByCssSelector("#www-wikipedia-org > h1 > img");
		wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(wikiLogo));
	}
	
	public void searchPeriodicTable(String searchTerm) {
		By searchBar = new By.ByXPath("//*[@id=\"searchInput\"]");
		By language = new By.ByCssSelector("#searchLanguage");
		
		LOGGER.info("Locating search bar and typing [" + searchTerm + "]");
		WebElement weSearchPeriodicTable = driver.findElement(searchBar);
		weSearchPeriodicTable.sendKeys(searchTerm);
		
		LOGGER.info("Searching in English");
		WebElement weLanguage = driver.findElement(language);
		
		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveToElement(weLanguage).perform();
		
		TestUtil.sleep(500);
	}
	
	public ResultPage clickSearchButton() {
		By searchButton = new By.ByCssSelector("#search-form > fieldset > button");
		
		LOGGER.info("Clicking on Search Button");
		driver.findElement(searchButton).click();
		
		ResultPage resultPage = new ResultPage();
		resultPage.verifyPage();

		return resultPage;
	}
	
}
