package com.automation.pages;

import com.automation.base.Page;
import com.automation.pages.calendar.CalendarPage;

public class ZohoAppPage extends Page {

	public void goToCRM() {
		click("CRMIcon_CSS");
	}

	public CalendarPage goToCalendar() throws InterruptedException {
		Thread.sleep(3000);
		click("CalendarIcon_CSS");
		return new CalendarPage();
	}

}
