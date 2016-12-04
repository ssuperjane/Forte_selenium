package wikitest;

import static org.junit.Assert.*;
import org.junit.Test;
import framework.FrameworkBase;
import pages.wikipedia.ResultPage;
import pages.wikipedia.WikiFrontPage;
import util.PropertiesLoader;

public class WikiWeightNameTest extends FrameworkBase{

	PropertiesLoader props = new PropertiesLoader();
	
	@Test
	public void wikiWeightNameTest() {
		String searchTerm = "Periodic Table";
		
		WikiFrontPage frontPage = new WikiFrontPage();
		frontPage.openPage();
		frontPage.searchPeriodicTable(searchTerm);
		ResultPage resultPage = frontPage.clickSearchButton();
		resultPage.getElementCount();
		resultPage.printAllElementsWeightAndName();
	}

}
