package Selenium.CommonMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class commonMethods extends SeleniumBase {
	
	Properties prop=new Properties();
	
	@BeforeClass
	@Parameters({"browser"})
	public void preCondition() throws IOException {
		
		FileInputStream fis=new FileInputStream("./src/main/resources/config.properties");
		prop.load(fis);
		onStartApp(prop.getProperty("url"));

	}
	@AfterClass
	public void postCondition() {
//		onTearDown();

	}
}
