package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import actions.*;

import actions.impl.*;

import webelement.IObjectRepositoryReader;
import webelement.ObjectRepositoryReader;

public class PageBase {
	protected WebDriver driver;
	Logger log = LogManager.getLogger(PageBase.class);
	
	protected IClickActions clickActions = new ClickActions();
	protected IWaitActions waitActions = new WaitActions();
	protected IElementActions actionsElement = new ElementActions();
	protected ITextActions textActions = new TextActions();
	protected IWaitJS waitLoad = new WaitJS();
	protected IAuthenticatingActions authActions = new AuthenticatingActions();
	protected ISelectActions selectActions = new SelectActions();
	protected IWindowActions windowActions= new WindowActions();
	protected IHelpers helpers=new Helpers();
	protected IKeyboardActions keyboardActions =new KeyboardActions();
	protected IFilesActions filesActions=new FilesActions();
	
	
	protected IObjectRepositoryReader objectRepositoryReader = new ObjectRepositoryReader();
	// create constructor take webdriver as param
	public PageBase(WebDriver driver) {
		// this mean the current class what you will use to read page
		PageFactory.initElements(driver, this);
	}

}
