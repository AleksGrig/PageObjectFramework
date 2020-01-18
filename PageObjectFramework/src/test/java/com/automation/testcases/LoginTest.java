package com.automation.testcases;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.automation.pages.HomePage;
import com.automation.utilities.TestUtil;

public class LoginTest extends BaseTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void loginTest(HashMap<String, String> data) throws InterruptedException {
		HomePage home = new HomePage();
		home.goToLogin().doLogin(data.get("username"), data.get("password"));
	}
}
