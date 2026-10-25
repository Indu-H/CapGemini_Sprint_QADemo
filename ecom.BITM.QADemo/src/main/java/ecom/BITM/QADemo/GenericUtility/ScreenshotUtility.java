package ecom.BITM.QADemo.GenericUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ScreenshotUtility {

    public static String captureScreenshot(WebDriver driver, String testName) {

        try {
            Path folder = Paths.get("screenshots");
            Files.createDirectories(folder);

            String time = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

            String fileName = testName + "_" + time + ".png";

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path destination = folder.resolve(fileName);

            Files.copy(source.toPath(), destination);

            String savedPath = destination.toAbsolutePath().toString();

            Reporter.log("Screenshot saved: " + savedPath, true);

            return savedPath;

        } catch (IOException | RuntimeException e) {
            Reporter.log(
                    "Screenshot could not be saved: " + e.getMessage(), true);
            return "";
        }
    }
}