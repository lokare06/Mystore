package com.shop.TestCases;

import org.testng.annotations.Test;

import com.shop.PageObject.FooterPage;

@Test
public class TC_FooterTest_001 extends BaseClass {
	public void footerTest() throws Exception {

		logger.info("URL is opened");

		FooterPage fp = new FooterPage(driver);

		fp.verifyNewsletter();
		logger.info("newletter Subscribe or not");
		fp.socialMedialinks();
		fp.footerlinkcol1();
		logger.info("links are working or not");
		fp.navigateBack();

		fp.verifylinkscol2();
		fp.verifylinkcol3();
		fp.verifyaddress();
		fp.verifycallus();
		fp.verifyemailid();
	}
}