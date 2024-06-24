package actions;

import org.openqa.selenium.WebDriver;

import webelement.ElementLocator;

public interface IClickActions {
	public void clickOnElement(WebDriver driver,ElementLocator element);

    public void clickOnElementUsingEnterKey(WebDriver driver,ElementLocator element);

    public void clickOnElementUsingActions(WebDriver driver,ElementLocator element);

    public void doubleClickOnElement(WebDriver driver,ElementLocator element);

    public void rightClickOnElement(WebDriver driver,ElementLocator element);

    public void hoverAndClickOnElement(WebDriver driver,ElementLocator element);

    public void dragAndDropElment(WebDriver driver,ElementLocator element1, ElementLocator element2);

    public void clickonEsc(WebDriver driver);

    public void clickonEnter(WebDriver driver);

    public void dragAndDropPixels(WebDriver driver, ElementLocator from, int sourcePixel, int destinationPixel);

    public void clickOnElementUsingJavaScript(WebDriver driver,ElementLocator element);


}
