package library;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.LogStatus;

import objects.ProductPojo;
import testData.TestData;
import testcases.BaseClass;

public class WebDriverOperations extends BaseClass {

	FileOperations fo = new FileOperations();
	@SuppressWarnings("deprecation")
	public void instantiateBrowser(String browser, String headless) {
		if(browser.equalsIgnoreCase("chrome")) {
			Log.info("ChromeDriverPath:"+chromeDriverPath);
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			Log.info("Chromdriverpath is set");
			Log.info(headless);
			if(headless.equalsIgnoreCase("true")) {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless", "--start-maximized", "--ignore-certificate-errors");
				driver = new ChromeDriver(chromeOptions);	
				
				Log.info("Headless Chromedriver is instantiated");
			}else {
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--start-maximized", "--ignore-certificate-errors");
				driver = new ChromeDriver(chromeOptions);
				
				Log.info("Not headless Chromedriver is instantiated");
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}		

	}
	
	
	public void navigateToUrl(String url) {
			driver.get(url);
			Log.info("Navigated to url:"+url);	
	}
	
	
	public void enterEdit(WebElement element, String data) {
		try {
			element.sendKeys(data);
			Log.info("Entered data:"+data+" in element:"+element);
			successLog("Entered data:"+data+" in element:"+element);		
		} catch (Exception e) {
			Log.error(e);
			failureLog("Unable to enter data:"+data+" in element:"+element);

		}

	}
	
	public void click(WebElement element) {
		try {
			element.click();
			Log.info("Clicked on element:"+element);
			successLog("Clicked on element:"+element);
		} catch (Exception e) {
			Log.error(e);
			failureLog("Unable to click on element:"+element);	
		}

	}
	
	public Boolean verifyElementIsDisplayed(WebElement element) {
		Boolean dispalyed = element.isDisplayed();
		
		if(dispalyed) {
			Log.info("Element is displayed: Element:"+element);	
			test.log(LogStatus.PASS, "Element is displayed: Element:"+element);
		}
		else {
			Log.error("Element is not displayed: Element:"+element);	
			test.log(LogStatus.FAIL, "Element is not displayed Element:"+element);			
		}

		return dispalyed;
	}
	
	public Boolean verifyElementTextIsMatching(WebElement element, String expectedString) {
		String actualText = element.getText();
		Boolean elementTextIsMatching = actualText.equals(expectedString);
		if(elementTextIsMatching) {
			Log.info("Element text is matching:" +expectedString);		
			test.log(LogStatus.PASS, "Element text is matching:" +expectedString);
		} else
		{
			takeScreenshot();
			Log.error("Element text is not matching - Expected:"+expectedString+" Actual:"+actualText);	
			test.log(LogStatus.FAIL, "Element text is not matching - Expected:"+expectedString+" Actual:"+actualText);	
		}
	
		return elementTextIsMatching;
	}	

	public void sleep(int number)
	{
		try {
			Thread.sleep(number*1000);
			Log.info("Waited for "+number+" of seconds");
			successLog("Waited for "+number+" of seconds");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ProductPojo> returnAllListedProducts ()
	{
		ArrayList<ProductPojo> productsDisplayed = new ArrayList<ProductPojo>();

		try {
			
		List<WebElement> number_of_products = driver.findElements(By.xpath("//div[@class='inventory_list']//div[@class='inventory_item_label']/a"));
		List<WebElement> number_of_products_price = driver.findElements(By.xpath("//div[@class='inventory_list']//div[@class='inventory_item_price']"));
		for(int i=0; i< number_of_products.size(); i++) {
			String itemName = number_of_products.get(i).getText();
			String price = number_of_products_price.get(i).getText().substring(1);
			Log.info("Itemname:"+itemName+" price:"+price);
			ProductPojo listedProduct = new ProductPojo(itemName, Double.valueOf(price));
			productsDisplayed.add(listedProduct);
		}
		}
		catch(Exception exception) {
			Log.error(exception.toString());
		}
		
		
		return productsDisplayed;
		
	}
	
	
	public void selectDropDownItem(WebElement select, String valueToSelect) {
		try {
			Select dropDown = new Select(select);
			dropDown.selectByVisibleText(valueToSelect);
			Log.info("Selected: "+valueToSelect+" from dropdown");	
			test.log(LogStatus.PASS, "Selected: "+valueToSelect+" from dropdown");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to select: "+valueToSelect+" from dropdown");
		}

	}
	
	
	public void addProductToCart(int number) {
		try {
			List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[contains(text(), 'Add to cart')]"));
			addToCartButtons.get(number).click();
			Log.info("Added product to the cart");	
			test.log(LogStatus.PASS, "Added product to the cart");
		} catch (Exception e) {
			Log.error("Not able to add product to the cart");	
			test.log(LogStatus.FAIL, "Not able to add product to the cart");
		}

	}
	
	public int returnNumberOfItemsInCart() {
		return driver.findElements(By.xpath("//div[@class='cart_list']//div[@class='cart_item']")).size();
	}

	public WebDriverWait explicitWait() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.valueOf(new TestData().explicitWaitTimeout)));
		return wait;
	}
	
	
	public void waitForElementTobeDisplayed(WebElement element) {
		explicitWait().until(ExpectedConditions.visibilityOf(element));
	}	
	
	public void quitBrowser()
	{
		try {
			if(!(driver==null)) {
				driver.quit();
				Log.info("Driver is quit");				
			}

		} catch (Exception e) {
			Log.error(e);
		}
	}
	
	
	public void takeScreenshot() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = fo.createSceenshotFile();

		try {
			Files.copy(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void successLog(String message) {
		try {
			test.log(LogStatus.PASS, message);					
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void failureLog(String message) {
		try {
			test.log(LogStatus.FAIL, message);				
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
}
