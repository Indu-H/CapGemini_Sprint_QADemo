
package ecom.BITM.QADemoLoginscript;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.BITM.QADemo.GenericUtility.BaseClass;
import ecom.BITM.QADemo.GenericUtility.DataProviderUtility;
import ecom.BITM.QADemo.POM.QADemoLoginpage;

public class QADemoLoginNegative extends BaseClass {

    @Test(
        groups = {"Negative"},
        dataProvider = "invaliddata",
        dataProviderClass = DataProviderUtility.class
    )
    public void verifyInvalidLogin(String username, String password) throws InterruptedException {

        Reporter.log("Starting Negative Login Test", true);

        QADemoLoginpage login = new QADemoLoginpage(driver);
        Reporter.log("Login page initialized", true);
        login.login(username, password);
        Reporter.log("Invalid credentials entered", true);
        Thread.sleep(3000);
        Assert.assertFalse(driver.getCurrentUrl().contains("/catalog"),"Negative login failed: Invalid credentials allowed login");
        Reporter.log("Negative login test passed: Invalid credentials did not open the catalog",true);
    }
}