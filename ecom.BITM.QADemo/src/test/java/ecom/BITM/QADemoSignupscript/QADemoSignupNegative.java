package ecom.BITM.QADemoSignupscript;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.BITM.QADemo.GenericUtility.BaseClass;
import ecom.BITM.QADemo.GenericUtility.DataProviderUtility;
import ecom.BITM.QADemo.GenericUtility.JavaUtility;
import ecom.BITM.QADemo.POM.QADemoSignuppage;



public class QADemoSignupNegative extends BaseClass {
	@Test(groups = {"Negative"}, dataProvider ="data",dataProviderClass = DataProviderUtility.class
	    )
	    public void verifySignUp(String email,String phoneno,
	            String password,
	            String confirmPassword) throws InterruptedException {

	        QADemoSignuppage signup = new QADemoSignuppage(driver);
	        JavaUtility ju=new JavaUtility();

	        

	        signup.registerUser(email,phoneno,password, confirmPassword);
	        Thread.sleep(4000);
	        Assert.assertEquals(signup.getErrorEmail().isDisplayed(),true);
	        Assert.assertEquals(signup.getErrorPassword().isDisplayed(),true);

	        Reporter.log("Negative test passes: Signup failed with invalid credentials ", true);
	         
	    }
	

}