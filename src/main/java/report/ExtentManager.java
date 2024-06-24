package report;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	static Logger log = LogManager.getLogger(ExtentManager.class);
	static String extentReport = "ExtentReport";

	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static LocalDate localDate = LocalDate.now();
	static LocalTime time = LocalTime.now();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");

	static String reportName = extentReport + time.format(formatter) + ".html";
//change to string buffer
	static StringBuffer htmlReport = new StringBuffer().append(System.getProperty("user.dir")).append(File.separator)
			.append(extentReport).append(File.separator).append(dtf.format(localDate)).append(File.separator)
			.append(reportName);
	static String htmlReportPath = htmlReport.toString();

	// Returns the ExtentReports instance, creating it if necessary
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();

		return extent;
	}

	// Creates a new ExtentReports instance with the given file name
	public static ExtentReports createInstance() {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(htmlReportPath);
		// Configures the HTML report's theme, title, encoding, and name
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(htmlReportPath);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(htmlReportPath);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter = new ExtentHtmlReporter(htmlReportPath);
		extent.attachReporter(htmlReporter);
		return extent;
	}

	// Updates the given HTML report file with new CSS and JS nodes
	public static void updateReportFile() throws IOException {
		String filePath = htmlReportPath;
		File htmlFile = new File(filePath);

		Document htmlDoc = Jsoup.parse(htmlFile, "UTF-8");

		// Get head (which includes the CSS new link) and body (which includes the JS
		// new link) nodes
		Element headNode = htmlDoc.selectFirst("html head");
		Element bodyNode = htmlDoc.selectFirst("html body");

		// Set new CSS and JS nodes to be added
		Element newCSSNode = Jsoup.parse(
				"<link href='https://www.extentreports.com/resx/v3html/css/extent.css' type='text/css' rel='stylesheet' />")
				.selectFirst("link");
		Element newScriptNode = Jsoup.parse(
				"<script src='https://www.extentreports.com/resx/v3html/js/extent.js' type='text/javascript'></script>")
				.selectFirst("script");

		// Get the old CSS and JS nodes that need to be replaced with new ones
		Element cssNode = htmlDoc.selectFirst("link[href*=https://cdn.jsdelivr]");
		Element jsNode = htmlDoc.selectFirst("script[src*=https://cdn.rawgit]");

		// Remove the nodes
		if (cssNode != null) {
			cssNode.remove();
		}
		if (jsNode != null) {
			jsNode.remove();
		}

		// Add the new nodes
		headNode.appendChild(newCSSNode);
		bodyNode.appendChild(newScriptNode);

		// Saving the file
		String htmlString = htmlDoc.outerHtml();
		FileUtils.writeStringToFile(htmlFile, htmlString, StandardCharsets.UTF_8);

	}

	// Logs a message with the given ExtentTest instance and boolean flag
	// If flag is true, logs as a pass message, otherwise logs as a fail message
	public static void logsreportsinfo(ExtentTest child, String message, boolean flag) {
		if (flag)

			child.pass(message + flag);

		else
			child.fail(message + flag);

		log.info(message);

	}

//Logs an info message with the given ExtentTest instance and message string
	public static void logsreportsinfo(ExtentTest child, String message) {
		child.info(message);
		log.info(message);
	}
}