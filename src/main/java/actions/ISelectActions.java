package actions;

import org.openqa.selenium.WebDriver;

import webelement.ElementLocator;

public interface ISelectActions {
	public void selectOptionByIndex( WebDriver driver,int index, ElementLocator element);

    public void selectOptionByValue(WebDriver driver,String value, ElementLocator element);

    public void clearSelection(WebDriver driver,ElementLocator element);

    public void selectOptionByVisibleText(WebDriver driver,String value, ElementLocator element);

    public boolean isChecked(WebDriver driver,ElementLocator element);

}
