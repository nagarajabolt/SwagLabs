package testcases;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import objects.CheckoutPage;
import objects.HomePage;
import objects.ProductPojo;
import objects.ProductsPage;
import testData.TestData;

public class Scenarios extends BaseClass {
	HomePage hp = new HomePage();
	ProductsPage pp = new ProductsPage();
	CheckoutPage cp = new CheckoutPage();
	TestData testData = new TestData();

	
	@BeforeMethod(description = "Logs into the application", groups = {"sanity", "regression", "positive", "e2e"})
	public void LoginToApplication()
	{		
		Log.info("<---------> Login to the application <---------> ");
		webDriverOperations.instantiateBrowser(browser, headless);
		webDriverOperations.navigateToUrl(url);
		webDriverOperations.enterEdit(hp.homePage_username_editBox(), testData.loginScreen_UserName);
		webDriverOperations.enterEdit(hp.homePage_password_editBox(), testData.loginScreen_Password);
		webDriverOperations.click(hp.homePage_login_button());
		webDriverOperations.waitForElementTobeDisplayed(pp.swag_Labs_Logo());

		Log.info("<---------> Login to the application <---------> ");		
		webDriverOperations.sleep(5);
	}
	
	@AfterMethod(description = "Quits browser", groups = {"sanity", "regression", "positive", "e2e"})
	public void cleanUp() {
		webDriverOperations.quitBrowser();

	}
	
	
	@Test (priority = 0, enabled = true, description = "Checks if logo is displayed after login", groups = {"sanity", "regression", "positive", "e2e"})
	public void LoginFlow()
	{				
		Log.info("<---------> Testcase - LoginFlow <---------> ");
		test = extent.startTest("LoginFlow");
		Assert.assertTrue(webDriverOperations.verifyElementIsDisplayed(pp.swag_Labs_Logo()), "Swag Labs logo is not displayed");
		Assert.assertTrue((webDriverOperations.verifyElementTextIsMatching(pp.swag_Labs_Logo(), testData.home_logoText)), "Swag Labs text is not matching");
		Log.info("<---------> Testcase - LoginFlow <---------> ");

		extent.endTest(test);
		extent.flush();
	}
	
	
	@Test (priority = 1, enabled = true, dependsOnMethods = "LoginFlow", description = "Checks if products are listed correctly", groups = {"sanity", "regression", "positive"})
	public void productListingVerification()
	{
		Log.info("<---------> Testcase - productListingVerification <---------> ");
		test = extent.startTest("productListingVerification");
		ArrayList<ProductPojo> productsActual = webDriverOperations.returnAllListedProducts();
		Boolean productsMatching = fileOperations.verifyProductListsAreMatching(productsActual, productsExpected);		
		Assert.assertTrue(productsMatching, "Products are not matching with expected list");
		Log.info("Products are displayed correctly");
		Log.info("<---------> Testcase - productListingVerification <---------> ");
		test.log(LogStatus.PASS, "productListingVerification testcase is passed");
		extent.endTest(test);
		extent.flush();
	}
	
	@Test (priority = 2, enabled = true, dependsOnMethods = "LoginFlow", description = "Checks if products are sorted correctly on different options of product and price", groups = {"sanity, regression"})
	public void sortingCheck()
	{
		Log.info("<---------> Testcase - sortingCheck <---------> ");
		test = extent.startTest("sortingCheck");
		ArrayList<ProductPojo> productsExpectedsorted = productsExpected;
		fileOperations.sortByProductName(productsExpectedsorted);
		Log.info("Arraylist is sorted by ascending order of Product Name");
		fileOperations.displayListItems(productsExpectedsorted);
		webDriverOperations.selectDropDownItem(pp.sortingList(), testData.product_sort_name_aToz);
		ArrayList<ProductPojo> productsActual = webDriverOperations.returnAllListedProducts();
		Boolean productsMatching = fileOperations.verifyProductListsAreMatching(productsActual, productsExpectedsorted);		
		Assert.assertEquals(productsMatching, true);
		Log.info("Products are sorted correctly");
		
		fileOperations.reverseSortByProductName(productsExpectedsorted);
		Log.info("Arraylist is sorted by descending order of Product Name");		
		fileOperations.displayListItems(productsExpectedsorted);
		
		webDriverOperations.selectDropDownItem(pp.sortingList(), testData.product_sort_name_zToa);		
		productsActual = webDriverOperations.returnAllListedProducts();
		productsMatching = fileOperations.verifyProductListsAreMatching(productsActual, productsExpectedsorted);		
		Assert.assertEquals(productsMatching, true);
		Log.info("Products are sorted correctly");		
		
		fileOperations.sortByPrice(productsExpectedsorted);
		Log.info("Arraylist is sorted by ascending order of Price");
		fileOperations.displayListItems(productsExpectedsorted);
		
		webDriverOperations.selectDropDownItem(pp.sortingList(), testData.product_sort_price_lowToHigh);		
		productsActual = webDriverOperations.returnAllListedProducts();
		productsMatching = fileOperations.verifyProductPricesAreMatching(productsActual, productsExpectedsorted);		
		Assert.assertEquals(productsMatching, true);
		Log.info("Products are sorted correctly");	
		
		
		fileOperations.reversesortByPrice(productsExpectedsorted);
		Log.info("Arraylist is sorted by descending order of Price");
		fileOperations.displayListItems(productsExpectedsorted);
		
		webDriverOperations.selectDropDownItem(pp.sortingList(), testData.product_sort_price_highToLow);		
		productsActual = webDriverOperations.returnAllListedProducts();
		productsMatching = fileOperations.verifyProductPricesAreMatching(productsActual, productsExpectedsorted);		
		Assert.assertEquals(productsMatching, true);
		Log.info("Products are sorted correctly");	
		webDriverOperations.sleep(5);
		test.log(LogStatus.PASS, "sortingCheck testcase is passed");
		extent.endTest(test);
		extent.flush();
		Log.info("<---------> Testcase - sortingCheck <---------> ");
	}
	
	@Test (priority = 3, enabled = true, dependsOnMethods = "LoginFlow", description = "Checks if products are added to cart", groups = {"sanity", "regression"})
	public void addingToCart()
	{
		Log.info("<---------> Testcase - addingToCart <---------> ");
		test = extent.startTest("addingToCart");
		webDriverOperations.addProductToCart(0);
		webDriverOperations.click(pp.cartButton());
		Assert.assertEquals(webDriverOperations.returnNumberOfItemsInCart()==1, true);
		Log.info("Number of items in cart is matching");
		webDriverOperations.click(pp.removeButton());
		Log.info("<---------> Testcase - addingToCart <---------> ");
		extent.endTest(test);
		extent.flush();
//		webDriverOperations.sleep(5);
	}

	@Test (priority = 4, enabled = true, dependsOnMethods = "LoginFlow", description = "Checks if user is able to checkout products", groups = {"sanity", "regression"})
	public void checkOut()
	{
		Log.info("<---------> Testcase - checkOut <---------> ");	
		test = extent.startTest("checkOut");
		webDriverOperations.addProductToCart(0);
		webDriverOperations.click(pp.cartButton());		
		Assert.assertTrue(webDriverOperations.returnNumberOfItemsInCart()==1, "Number of products in cart is not matching with expected value");		
		webDriverOperations.click(cp.checkOutButton());
		webDriverOperations.enterEdit(cp.firstName(), testData.checkout_FirstName);
		webDriverOperations.enterEdit(cp.lastName(), testData.checkout_LastName);	
		webDriverOperations.enterEdit(cp.postalCode(), testData.checkout_PostalCode);		
		webDriverOperations.click(cp.continueButton());
		webDriverOperations.click(cp.finishButton());
		Assert.assertTrue(webDriverOperations.verifyElementIsDisplayed(pp.thankYouForOrderMessage()), "Check out complete message is not dispalyed");		
		webDriverOperations.click(cp.back_to_home());		
		Log.info("User is able to checkout product");
		Log.info("<---------> Testcase - checkOut <---------> ");	
		extent.endTest(test);
		extent.flush();
	}	
	
	@Test (priority = 5, enabled = true, dependsOnMethods = "LoginFlow", description = "Checks if user is able to checkout first 3 least priced products", groups = {"sanity", "regression", "e2e"})
	public void additional_Case()
	{
		Log.info("<---------> Testcase - additional_Case <---------> ");	
		test = extent.startTest("additional_Case");
		webDriverOperations.selectDropDownItem(pp.sortingList(), testData.product_sort_price_lowToHigh);
		webDriverOperations.addProductToCart(0);
		webDriverOperations.addProductToCart(1);
		webDriverOperations.addProductToCart(2);
		webDriverOperations.click(pp.cartButton());	
		Assert.assertTrue(webDriverOperations.returnNumberOfItemsInCart()==3, "Number of products in cart is not matching with expected value");			
		webDriverOperations.click(cp.checkOutButton());
		webDriverOperations.enterEdit(cp.firstName(), testData.checkout_FirstName);
		webDriverOperations.enterEdit(cp.lastName(), testData.checkout_LastName);	
		webDriverOperations.enterEdit(cp.postalCode(), testData.checkout_PostalCode);		
		webDriverOperations.click(cp.continueButton());
		webDriverOperations.click(cp.finishButton());	
		Assert.assertTrue(webDriverOperations.verifyElementIsDisplayed(pp.thankYouForOrderMessage()), "Check out complete message is not dispalyed");
		Log.info("User is able to checkout 3 products");
		Log.info("<---------> Testcase - additional_Case <---------> ");	
		test.log(LogStatus.PASS, "additional_Case testcase is passed");
		extent.endTest(test);
		extent.flush();
	}
	
}
