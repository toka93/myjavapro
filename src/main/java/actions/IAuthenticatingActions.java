package actions;

import org.openqa.selenium.WebDriver;

import webelement.ElementLocator;

public interface IAuthenticatingActions {
	

    public void acceptAlert(WebDriver driver) throws InterruptedException;

    public void dismissAlert(WebDriver driver);

    public String getAlertText(WebDriver driver);

    public void sendAlertText(WebDriver driver, String text);

    public void acceptAlertandClickOk(WebDriver driver, ElementLocator element) throws InterruptedException;

}
