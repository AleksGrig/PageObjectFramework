package com.automation.pages.calendar;

import java.util.HashMap;

import com.automation.base.Page;

public class CalendarPage extends Page {

	public void chooseDay() {
		click("dayLink_XPATH");
	}

	public void addToDo(HashMap<String, String> data) throws InterruptedException {
		Thread.sleep(3000);
		click("dayTaskBox_XPATH");
		type("taskNameBox_CSS", data.get("note"));
		click("submitButtonCalendar_XPATH");
	}
}
