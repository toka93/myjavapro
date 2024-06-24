package webelement;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IObjectRepositoryReader {

	 public Map<String, String> readRepoPage(String fileName);

	    public List<String> readLocatorTypeandValue(String value);

	    public List<ElementLocator> readLocators(String pageName);

	    public WebElement webEl(WebDriver driver, Function<String, By> locatorStrategy, String locator);

	    public List<WebElement> webEls(WebDriver driver, Function<String, By> locatorStrategy, String locator);

	    public ElementLocator getWebElementObject(List<ElementLocator> locators, String locatorname);

	    public WebElement getWebElement(WebDriver driver, ElementLocator elementlocatorObject);

	    public List<WebElement> getWebElements(WebDriver driver, ElementLocator elementlocatorObject);
}
