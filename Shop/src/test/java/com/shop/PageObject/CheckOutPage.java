package com.shop.PageObject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
 

public class CheckOutPage {
	WebDriver ldriver;
	

	public CheckOutPage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
//locator for signin button
	@FindBy(className = "header_user_info")
	WebElement signin;
//Locator for EmailId		
	@FindBy(how = How.CSS, using = "input[id='email']")
	WebElement emailId;
//Locator for Password
	@FindBy(how = How.CSS, using = "input[id='passwd']")
	WebElement pwd;
//Locator for Login Button
	@FindBy(how = How.CSS, using = "button[id='SubmitLogin']")
	WebElement login;

	// Locator for Dresses
	@FindBy(how = How.XPATH, using = "(//a[text()='Dresses'])[2]")
	WebElement dresses;

	// Locator for Summer Dresses
	@FindBy(how = How.XPATH, using = "(//a[text()='Summer Dresses'])[4]")
	WebElement summer_dresses;
//Locator for cart window
	@FindBy(how = How.XPATH, using = "(//div[@class='right-block'])[1]")
	WebElement cartcontainer;
//Locator for addtocart button
	@FindBy(how = How.XPATH, using = "(//a[@title='Add to cart'])[1]")
	WebElement addtoCart;
//Locator for Continue Shopping Button
	@FindBy(how = How.CSS, using = "span[title='Continue shopping']")
	WebElement Continueshop;
//Locator for Proceed Checkout button
	@FindBy(how = How.CSS, using = "a[title='Proceed to checkout']")
	WebElement proceedchkout1;
	//Locator for Proceed Checkout button
	@FindBy(how = How.XPATH, using = "//span[text()='Proceed to checkout']")
	WebElement proceedchkout2;
	//Locator for Proceed Checkout button
	@FindBy(how = How.CSS, using = "button[name='processAddress']")
	WebElement proceedchkout3;
	//Locator for checkbox to check terms and conditions
	@FindBy(how = How.CSS, using = "input[name='cgv']")
	WebElement termcondn;
	//Locator for proceed chkout and conditions
	@FindBy(how = How.CSS, using = "button[name='processCarrier']")
	WebElement proceedchkout4;
	
   //Locator for to pay by wire/online
	@FindBy(how = How.CSS, using = "a[title='Pay by bank wire']")
	WebElement paybywire;
	//Locator for to pay by cheque
	@FindBy(how = How.CSS, using = "a[title='Pay by check.']")
	WebElement paybycheque;
	//Locator for to other mode of payment
	@FindBy(how = How.CSS, using = "a[class*='button-exclusive']")
	WebElement othermodepaymt;
   //Locator for Confirmation of payment
	@FindBy(how = How.CSS, using = "button[class*='button-medium']")
	WebElement Confirmpymt;

//********Method for online shopping from start to end upto checkout *****
	public void checkOut() throws InterruptedException {

		 
		signin.click();

		emailId.sendKeys("deep123321@gmail.com");
		pwd.sendKeys("deep123" );
		login.click();
		Actions ac = new Actions(ldriver);

		ac.moveToElement(dresses).perform();
		dresses.click();
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		System.out.println(summer_dresses.isDisplayed());
		summer_dresses.click();
		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript("window.scrollBy(0,1000)", "addtoCart ");
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		ac.moveToElement(cartcontainer).perform();
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		addtoCart.click();
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		proceedchkout1.click();
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		proceedchkout2.click();
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		proceedchkout3.click();
		termcondn.click();
		proceedchkout4.click();
		paybywire.click();
		Confirmpymt.click();

	}
}
