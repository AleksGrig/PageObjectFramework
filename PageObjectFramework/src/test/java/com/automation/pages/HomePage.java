package com.automation.pages;

import com.automation.base.Page;

public class HomePage extends Page {

	public void goToSupport() {
		click("supportLink_CSS");
	}

	public void goToSignUp() {
		click("singupLink_CSS");
	}

	public LoginPage goToLogin() throws InterruptedException {
		Thread.sleep(3000);
		click("loginLink_CSS");
		Thread.sleep(3000);
		click("backArrow_CSS");
		return new LoginPage();
	}
}
