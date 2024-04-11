package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import objects.HomePage;
import testData.DataProviderClass;
import testData.Messages;
/**
 * This class contains all negative scenarios
 * 
 */
public class NegativeScenarios extends BaseClass{
	HomePage hp = new HomePage();
	Messages mm = new Messages();
	@BeforeMethod (groups = {"sanity"})
	public void navigateToUrl() {
		webDriverOperations.instantiateBrowser(browser, headless);
		webDriverOperations.navigateToUrl(url);
	}
	
	@Test ( groups = {"regression", "negative"}, dataProvider = "invalidLoginOptions", dataProviderClass = DataProviderClass.class)
    public void myTest (String userName, String password) {
		webDriverOperations.enterEdit(hp.homePage_username_editBox(), userName);
		webDriverOperations.enterEdit(hp.homePage_password_editBox(), password);
		webDriverOperations.click(hp.homePage_login_button());
		webDriverOperations.waitForElementTobeDisplayed(hp.errorMessage());
		Assert.assertTrue(
		webDriverOperations.verifyElementTextIsMatching(hp.errorMessage(), mm.errorMessageForLockedOutUser)==true,
		"Error message for trying to login with Locked out user is not matching");
		
    }
	
	@AfterMethod (groups = {"regression", "negative"})
	public void cleanUp() {
		webDriverOperations.quitBrowser();
	}
	
	
}
