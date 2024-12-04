package Selenium.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Interface.Locators;
import Selenium.CommonMethods.SeleniumBase;

public class BusSearchResultPage extends SeleniumBase {
	
	public BusSearchResultPage verifyBusBookingResultPage(String value) {
		
		String Pagetitle = getDriver().getTitle();
		System.out.println(Pagetitle);
		Assert.assertTrue(Pagetitle.contains(value),"Page title is not matching");
		return  this;
		
	}
	
	public BusSearchResultPage filterLiveTracking() {
		WebDriverWait wait=getWait();
		WebElement liveTracking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='clearfix']//span[@class='d-color']")));
		liveTracking.click();
		return this;

	}

	public BusSearchResultPage filterDepartureTime() throws InterruptedException {
		WebDriverWait wait=getWait();
		WebElement afterCheckBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='dtAfter 6 pm']")));
		afterCheckBox.click();
		return this;
	}
	
	public BusSearchResultPage getBusCount() {
		String NoOfBuses = getText(LocateElement(Locators.XPATH, "//span[@class='f-bold busFound']"));	
		System.out.println(NoOfBuses);
		return this;

	}
	
	
	public BusSearchResultPage getBusDetails() throws InterruptedException {

		
		List<WebElement> busList = LocateElements(Locators.XPATH, "//div[@class='travels lh-24 f-bold d-color']");

		js= (JavascriptExecutor) getDriver();
		long oldHeight =(long) js.executeScript("return document.body.scrollHeight");
			
		while(true)
		{
			js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
			List<WebElement> newBusList = LocateElements(Locators.XPATH, "//div[@class='travels lh-24 f-bold d-color']");
			
			for(WebElement eachnewBus:newBusList) {
				if(!busList.contains(eachnewBus)) {
					busList.add(eachnewBus);
						
					}
				}
			
				
			long newHeight = (long) js.executeScript("return document.body.scrollHeight");
			
			if(newHeight==oldHeight) {
				break;
			}
			
			oldHeight=newHeight;
	}
		
		js.executeScript("window.scrollTo(0, 0);");
				
		for(WebElement eachBus:busList) {
					
			if(eachBus.getText().equals("SST LIMOLINER")) {
			String BusName = eachBus.getText();	
			System.out.println(BusName);
			click(LocateElement(Locators.XPATH, "//div[@class='travels lh-24 f-bold d-color']//following::div[@class='button view-seats fr']"));
			
			}
		}
		
	
			
			
		
		return this;

	}
	
}
