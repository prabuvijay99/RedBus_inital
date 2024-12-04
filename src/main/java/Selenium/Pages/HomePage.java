package Selenium.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Interface.Locators;
import Selenium.CommonMethods.SeleniumBase;

public class HomePage extends SeleniumBase {

	public HomePage verifyTheHomepageLogo() {
		boolean verifyHomePage = verifyHomePage(LocateElement(Locators.CLASS, "rb_logo"));
		Assert.assertTrue(verifyHomePage, "Logo id not visible");
		return this;
	}
	
	public HomePage enterSourceCity() throws InterruptedException {
		clearAndType(LocateElement(Locators.ID, "src"), "Chennai");
		WebDriverWait wait=getWait();
		WebElement source_City = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sc-gZMcBi hviMLb']")));
		source_City.click();
		return this;

	}
	
	public HomePage enterDestinationCity() throws InterruptedException {
		clearAndType(LocateElement(Locators.ID, "dest"), "Coimbatore");		
		WebDriverWait wait=getWait();
		WebElement destination_City = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sc-gZMcBi hviMLb']")));		
		destination_City.click();
		return this;
	}
		
	public  String selectTravelDate() {
		click(LocateElement(Locators.ID, "onwardCal"));
		String monthAndYear= getText(LocateElement(Locators.XPATH, "(//div[@class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr']//following::div)[1]"));
		String[] travelDt = monthAndYear.split("\n");
		String dt =travelDt[0];
		System.out.println(dt);
		return dt;
		
	}
	
	public HomePage clickNextMonth() {
		click(LocateElement(Locators.XPATH, "(//div[@class='DayNavigator__IconBlock-qj8jdz-2 iZpveD'])[3]"));
		return this;
	}

	public HomePage selectMonthAndYear(String month_Year) {
		
		while(true)
		{
			String selectTravelDate = selectTravelDate();
			if(selectTravelDate.equals(month_Year))
			{
				break;
			}else {
				clickNextMonth();
			}
		}
		return this;

	}
	
	public List<WebElement> getDates() {
		List<WebElement> dates = LocateElements(Locators.XPATH, "//div[@class='DayTilesWrapper__RowWrap-sc-19pz9i8-1 fGGTDM']//div//span");
		return dates;
	}
	
	public HomePage selectDate(String date) {
		List<WebElement> dates = getDates();
		while(true)
		{
			for(WebElement eachDate:dates) {
				if(eachDate.getText().equals(date)) {
					eachDate.click();
					break;
				}
				
				
			}
			return this;
		}
	}
	
	public BusSearchResultPage clickSearchBusesBtn() {
		click(LocateElement(Locators.ID, "search_button"));
		return new BusSearchResultPage();

	}
	
	
}

