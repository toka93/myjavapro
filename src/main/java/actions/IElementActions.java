package actions;

import org.openqa.selenium.WebDriver;

import webelement.ElementLocator;

public interface IElementActions {
	public boolean isElementDisplayed(WebDriver driver, ElementLocator element);

	public boolean isElementEnabled(WebDriver driver, ElementLocator element);

	public int getCountOfElements(WebDriver driver, ElementLocator element);

	public String getAttribute(WebDriver driver, ElementLocator element, String AttributeName);

	public String getElementColor(WebDriver driver, ElementLocator element);

	public boolean isElementSelected(WebDriver driver, ElementLocator element);

}
