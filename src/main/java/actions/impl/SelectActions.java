package actions.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import actions.ISelectActions;
import webelement.ElementLocator;
import webelement.ObjectRepositoryReader;
/**
 * SelectActions class in the Actions package contains methods to select options from a dropdown,
 * clear the selected options, and check if an element is selected or not.
 * 
 * Note: All methods take an ElementLocator and a WebDriver object as arguments to identify the 
 * dropdown element and perform the action on it.
 * 
 * Note: This class assumes that the dropdown is a Select WebElement.
 * 
 * @author Toka
 *
 */

public class SelectActions implements ISelectActions{
	Logger log = LogManager.getLogger(SelectActions.class);
	ObjectRepositoryReader objectRepositoryReader = new ObjectRepositoryReader();

	/**
	 * Selects an option from the dropdown by its index.
	 * @param index the index of the option to select.
	 * @param element the ElementLocator of the dropdown WebElement.
	 * @param driver the WebDriver object.
	 */
	public void selectOptionByIndex(WebDriver driver,int index ,ElementLocator element)
	{
	       WebElement webElement=objectRepositoryReader.getWebElement( driver, element);

		log.info("get list of elements");
		Select list = new Select(webElement);
		log.info("select by index {}" ,index);
		list.selectByIndex(index);
		
	}
	/**
	 * Selects an option from the dropdown by its value.
	 * @param value the value of the option to select.
	 * @param element the ElementLocator of the dropdown WebElement.
	 * @param driver the WebDriver object.
	 */
	public void selectOptionByValue(WebDriver driver,String value , ElementLocator element)
	{
		WebElement webElement=objectRepositoryReader.getWebElement( driver, element);
		log.info("select by option");
		Select list = new Select(webElement);
		list.selectByValue(value);
		
	}
	/**
	 * Clears all selected options from the dropdown.
	 * @param element the ElementLocator of the dropdown WebElement.
	 * @param driver the WebDriver object.
	 */
	public void clearSelection(WebDriver driver,ElementLocator element)
	{
		WebElement webElement=objectRepositoryReader.getWebElement( driver, element);
		log.info("clear all selections");
		Select list = new Select(webElement);
		list.deselectAll();
		
	}
	/**
	 * Selects an option from the dropdown by its visible text.
	 * @param value the visible text of the option to select.
	 * @param element the ElementLocator of the dropdown WebElement.
	 * @param driver the WebDriver object.
	 */
	public void selectOptionByVisibleText(WebDriver driver,String value ,ElementLocator element)
	{WebElement webElement=objectRepositoryReader.getWebElement( driver, element);
		log.info("select by element");
		Select list = new Select(webElement);
		list.selectByVisibleText(value);
		
	}
	
	/**
	 * Checks if the given WebElement is selected or not.
	 * @param element the ElementLocator of the WebElement to check.
	 * @param driver the WebDriver object.
	 * @return true if the WebElement is selected, false otherwise.
	 */
	
	public boolean isChecked(WebDriver driver,ElementLocator element)
	{
		WebElement webElement=objectRepositoryReader.getWebElement( driver, element);
	log.info("check if element is selected");
		return webElement.isSelected();
		
	}
	
	
	
	
}
