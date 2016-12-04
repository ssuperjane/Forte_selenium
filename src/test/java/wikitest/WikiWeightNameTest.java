package wikitest;

import static org.junit.Assert.*;

import org.junit.Before;
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
		
		assertTrue("The total number of elements is 118.",resultPage.getElementCount()==118);
		
		resultPage.printAllElementsWeightAndName();
	}


	@Test
	public void wikiTestNumber2() {
		String searchTerm = "Periodic Table";
		
		int weight = props.getPropertyAsInt("test2.atomic.weight");
		String name = props.getProperty("test2.atomic.name");
		
		WikiFrontPage frontPage = new WikiFrontPage();
		frontPage.openPage();
		frontPage.searchPeriodicTable(searchTerm);
		ResultPage resultPage = frontPage.clickSearchButton();
		
		assertTrue("The sum of elements is 7021.",resultPage.getSumOfElements()==7021);
		assertTrue("The name of the element with atomic weight of '" + weight +"'", name.equals(resultPage.findElementName(weight)));
	}
}
