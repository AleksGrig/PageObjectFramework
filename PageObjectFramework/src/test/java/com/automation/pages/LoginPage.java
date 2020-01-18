package com.automation.pages;

import com.automation.base.Page;

public class LoginPage extends Page {

	public ZohoAppPage doLogin(String userName, String password) throws InterruptedException {
		Thread.sleep(3000);
		type("nameBox_CSS", userName);
		type("passwordBox_CSS", password);
		click("submitButton_CSS");
		return new ZohoAppPage();
	}

	public void forgotPassword() {

	}
}
