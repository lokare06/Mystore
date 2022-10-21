package com.shop.TestCases;

 
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

 
import com.shop.utilites.ReadConfig;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	public String baseURL = readconfig.getApplicationURL();
	public String path = readconfig.getchromepath();
	public static WebDriver driver;

	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br)

	{
		logger = Logger.getLogger("Ecommerce");
		PropertyConfigurator.configure("log4j.Properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D://Drivers//chromedriver.exe");
			driver = new ChromeDriver();

		}
		driver.get(baseURL);
		driver.manage().window().maximize();
	}

	@AfterClass

	public void tearDown()

	{

		driver.quit();

	}

}
