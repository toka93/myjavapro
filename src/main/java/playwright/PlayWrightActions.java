package playwright;

import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public class PlayWrightActions implements IPlayWrightActions {

	Logger log = LogManager.getLogger(PlayWrightActions.class);

	public Page openChromeWithURL(String url, Browser browser) {
		Page page = browser.newPage();
		page.navigate(url);

		return page;

	}
	
	public void clickOnButton(Page page,String locator)
	{
		page.locator(locator).click();
	}
	public void checkButton(Page page,String locator)
	{
		page.locator(locator).check();
	}
	public void enterText(Page page,String locator,String value)
	{
		page.locator(locator).type(value);
	}
	public void uncheckButton(Page page,String locator)
	{
		page.locator(locator).uncheck();
	}
	
	public void clearTextBox(Page page,String locator)
	{
		page.locator(locator).clear();
	}
	public void doubleClickOnButton(Page page,String locator)
	{
		page.locator(locator).dblclick();
	}
	public String getPageTitle(Page page)
	{
	return	page.title();
	}
	public void hoverByMouse(Page page,String locator)
	{
		page.locator(locator).hover();
	}
	public void takeScreenshot(Page page,String path)
	{
		 page.screenshot(new Page.ScreenshotOptions()
	                .setPath(Paths.get(path)));
	}
	public void takeFullScreenshot(Page page,String path)
	{
		 page.screenshot(new Page.ScreenshotOptions()
	                .setPath(Paths.get(path)).setFullPage(true));
	}
	public void selectFromDDL(Page page,String locator,String value)
	{
		page.locator(locator).selectOption(value);
	}
	
}
