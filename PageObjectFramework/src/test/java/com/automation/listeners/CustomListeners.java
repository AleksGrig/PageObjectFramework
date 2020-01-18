package com.automation.listeners;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.automation.base.Page;
import com.automation.utilities.MonitoringMail;
import com.automation.utilities.TestConfig;
import com.automation.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	@Override
	public void onTestStart(ITestResult result) {
		// ExtentReports
		test = report.startTest(result.getName().toUpperCase());
		log.info(result.getName().toUpperCase());

		// Checking runmodes
		if (!TestUtil.isTestRunnable(result.getName(), excel)) {
			throw new SkipException("Skipping the test " + result.getName().toUpperCase() + "as Runmode is NO");
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		report.endTest(test);
		report.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		TestUtil.captureScreenshot();

		// ExtentReports
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " FAIL: " + result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		report.endTest(test);
		report.flush();

		// reportNG
		System.setProperty("org.uncommons.reportng.escape-output", "false"); // Turn on html in reportNG
		Reporter.log("Click to see ");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log(
				"<a target=\"_blank\" href=" + TestUtil.screenshotName + ">" + "<img src=" + TestUtil.screenshotName
						+ " height=200 width=200></img></a>");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getName().toUpperCase() + " skipped as Runmode is NO");
		report.endTest(test);
		report.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ISuite suite) {
		MonitoringMail mail = new MonitoringMail();
		String messageBody = "";
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/PageObjectProject/Extent_20Report/";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
