package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import webelement.ElementLocator;

public class SignPage extends PageBase {
	String className = this.getClass().getSimpleName();

	List<ElementLocator> locators = objectRepositoryReader.readLocators(className);

	ElementLocator emailTextBox = objectRepositoryReader.getWebElementObject(locators, "EmailField");

	ElementLocator nextButton = objectRepositoryReader.getWebElementObject(locators, "NextButton");
	ElementLocator errorMsg = objectRepositoryReader.getWebElementObject(locators, "ErrorMessage");

	public SignPage(WebDriver driver) {
		super(driver);

	}

	public void enterEmail(WebDriver driver, String value)  {

		waitLoad.waitAllRequest(driver);
		textActions.enterTextinField(driver,emailTextBox, value);

	}

	public void clickOnNext(WebDriver driver) {

		waitActions.waitForElementToBeClickable(driver, nextButton, 10);
		waitLoad.waitAllRequest(driver);
		clickActions.clickOnElement(driver,nextButton);
		waitLoad.waitAllRequest(driver);

	}

	public String getErrorMessage(WebDriver driver) throws InterruptedException {
		waitActions.wait(10);
		waitLoad.waitAllRequest(driver);
		waitActions.waitForElementsToBeVisible(driver, errorMsg, 5);

		return textActions.getElementText(driver,errorMsg);

	}

}