package actions.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import actions.IKeyboardActions;

/**
 * KeyBoardActions class in Actions Package contain methods using keyboard keys
 * to press Enter , arrow up , arrow down , arrow left , arrow right , page up ,
 * page down , Escape, Space , Enter , Ctrl , Delete , back space, alt , shift ,
 * home each key in a method
 * 
 * @author Toka.Ashraf
 *
 */

public class KeyboardActions implements IKeyboardActions{
	Logger log = LogManager.getLogger(KeyboardActions.class);
	/**
	 * Presses the Arrow Up key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void arrowUP(WebElement element)

	{
		log.info("click on Arrow up key");
		element.sendKeys(Keys.ARROW_UP);

	}
	/**
	 * Presses the Arrow Down key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void arrowDown(WebElement element)

	{
		log.info("click on arrow down");
		element.sendKeys(Keys.ARROW_DOWN);

	}
	/**
	 * Presses the Arrow Right key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */

	public void arrowRight(WebElement element)

	{
		log.info("click on Arrow right");
		element.sendKeys(Keys.ARROW_RIGHT);

	}
	/**
	 * Presses the Arrow Left key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void arrowLeft(WebElement element)

	{
		log.info("click on arrow left");
		element.sendKeys(Keys.ARROW_LEFT);

	}
	/**
	 * Presses the Page Up key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void pageUpKey(WebElement element)

	{
		log.info("click on page up");
		element.sendKeys(Keys.PAGE_UP);

	}
	/**
	 * Presses the Page Down key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void pageDownKey(WebElement element)

	{
		log.info("click on page down");
		element.sendKeys(Keys.PAGE_DOWN);

	}
	/**
	 * Presses the Escape key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void escKey(WebElement element)

	{
		log.info("click on Escape ");
		element.sendKeys(Keys.ESCAPE);

	}

/**
 * Presses the Enter key on the keyboard.
 * @param element the WebElement to perform the action on.
 */

	public void enterKey(WebElement element)

	{
		log.info("click on Enter key");
		element.sendKeys(Keys.ENTER);

	}
	/**
	 * Presses the Cancel key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void cancelKey(WebElement element)

	{
		log.info("click on cancel key");
		element.sendKeys(Keys.CANCEL);

	}
	/**
	 * Presses the Backspace key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void backSpaceKey(WebElement element)

	{
		log.info("click on back space key");
		element.sendKeys(Keys.BACK_SPACE);

	}
	/**
	 * Presses the Delete key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */

	public void deleteKey(WebElement element)

	{
		log.info("click on delete key");
		element.sendKeys(Keys.DELETE);

	}
	/**
	 * Presses the Control key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void ctrlKey(WebElement element)

	{
		log.info("click on control key");
		element.sendKeys(Keys.CONTROL);

	}
	/**
	 * Presses the Space key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void spaceKey(WebElement element)

	{
		log.info("click on space key");
		element.sendKeys(Keys.SPACE);

	}
	/**
	 * Presses the Alt key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */
	public void altKey(WebElement element)

	{
		log.info("click on Alt key");
		element.sendKeys(Keys.ALT);

	}
	/**
	 * Presses the Shift key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */

	public void shiftKey(WebElement element)

	{
		log.info("click on shift key");
		element.sendKeys(Keys.SHIFT);

	}
	/**
	 * Presses the Home key on the keyboard.
	 * @param element the WebElement to perform the action on.
	 */

	public void homeKey(WebElement element)

	{
		log.info("click on Home key");
		element.sendKeys(Keys.HOME);

	}

}
