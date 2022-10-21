package com.shop.PageObject;

import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import org.testng.asserts.SoftAssert;

public class FooterPage {

	@FindBy()
	WebElement foot;
	WebDriver ldriver;

	public FooterPage(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	SoftAssert s = new SoftAssert();
	// **************Locators of Newletter field************
	// locator for emil textbox
	@FindBy(how = How.NAME, using = "email")
	WebElement newslettertxt;

	// submit button of Newsletter
	@FindBy(how = How.NAME, using = "submitNewsletter")
	WebElement submitbtn;

	// Locator for new user
	@FindBy(how = How.CSS, using = "p[class*='alert-success']")
	WebElement validemail;

	// Locator for Already Register user
	@FindBy(how = How.CSS, using = "p[class*='alert-danger']")
	WebElement registeredemail;

	// **************Social Media Links*****************
	// Locator for Facebook link
	@FindBy(how = How.CLASS_NAME, using = "facebook")
	WebElement facbooklink;

	// Locator for Twitter link
	@FindBy(how = How.CLASS_NAME, using = "twitter")
	WebElement twitterlink;

	// Locator for Yutube link
	@FindBy(how = How.CLASS_NAME, using = "youtube")
	WebElement youtubelink;

	// Locator for Google Pluse link
	@FindBy(how = How.CLASS_NAME, using = "google-plus")
	WebElement gpluselink;

	// ****************Locator for Footer Links***************
	// Locator for Footer link column1
	@FindBy(how = How.CSS, using = "section[class*='blockcategories']")
	WebElement footerlink1;

	// Locator of footer1 child
	@FindBy(how = How.XPATH, using = "//section[@class='blockcategories_footer footer-block col-xs-12 col-sm-2']/div")
	WebElement footerchild11;

	// Locator for Footer links for column2
	@FindBy(how = How.CLASS_NAME, using = "toggle-footer")
	WebElement footerlink2;

	// Locator for Footer links for column3
	@FindBy(how = How.CSS, using = "div[class='block_content toggle-footer']")
	WebElement footerlink3;

	// **********Store Information at Footer************
	// Locator of address at footer
	@FindBy(how = How.CLASS_NAME, using = "icon-map-marker")
	WebElement map_marker;

	// Locator of phone number at footer
	@FindBy(how = How.CLASS_NAME, using = "icon-phone")
	WebElement call_us;

	// Locator for Email Id at footer
	
	@FindBy(how = How.LINK_TEXT, using = "support@seleniumframework.com")
	WebElement emailId;

	// ************* Method to navigate to back page**********
	public void navigateBack() {
		ldriver.navigate().back();
	}

	// ******** Method to verify Newsletter field***********
	public boolean verifyNewsletter() {
		newslettertxt.click();
		newslettertxt.sendKeys("lokare.deepali@gmail.com");
		submitbtn.click();
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		System.out.println(registeredemail.getText());
		
		if (registeredemail.getText().equals("Newsletter : This email address is already registered.")) {
			s.assertTrue(true);

		} else if (validemail.getText().equals("Newsletter : You have successfully subscribed to this newsletter.")) {

			s.assertTrue(false);

		}

		return false;

	}

	// ***********Method to verify the social media links**************

	public void socialMedialinks() throws Exception {

		String parent = ldriver.getWindowHandle();

		System.out.println("Parent window id" + parent);
		Thread.sleep(3000);
		facbooklink.click();
		twitterlink.click();
		youtubelink.click();
		gpluselink.click();
		Set<String> allwindows = ldriver.getWindowHandles();
		int count = allwindows.size();
		System.out.println("Total no. of windows" + count);
		 
		for (String child : allwindows) {

			if (!parent.equalsIgnoreCase(child)) {
				System.out.println("Switching new tab");
				Thread.sleep(5000);
				ldriver.switchTo().window(child);
				System.out.println("Title is" + ldriver.getTitle());

				ldriver.close();
			}
		}
		ldriver.switchTo().window(parent);
		System.out.println("Parent Windoe title" + ldriver.getTitle());

	}

	// *********** Method to verify the Broken link of column1****************
	public boolean footerlinkcol1() {

		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		footerchild11.click();
		String text = footerchild11.getText();
		System.out.println(text);
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String title = ldriver.getTitle();
		System.out.println("Title of page=" + title);
		if (title.contains("404"))
			System.out.println("1 Link Broken");
		else
			System.out.println("Link is working");
		s.assertEquals(ldriver.getTitle(), "Women - My Store");
		return true;

	}

	// *********** Method to check Broken Links of footer column2********
	public void verifylinkscol2() throws Exception {
		List<WebElement> links = footerlink2.findElements(By.tagName("a"));
		Set<String> brokenlinkUrls = new HashSet<String>();
		System.out.println(links.size());
		for (WebElement link : links) {

			String linkURL = link.getAttribute("href");

			URL url = new URL(linkURL);
			URLConnection urlconnection = url.openConnection();
			HttpURLConnection httpurlconnection = (HttpURLConnection) urlconnection;
			httpurlconnection.setConnectTimeout(5000);
			httpurlconnection.connect();
			Thread.sleep(5000);
			ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			if (httpurlconnection.getResponseCode() == 404) {
				brokenlinkUrls.add(linkURL);
				System.out.println(linkURL + "-" + httpurlconnection.getResponseMessage());
			} else {
				System.err.println(linkURL + "-" + httpurlconnection.getResponseMessage());

				httpurlconnection.disconnect();
			}
		}

		for (String brokenlinkUrl : brokenlinkUrls) {
			System.err.println(brokenlinkUrl);
			ldriver.navigate().back();
		}
	}

	// *********** Method to check Broken Links of footer column3********
	public void verifylinkcol3() throws Exception

	{
		Thread.sleep(5000);
		List<WebElement> links = footerlink3.findElements(By.tagName("a"));
		Set<String> brokenlinkUrls = new HashSet<String>();
		System.out.println(links.size());
		for (WebElement link : links) {

			String linkURL = link.getAttribute("href");
			URL url = new URL(linkURL);
			URLConnection urlconnection = url.openConnection();
			HttpURLConnection httpurlconnection = (HttpURLConnection) urlconnection;
			httpurlconnection.setConnectTimeout(5000);
			httpurlconnection.connect();
			Thread.sleep(5000);
			ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			if (httpurlconnection.getResponseCode() == 404) {
				brokenlinkUrls.add(linkURL);
				System.out.println(linkURL + "-" + httpurlconnection.getResponseMessage());
			} else {
				System.err.println(linkURL + "-" + httpurlconnection.getResponseMessage());

				httpurlconnection.disconnect();
			}
		}

		for (String brokenlinkUrl : brokenlinkUrls) {
			System.err.println(brokenlinkUrl);
			ldriver.navigate().back();

		}
	}

	// ***************Method to verify the address at footer store
	// Information*******
	public void verifyaddress() {

		map_marker.isDisplayed();
		s.assertEquals(map_marker.getText(), "Selenium Framework, Research Triangle Park,North Carolina,USA",
				"Test Passed");
	}

	// ***************Method to verify the callus at footer store Information*******
	public void verifycallus() {
		call_us.isDisplayed();
		s.assertEquals(call_us.getText(), "(347) 466-7432", "Test Passed");
	}

	// ***************Method to verify the email Id at footer store
	// Information*******
	public void verifyemailid() throws Exception {
		Thread.sleep(1000);
		emailId.isDisplayed();
		s.assertEquals(emailId.getText(), "support@seleniumframework.com", "Test Passed");
	}
}
