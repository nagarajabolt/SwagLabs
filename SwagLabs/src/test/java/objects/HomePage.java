package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testcases.BaseClass;

public class HomePage extends BaseClass {

	public WebElement homePage_username_editBox()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath("//input[@placeholder='Username']"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;		
	}
	
	public WebElement homePage_password_editBox()
	{
		
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath("//input[@placeholder='Password']")); 
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;	
	}
	
	public WebElement homePage_login_button()
	{
		WebElement element = null;
		try {
			element =  driver.findElement(By.id("login-button"));  
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;	
	}
	
	
	public WebElement errorMessage()
	{
		WebElement element = null;
		try {
			element =  driver.findElement(By.xpath("//h3[@data-test='error']"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;	
	}


}
