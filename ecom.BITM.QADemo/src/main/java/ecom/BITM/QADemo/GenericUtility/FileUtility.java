package ecom.BITM.QADemo.GenericUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	public String getDataFromPropertyFile(String key) throws Exception{
		FileInputStream fis = new FileInputStream("./src/test/resources/QADemo/QADemo.properties");
		Properties p = new Properties();
		p.load(fis);

		return p.getProperty(key);
	}
}