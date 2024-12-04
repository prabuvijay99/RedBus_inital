package Selenium.TestCases;

import org.testng.annotations.Test;

import Selenium.CommonMethods.commonMethods;
import Selenium.Pages.HomePage;

public class TCenter extends commonMethods {
	@Test
	public void verify() throws InterruptedException {
		new HomePage()
		.enterSourceCity()
		.enterDestinationCity()
		.selectMonthAndYear("Nov 2024")
		.selectDate("25")
		.clickSearchBusesBtn()
		.filterLiveTracking()
		.filterDepartureTime()
		.getBusCount()
		.getBusDetails();
	
	}

}
