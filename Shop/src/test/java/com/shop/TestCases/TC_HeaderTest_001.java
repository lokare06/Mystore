package com.shop.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.shop.PageObject.HeaderPage;

public class TC_HeaderTest_001 extends BaseClass {

	@Test
	public void headerTest() throws InterruptedException {

		logger.info("URL is opened");

		HeaderPage hp = new HeaderPage(driver);
//Method call to for Verifying image at header
		hp.verifyimg();
		logger.info("Verified image");
//Method call to contactus text at Header page
		hp.verifycontact();
		logger.info("Verified contact");
		// Method to verify callus field at Header page
		hp.verifyphonnum();
		logger.info("Verified phone no.");
		// Method to verify Sign in button at Header page
		hp.verifylogin();
		logger.info("Verified sign in button");
		// Method to verify the signin field redirect to sign in page at Header page
		hp.movetologinpage();
		logger.info("move to login page");

		// Method to verify the signin field redirect to Header page
		hp.movetobackpage();
		logger.info("move to back page");

		// Method to verify the contact us field redirect to contact us121 page at
		// Header page
		hp.movecontactuspage();
		logger.info("move to contact us page");

		hp.movetobackpage();
		logger.info("move to back page");

		if (driver.getTitle().equals("My Store")) {
			AssertJUnit.assertTrue(true);
			logger.info("Login Test passed");
		} else {
			AssertJUnit.assertTrue(false);
			logger.info("Login Test Failed");
		}

	}

}
