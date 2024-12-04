package Interface;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public interface BrowserElements {
	
	
	public void onStartApp(String Url);

	public void onTearDown();
	
	
	public boolean verifyHomePage(WebElement ele);
	
	public void click(WebElement ele);	

	public WebElement LocateElement(Locators locatortype, String value);
	
	void clearAndType(WebElement ele,String value);	
	
	void switchToWindow(String title);
	
	void switchToFrame(WebElement ele);
	
	void clickJsExecutor(WebElement ele);
	
	void switchToDefaultContent();
	
	String getText(WebElement ele);
	
	public List<WebElement> LocateElements(Locators locatortype, String value);
	
	SearchContext getShadowRoot();
	
	void jsScrollPage() throws InterruptedException;

}
