package com.shop.TestCases;

import org.testng.annotations.Test;

import com.shop.PageObject.CheckOutPage;

@Test
public class TC_CheckOutTest_001 extends BaseClass {

	public void checkout() throws Exception {

		CheckOutPage cp = new CheckOutPage(driver);

		cp.checkOut();
	}
}