package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import library.FileOperations;
import library.WebDriverOperations;
import objects.ProductPojo;
import testData.ProductsData;
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
		ProductsData pd = new ProductsData();
		productsExpected = pd.initializeProduct();
		browser = properties.getProperty("Browser");
		Log.info("Browser name:"+browser);
		url = properties.getProperty("URL");
		Log.info("URL:"+url);		
	}

	@AfterSuite
	public void cleanUp() {
		webDriverOperations.quitBrowser();
	}	

}
