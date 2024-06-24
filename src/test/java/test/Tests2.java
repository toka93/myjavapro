package test;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import excel.ExcelTestParser;
import pages.HomePage;
import pages.SignPage;
import report.ExtentManager;

import static configuration.ConfigProperties.*;
@Listeners(test.ListenerTest.class)

public class Tests2 extends BaseConfiguration {
	Logger log = LogManager.getLogger(Tests2.class);

	String className = this.getClass().getSimpleName();

	String methodname;

	HomePage homePage;
	SignPage signPage;

	@BeforeMethod(alwaysRun = true)
	public void setup() throws IOException, URISyntaxException {

		startDriver(browser());
		log.info("send browser name as a parameter ");
		test.set(child);
		log.info("start driver browser in before method");
		excelTestParser = new ExcelTestParser(excelpath);
		homePage = new HomePage(driver);
		signPage = new SignPage(driver);
	}

	@Test
	public void Testweb232(Method method) throws AWTException, InterruptedException, IOException {

		methodname = method.getName();
		log.info("get test name to use it in extent report , name is : " + methodname);
		child = ((ExtentTest) parentTest.get()).createNode(methodname);
		ExtentManager.logsreportsinfo(child, "Open Home Page test");
	///	String mobile = excelTestParser.getCellValue(className, methodname, "Mobile");

		homePage.openSignin(driver);
		signPage.enterEmail(driver, "91819");
		signPage.clickOnNext(driver);
		String txt = signPage.getErrorMessage(driver).trim();
		ExtentManager.logsreportsinfo(child, " Message :" + txt);

		ExtentManager.logsreportsinfo(child, "Error Message Appear as Expected",
				txt.equals("Incorrect username or password."));

		screenshotPath = windowActions.takeScreenshot(driver, methodname);
		Assert.assertTrue(txt.equals("Incorrect username or password."));
	}

}
