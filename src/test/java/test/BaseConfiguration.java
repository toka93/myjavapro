package test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.logging.Level;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import actions.IWaitJS;
import actions.IWindowActions;
import actions.impl.WaitJS;
import actions.impl.WindowActions;

import static configuration.ConfigProperties.*;

import excel.ExcelTestParser;
import io.github.bonigarcia.wdm.WebDriverManager;
import report.ExtentManager;

public class BaseConfiguration {
	protected static ExtentReports extent;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	protected static ThreadLocal<Object> test = new ThreadLocal<Object>();
	public ExtentTest child;

	IWaitJS waitJS = new WaitJS();
	Logger log = LogManager.getLogger(BaseConfiguration.class);

	public  WebDriver driver;
	ITestResult result = null;

	String screenshotPath = "no path no screeenshot taken";
	String excel = "TestData";
	ExcelTestParser excelTestParser;

	IWindowActions windowActions= new WindowActions();

	StringBuffer excelBuffer = new StringBuffer()
	        .append(System.getProperty("user.dir"))
	        .append(File.separator)
	        .append(excel)
	        .append(File.separator)
	       .append(excel)
	       .append(".xlsx");
	String excelpath=excelBuffer.toString();
	
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		extent = ExtentManager.createInstance();

	}

	@BeforeClass(alwaysRun = true)
	public synchronized void beforeClass() throws MalformedURLException {

		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
	}

	public void startDriver(String browserName) throws MalformedURLException, URISyntaxException {
		if (type().equalsIgnoreCase("local")) {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				//.avoidBrowserDetection()
				log.info("start the chrome driver : ");
				driver = new ChromeDriver(chromeOption());

			}

			else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(firefoxOption());

			}

			else if (browserName.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();

			} else if (browserName.equalsIgnoreCase("safari")) {

				System.setProperty("webdriver.safari.noinstall", "true"); // To stop uninstall each time
				driver = new SafariDriver();

			}

		} else if (type().equalsIgnoreCase("remote")) {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(ChromeOptions.CAPABILITY, chromeOption());

			URI uri = new URI("http", null, seleniumHub(), 4444, "/wd/hub", null, null);
			URL url = uri.toURL();

			driver = new RemoteWebDriver(url, dc);
			

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		driver.navigate().to(appURL());
		waitJS.waitAllRequest(driver);

	}

	public static FirefoxOptions firefoxOption() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.tabs.remote.autostart", false);
		option.addPreference("browser.tabs.remote.autostart.1", false);
		option.addPreference("browser.tabs.remote.autostart.2", false);
		option.addPreference("browser.tabs.remote.force-enable", "false");

		return option;
	}

	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-web-security");
		options.addArguments("--allow-running-insecure-content");
		options.addArguments("--allow-insecure-localhost");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-features=VizDisplayCompositor");
		// added for netty exception
		options.addArguments("--remote-allow-origins=*");

		// options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		LoggingPreferences loggingprefs = new LoggingPreferences();
		loggingprefs.enable(LogType.BROWSER, Level.ALL);
		// options.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
		options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, "accept");

	

		if (headlessState().equalsIgnoreCase("true")) {
			options.addArguments("--headless");
			options.addArguments("start-maximized");
			options.addArguments("--window-size=1920,1080");

		} else if (headlessState().equalsIgnoreCase("false")) {

		}

		return options;

	}

	@AfterMethod(alwaysRun = true)
	public void stopDriver(ITestResult res) throws IOException {
		log.info("get status of test just run");
		if (res.getStatus() == ITestResult.SUCCESS) {
			log.info("test passes and screenshot is taken before driver closes in path " + screenshotPath);
		} else if (res.getStatus() == ITestResult.FAILURE) {
			log.info("test failed and screenshot is taken before driver closes in path " + screenshotPath);
			child.log(Status.FAIL, "Test failed ");
		} else if (res.getStatus() == ITestResult.SKIP) {
			log.info("test skipped and screenshot is taken before driver closes in path " + screenshotPath);
			child.log(Status.SKIP, "Test skipped");
		} else {
			log.info(
					"test finished with status other than (pass,fail,skip) and screenshot is taken before driver closes in path "
							+ screenshotPath);
			child.log(Status.ERROR, "Test skipped");
		}
		child.addScreenCaptureFromPath(screenshotPath);
		log.info("close driver browser in after method");

		driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void endSuite() throws IOException {
		log.info("end report in after class");
		extent.flush();
		ExtentManager.updateReportFile();

	}

}
