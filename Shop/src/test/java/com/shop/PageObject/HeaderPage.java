package com.shop.PageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class HeaderPage {
	@FindBy(id = "header")
	WebElement head;
	WebDriver ldriver;

	public HeaderPage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	SoftAssert softAssert = new SoftAssert();

	// Locator of image field
	@FindBy(className = "img-responsive")

	WebElement image;

	@FindBy(id = "contact-link")

	WebElement contactus;

	@FindBy(className = "shop-phone")

	WebElement phonno;

	@FindBy(className = "header_user_info")
	WebElement login;

	public boolean verifyimg()

	{

		return image.isDisplayed();

	}

	public boolean verifycontact()

	{

		return contactus.isEnabled();

	}

	public boolean verifyphonnum()

	{

		return phonno.isEnabled();

	}

	public boolean verifylogin()

	{

		return login.isEnabled();

	}

	public boolean movetologinpage() {
		login.click();
		if (ldriver.getTitle().equals("Login - My Store")) {
			Assert.assertTrue(true);
			return true;

		} else {
			Assert.assertTrue(false);
			return false;
		}

	}

	public boolean movecontactuspage() {
		contactus.click();

		if (ldriver.getTitle().equalsIgnoreCase("Contact us - My Store")) {

			Assert.assertTrue(true);
			return true;

		} else {
			Assert.assertTrue(false);
			return false;
		}

	}

	public void movetobackpage() {
		ldriver.navigate().back();
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

}
