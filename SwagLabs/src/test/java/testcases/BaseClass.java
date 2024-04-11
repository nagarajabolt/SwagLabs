package testcases;

import org.testng.annotations.BeforeSuite;
import library.FileOperations;
import library.WebDriverOperations;
import objects.ProductPojo;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;



public class BaseClass {
	public static Logger Log = Logger.getLogger(BaseClass.class);

	public static WebDriver driver = null;
	public String workingDirectory = System.getProperty("user.dir");
	String configurationFileName = workingDirectory + "\\src\\test\\java\\testData\\Configuration.properties";
	protected String screenShotsFolderName = workingDirectory + "\\src\\test\\java\\Screenshots";
	protected String chromeDriverPath = workingDirectory + "\\src\\test\\java\\drivers\\chromedriver.exe";

	FileOperations fileOperations = null;
	public Properties properties = null;
	WebDriverOperations webDriverOperations = null;
	ArrayList<ProductPojo> productsExpected = null;
	String browser = "", url = "";
	
	@BeforeSuite
	public void initialization()
	{
		PropertyConfigurator.configure("Log4j.properties");
		Log.info("Configuration Filename"+configurationFileName);

		fileOperations = new FileOperations();
		
		properties = fileOperations.readPropertiesFile(configurationFileName);
		
		webDriverOperations = new WebDriverOperations();
		productsExpected = new ArrayList<ProductPojo>();


		browser = properties.getProperty("Browser");
		Log.info("Browser name:"+browser);
		url = properties.getProperty("URL");
		Log.info("URL:"+url);
		

		ProductPojo product = new ProductPojo("Sauce Labs Backpack", 29.99);
		productsExpected.add(product);		
		product = new ProductPojo("Sauce Labs Bike Light", 9.99);
		productsExpected.add(product);	
		product = new ProductPojo("Sauce Labs Bolt T-Shirt", 15.99);
		productsExpected.add(product);
		product = new ProductPojo("Sauce Labs Fleece Jacket", 49.99);
		productsExpected.add(product);		
		product = new ProductPojo("Sauce Labs Onesie", 7.99);
		productsExpected.add(product);				
		product = new ProductPojo("Test.allTheThings() T-Shirt (Red)", 15.99);
		productsExpected.add(product);
	}

	

}
