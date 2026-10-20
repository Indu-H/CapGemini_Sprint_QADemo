package ecom.BITM.QADemo.GenericUtility;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Crossbrowsertesting {

    WebDriver driver = null;

    public WebDriver launchBrowser(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions settings = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.password_manager_leak_detection", false);
            prefs.put("autofill.credit_card_enabled", false);
            settings.setExperimentalOption("prefs", prefs);

            settings.addArguments("--start-maximized");
            settings.addArguments("--force-device-scale-factor=0.8");

            driver = new ChromeDriver(settings);
        }

     else if (browser.equalsIgnoreCase("edge")) {
        EdgeOptions settings = new EdgeOptions();
        driver = new EdgeDriver(settings);
    }
        else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
        }

        else {
            ChromeOptions settings = new ChromeOptions();
            settings.addArguments("--start-maximized");
            settings.addArguments("--force-device-scale-factor=0.8");

            driver = new ChromeDriver(settings);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }
}
