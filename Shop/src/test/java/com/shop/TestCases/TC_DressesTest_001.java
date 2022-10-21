package com.shop.TestCases;

import org.testng.annotations.Test;

import com.shop.PageObject.DressesPage;

@Test
public class TC_DressesTest_001 extends BaseClass {
	public void dressestest() throws Exception {

		logger.info("URL is opened");

		DressesPage dp = new DressesPage(driver);
		// dp.xycoord();
		dp.SortbyLowtoHigh();

	}
}
