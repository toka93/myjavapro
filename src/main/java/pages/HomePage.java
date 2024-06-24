package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import webelement.ElementLocator;

public class HomePage extends PageBase {
	String className = this.getClass().getSimpleName();

	List<ElementLocator> locators = objectRepositoryReader.readLocators(className);

	ElementLocator signinButton = objectRepositoryReader.getWebElementObject(locators, "SigninField");

	public HomePage(WebDriver driver) {
		super(driver);

	}

	public void openSignin(WebDriver driver)  {

		waitActions.waitForElementToBeClickable(driver, signinButton, 10);
		waitLoad.waitAllRequest(driver);
		clickActions.clickOnElement(driver,signinButton);

	}

}