package ecom.BITM.QADemo.GenericUtility;

import java.util.Random;

public class JavaUtility {
	
	public String generateRandomNumber(String key) {
		Random r = new Random();
		int number = 100 + r.nextInt(900);
		 return key + number + "@gmail.com";	
	}
	public String generateNumber(String phoneno) {
		Random r = new Random();
		int number = 10000 + r.nextInt(90000);
		 return phoneno + number ;	
	}
}