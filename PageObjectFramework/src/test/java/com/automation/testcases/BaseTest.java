package com.automation.testcases;

import org.testng.annotations.AfterSuite;

import com.automation.base.Page;

public class BaseTest {

	@AfterSuite
	public void tearDown() {
		Page.quit();
	}
}
