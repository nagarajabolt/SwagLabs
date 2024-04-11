package testData;

import testcases.BaseClass;
import org.testng.annotations.DataProvider;

public class DataProviderClass extends BaseClass{
	TestData td = new TestData();
	@DataProvider (name = "invalidLoginOptions")
    public Object[][] dpMethod(){
	 return new Object[][] {{td.loginScreen_lockedOut_UserName, td.loginScreen_Password}};
    }
}
