package test;


import java.nio.file.Paths;

import com.microsoft.playwright.*;

import playwright.IPlayWrightActions;
import playwright.PlayWrightActions;

import java.util.regex.Pattern;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Listeners(test.ListenerTest.class)

public class playwrighttest {

	Page page;
	
	  Playwright playwright; Browser browser; 
	 
	  IPlayWrightActions  playwrightac = new PlayWrightActions();
	// public static void main(String[] args) {
	
	@Test
	public void test1()
	{
		    
	 playwright = Playwright.create();
	 browser = playwright.chromium()
			.launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(50));
	page=	playwrightac.openChromeWithURL("https://playwright.dev/", browser);
		      assertThat(page).hasTitle(Pattern.compile("Playwright"));
		      playwrightac.takeFullScreenshot(page, "screenshots/screen.png");
		  // page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
		      
				
				  
				 
		
}
	
	
	
	
	@AfterMethod
	public void teardown()
	{
		page.close();
		browser.close();
		playwright.close();
	}
	
	
	
	
}