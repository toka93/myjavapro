package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;

public interface IPlayWrightActions {
    Page openChromeWithURL(String url, Browser browser);
    
    void clickOnButton(Page page, String locator);
    
    void checkButton(Page page, String locator);
    
    void enterText(Page page, String locator, String value);
    
    void uncheckButton(Page page, String locator);
    
    void clearTextBox(Page page, String locator);
    
    void doubleClickOnButton(Page page, String locator);
    
    String getPageTitle(Page page);
    
    void hoverByMouse(Page page, String locator);
    
    void takeScreenshot(Page page, String path);
    
    void takeFullScreenshot(Page page, String path);
    
    void selectFromDDL(Page page, String locator, String value);
}