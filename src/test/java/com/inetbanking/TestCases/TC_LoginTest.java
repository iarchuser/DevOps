package com.inetbanking.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.PageObjects.LoginPage;

public class TC_LoginTest extends BaseClass {
	
	@Test
	public void LoginTest() throws IOException
	{
		driver.get(appurl);
		logger.info("Application Launched");
		LoginPage lp = new LoginPage(driver);
		lp.ApplicationLogin(user, pwd);
		logger.info("Username and Password entered and cliecked on Login button");
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login Successful");
		}
		else
		{
			CaptureScreenshot(driver, "TC_LoginTest");
			Assert.assertTrue(false);
			logger.info("Login Failed");
		}
	}

}
