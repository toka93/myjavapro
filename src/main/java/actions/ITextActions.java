package actions;

import org.openqa.selenium.WebDriver;

import webelement.ElementLocator;

public interface ITextActions {

    public void enterTextinField(WebDriver driver,ElementLocator element, String text);

    public String getElementText(WebDriver driver,ElementLocator element);
}
