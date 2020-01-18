package com.automation.rough;

import java.util.HashMap;

import com.automation.pages.HomePage;

public class LoginTest {

	public static void main(String[] args) throws InterruptedException {
		HashMap<String, String> data = new HashMap<>();
		data.put("note", "Tested from rough package.");
		HomePage home = new HomePage();
		home.goToLogin().doLogin("alexxsgrig@gmail.com", "Selenium123").goToCalendar().addToDo(data);
	}
}
