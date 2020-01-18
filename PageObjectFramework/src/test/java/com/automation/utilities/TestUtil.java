package com.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.automation.base.Page;

public class TestUtil extends Page {
	public static String screenshotName;

	public static void captureScreenshot() {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		screenshotName = date.toString().replace(":", "-").replace(" ", "_") + ".jpg";
		try {
			FileUtils.copyFile(scrFile, new File("target\\surefire-reports\\html\\" + screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);
		for (int rNum = 2; rNum <= rows; rNum++) {
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			if (testCase.equalsIgnoreCase(testName)) {
				if (excel.getCellData(sheetName, "Runmode", rNum).equalsIgnoreCase("Y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	@DataProvider(name = "dp")
	public static Object[][] getData(Method method) {
		String sheetName = method.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][1];
		HashMap<String, String> map = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			map = new HashMap<>();
			for (int colNum = 0; colNum < cols; colNum++) {
				map.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
			}
			data[rowNum - 2][0] = map;
		}
		return data;
	}
}
