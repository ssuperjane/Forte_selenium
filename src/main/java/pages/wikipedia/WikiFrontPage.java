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
		url=props.getProperty("amazon.front.page");
	}

	@Override
	protected void verifyPage() {
		By wikiLogo = new By.ByCssSelector("#www-wikipedia-org > h1 > img");
		wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(wikiLogo));
	}
	
}
