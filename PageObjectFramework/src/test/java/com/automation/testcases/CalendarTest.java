package com.automation.testcases;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.automation.pages.ZohoAppPage;
import com.automation.utilities.TestUtil;

public class CalendarTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void calendarTest(HashMap<String, String> data) throws InterruptedException {
		ZohoAppPage appPage = new ZohoAppPage();
		appPage.goToCalendar().addToDo(data);
	}
}
