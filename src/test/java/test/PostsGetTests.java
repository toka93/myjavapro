package test;

import apiclients.PostsClient;
import apiwrappers.RequestWrapper;
import apiwrappers.ResponseWrapper;
import models.Post;
import report.ExtentManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static apiutils.JsonSchemaValidator.isJsonSchemaValid;
import static apiutils.ObjectToStringJSONConverter.convertToStringJSON;
import static configuration.ConfigProperties.*;
@Listeners(test.ListenerTest.class)

public class PostsGetTests {

	Logger log = LogManager.getLogger(PostsGetTests.class);
	String methodname;
	private static final String URL = aPIbaseURL() + "posts";
	private PostsClient postsClient;
	private SoftAssert softAsserts;
	private static final Map<String, String> HEADERS = new HashMap<String, String>() {
		{
			put("Content-type", "application/json; charset=UTF-8");
		}
	};
	private ResponseWrapper<Post> responseWrapperPreconditions;

	protected static ExtentReports extent;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	protected static ThreadLocal<Object> test = new ThreadLocal<Object>();
	public ExtentTest child;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		extent = ExtentManager.createInstance();

	}

	@BeforeMethod
	public void init() {
		postsClient = new PostsClient();
		//softAsserts = new SoftAssert();

		Post post = Post.builder().userId(1).title("title").body("body").build();

		RequestWrapper<Post> requestWrapper = RequestWrapper.<Post>builder().headers(HEADERS).body(post).build();

		responseWrapperPreconditions = postsClient.postEntity(Post.class, requestWrapper, URL);
	}

	@Test
	public void testGetPosts(Method method) {
		methodname = method.getName();
		log.info("get test name to use it in extent report , name is : " + methodname);
		child = extent.createTest(methodname);

		RequestWrapper<Post> requestWrapper = RequestWrapper.<Post>builder().headers(HEADERS).build();

		ResponseWrapper<Post> responseWrapper = postsClient.getEntity(Post.class, requestWrapper,
				URL + "/" + responseWrapperPreconditions.getBody().getId());
		ExtentManager.logsreportsinfo(child, "Response status code should be 200",
				responseWrapper.getStatusCode() == 200);
		System.out.println(responseWrapper.getStatusCode());

		System.out.println(responseWrapper.getBody());
		ExtentManager.logsreportsinfo(child, "The search returns response with invalid json schema",
				isJsonSchemaValid("postsGetSchema.json", convertToStringJSON(responseWrapper.getBody())));
		ExtentManager.logsreportsinfo(child, "Response id should be correct",
				responseWrapper.getBody().getId() == responseWrapperPreconditions.getBody().getId());

		ExtentManager.logsreportsinfo(child, "Response userId should be correct",
				responseWrapper.getBody().getUserId() == responseWrapperPreconditions.getBody().getUserId());
		ExtentManager.logsreportsinfo(child, "Response title should be correct",
				responseWrapper.getBody().getTitle().equals(responseWrapperPreconditions.getBody().getTitle()));
		ExtentManager.logsreportsinfo(child, "Response body should be correct",
				responseWrapper.getBody().getBody().equals(responseWrapperPreconditions.getBody().getBody()));

	}

	@AfterMethod
	public void cleanup() {
		RequestWrapper<Post> requestWrapper = RequestWrapper.<Post>builder().headers(HEADERS)
				.body(responseWrapperPreconditions.getBody()).build();

		postsClient.deleteEntity(Post.class, requestWrapper,
				URL + "/" + responseWrapperPreconditions.getBody().getId());
	}

	@AfterSuite(alwaysRun = true)
	public void endSuite() throws IOException {
		log.info("end report in after class");
		extent.flush();
		ExtentManager.updateReportFile();

	}

}
