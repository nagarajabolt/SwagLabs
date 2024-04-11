package testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import library.FileOperations;
import library.ProductUtilities;
import library.WebDriverOperations;
import objects.ProductPojo;
import testData.ProductsData;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
/**
 * Initialization of Property files, all static variables and object instances are done here
 * 
 */


public class BaseClass {

	
	
	public static Logger Log = Logger.getLogger(BaseClass.class);
	protected  ExtentReports extent;
	protected  static ExtentTest test;


	public static WebDriver driver = null;
	public String workingDirectory = System.getProperty("user.dir");
	String configurationFileName = workingDirectory + "\\src\\test\\java\\testData\\Configuration.properties";
	protected String screenShotsFolderName = workingDirectory + "\\src\\test\\java\\Screenshots";
	protected String chromeDriverPath = workingDirectory + "\\src\\test\\java\\drivers\\chromedriver.exe";

	FileOperations fileOperations = null;
	ProductUtilities productUtilities = null;

	public Properties properties = null;
	WebDriverOperations webDriverOperations = null;
	ArrayList<ProductPojo> productsExpected = null;
	String browser = "", url = "";

	protected String headless = "false";
	
	@BeforeSuite (groups = {"sanity", "regression", "positive", "e2e", "negative"})
	public void initialization()
	{
		PropertyConfigurator.configure("Log4j.properties");
		Log.info("Configuration Filename"+configurationFileName);

		extent = new ExtentReports(workingDirectory+"/test-output/ExtentReportResults.html", true);
		extent.loadConfig(new File( workingDirectory+"/test-output/extent-report.xml"));
		fileOperations = new FileOperations();
		productUtilities = new ProductUtilities();
		properties = fileOperations.readPropertiesFile(configurationFileName);
		webDriverOperations = new WebDriverOperations();
		ProductsData pd = new ProductsData();
		productsExpected = pd.initializeProduct();
		browser = properties.getProperty("Browser");
		Log.info("Browser name:"+browser);
		url = properties.getProperty("URL");
		Log.info("URL:"+url);	
		headless = properties.getProperty("Headless");
		Log.info("Headless:"+headless);	
	}

	@AfterSuite(groups = {"sanity", "regression", "positive", "e2e", "negative"})
	public void cleanUp() {
		webDriverOperations.quitBrowser();
		extent.flush();
	}	

}
