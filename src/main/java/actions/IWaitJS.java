package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface IWaitJS {

	 public void setDriver(WebDriver driver);

	    public void ajaxComplete(WebDriver driver);

	    public void waitForJQueryLoad(WebDriver driver);

	    public void waitForAngularLoad(WebDriver driver);

	    public void waitUntilJSReady(WebDriver driver);

	    public void waitUntilJQueryReady(WebDriver driver);

	    public void waitUntilAngularReady(WebDriver driver);

	    public void waitUntilAngular5Ready(WebDriver driver);

	    public void waitForAngular5Load(WebDriver driver);

	    public void angularLoads(WebDriver driver,String angularReadyScript);

	    public void waitAllRequest(WebDriver driver);

	    public void waitForElementAreComplete(By by, int expected);

	    public void waitForAnimationToComplete(String css);

	    public void poll(long milis);
}
