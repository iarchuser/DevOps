package com.inetbanking.TestCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.Utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	String appurl = readconfig.getApplicationURL();
	String user = readconfig.getUserName();
	String pwd = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger = Logger.getLogger("I-NetBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.toLowerCase().equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		if(br.toLowerCase().equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./Drivers\\\\geckodriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", "./Drivers\\\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
		
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	
	public void CaptureScreenshot(WebDriver driver, String tName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("./Screenshots/" + tName + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("screenshot captured");
	}
	

}
