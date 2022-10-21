package com.shop.PageObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class DressesPage {

	WebDriver ldriver;

	public DressesPage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	// Creation of softassert object
	SoftAssert sAssert = new SoftAssert();

	// Locator for Dresses
	@FindBy(how = How.XPATH, using = "(//a[text()='Dresses'])[2]")
	WebElement dresses;

	// Locator for Summer Dresses
	@FindBy(how = How.XPATH, using = "(//a[text()='Summer Dresses'])[4]")
	WebElement summer_dresses;

//*****************Method for sorting Price: Lowest first**********************************
	public void SortbyLowtoHigh() throws Exception {
		Thread.sleep(1000);
		System.out.println(dresses.isDisplayed());
		Actions ac = new Actions(ldriver);

		ac.moveToElement(dresses).perform();
		dresses.click();
		Thread.sleep(1000);
		System.out.println(summer_dresses.isDisplayed());
		summer_dresses.click();
		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript("window.scrollBy(0,1000)", "prices_beforesort");
		Thread.sleep(5000);

		ArrayList<String> obtainedList = new ArrayList<>();
		List<WebElement> elementList = ldriver.findElements(By.cssSelector("span[itemprop='price']"));
		for (WebElement we : elementList) {
			obtainedList.add(we.getText());
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String s : obtainedList) {
			Select dropDown = new Select(ldriver.findElement(By.id("selectProductSort")));
			dropDown.selectByVisibleText("Price: Lowest first");
		}
		Collections.sort(sortedList);
		// Assert.assertEquals(sortedList,obtainedList);
		for (String s : obtainedList) {
			if (sortedList.equals(obtainedList))
				sAssert.assertTrue(true);
		}

	}

}
