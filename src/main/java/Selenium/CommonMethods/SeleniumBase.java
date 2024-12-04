package Selenium.CommonMethods;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Interface.BrowserElements;
import Interface.Locators;

public class SeleniumBase implements BrowserElements {
    
    private static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();
    private WebDriverWait wait;
    public Actions act;
    public JavascriptExecutor js;
    // Initialize WebDriverWait based on driver instance
    public WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait;
    }

    public void setDriver(WebDriver driver) {
        tldriver.set(driver);
    }

    public WebDriver getDriver() {
        return tldriver.get();
    }

    @Override
    public void onStartApp(String Url) {
//    	ChromeOptions option=new ChromeOptions();
//    	option.addArguments("--headless");
        setDriver(new ChromeDriver());
        getDriver().manage().window().maximize();
        getDriver().get(Url);
        // Use WebDriverWait explicitly, removing implicitWait
    }

    @Override
    public void onTearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    @Override
    public boolean verifyHomePage(WebElement ele) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(ele));
            return ele.isDisplayed();
        } catch (Exception e) {
            System.err.println("Element is not visible: " + e.getMessage());
            return false;
        }
    }

    @Override
    public WebElement LocateElement(Locators locatortype, String value) {
        switch (locatortype) {
            case ID:
                return getDriver().findElement(By.id(value));
            case XPATH:
                return getDriver().findElement(By.xpath(value));
            case TAGNAME:
                return getDriver().findElement(By.tagName(value));
            case CSS:
                return getDriver().findElement(By.cssSelector(value));
            case LINKTEXT:
                return getDriver().findElement(By.linkText(value));
            case PARTIAL_LINKTEXT:
                return getDriver().findElement(By.partialLinkText(value));
            case NAME:
                return getDriver().findElement(By.name(value));
            case CLASS:
                return getDriver().findElement(By.className(value));
            default:
                System.err.println("Invalid locator type");
                return null;
        }
    }

    @Override
    public void click(WebElement ele) {
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(ele));
            ele.click();
        } catch (Exception e) {
            System.err.println("Element is not clickable: " + e.getMessage());
        }
    }

    @Override
    public void clearAndType(WebElement ele, String value) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(ele));
            ele.clear();
            ele.sendKeys(value);
        } catch (Exception e) {
            System.err.println("Element interaction failed: " + e.getMessage());
        }
    }

    @Override
    public void switchToWindow(String title) {
        Set<String> windowHandles = getDriver().getWindowHandles();
        for (String eachWindow : windowHandles) {
            getDriver().switchTo().window(eachWindow);
            if (getDriver().getTitle().equals(title)) {
                System.out.println("Switched to window with title: " + title);
                break;
            }
        }
    }

    @Override
    public void switchToFrame(WebElement ele) {
        try {
            getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
        } catch (Exception e) {
            System.err.println("Unable to switch to frame: " + e.getMessage());
        }
    }

    @Override
    public void clickJsExecutor(WebElement ele) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(ele));
            js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", ele);
        } catch (Exception e) {
            System.err.println("Unable to click the element using JS: " + e.getMessage());
        }
    }

    public void doubleClick(WebElement ele) {
        try {
            act = new Actions(getDriver());
            act.doubleClick(getWait().until(ExpectedConditions.elementToBeClickable(ele))).perform();
        } catch (Exception e) {
            System.err.println("Unable to double-click the element: " + e.getMessage());
        }
    }

    @Override
    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

	@Override
	public String getText(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
		String text = ele.getText();
		return text;
		
	}

	@Override
    public List<WebElement> LocateElements(Locators locatortype, String value) {
        switch (locatortype) {
            case ID:
                return getDriver().findElements(By.id(value));
            case XPATH:
                return getDriver().findElements(By.xpath(value));
            case TAGNAME:
                return getDriver().findElements(By.tagName(value));
            case CSS:
                return getDriver().findElements(By.cssSelector(value));
            case LINKTEXT:
                return getDriver().findElements(By.linkText(value));
            case PARTIAL_LINKTEXT:
                return getDriver().findElements(By.partialLinkText(value));
            case NAME:
                return getDriver().findElements(By.name(value));
            case CLASS:
                return getDriver().findElements(By.className(value));
            default:
                System.err.println("Invalid locator type");
                return null;
        }
    }

	@Override
	public SearchContext getShadowRoot() {
		SearchContext shadowRoot = LocateElement(Locators.CSS,"#root").getShadowRoot();
		return shadowRoot;
		
	}

	@Override
	public void jsScrollPage() throws InterruptedException {
		
		js= (JavascriptExecutor) getDriver();
		long oldHeight = (long) js.executeScript("return document.body.scrollHeight");
		
		while(true)
		{
			js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
			Thread.sleep(1000);
			
			long newHeight = (long) js.executeScript("return document.body.scrollHeight");
			
			if(newHeight==oldHeight) {
				break;
			}else {
				oldHeight=newHeight;
				
			}
									
			
			
		}
		
		
		
		
		
	}
}
