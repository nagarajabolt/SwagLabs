package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testcases.BaseClass;
/**
 * This page contains all elements of Checkout page
 * 
 */
public class CheckoutPage extends BaseClass {
	public WebElement checkOutButton()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.id("checkout"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}

	public WebElement firstName()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.id("first-name"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}
	
	public WebElement lastName()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.id("last-name"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}	

	public WebElement postalCode()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.id("postal-code"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}	
	
	public WebElement continueButton()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.id("continue"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}
	public WebElement finishButton()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.id("finish"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}	
	public WebElement back_to_home()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.id("back-to-products"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}		
	
}
