package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testcases.BaseClass;
/**
 * This class contains all elements of Products page
 * 
 */
public class ProductsPage extends BaseClass {
	

	public WebElement swag_Labs_Logo()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath("//div[contains(text(), 'Swag Labs')]"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}
	
	public WebElement inventoryList()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath("inventory_list"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}
	
	public WebElement sortingList()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
			Log.info("element sortinglist is returned");
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;
	}

	
	public WebElement cartButton()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath("//a[@class = 'shopping_cart_link']"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;		
		
	}
	
	public WebElement thankYouForOrderMessage()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your order!')]"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;		
		
	}
	
	public WebElement removeButton()
	{
		WebElement element = null;
		try {
			element = driver.findElement(By.id("remove-sauce-labs-backpack"));
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		 
		return element;		
		
		
	}
		
}
