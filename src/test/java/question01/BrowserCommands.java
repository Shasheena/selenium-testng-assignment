package question01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class BrowserCommands {

    @Test
    public void browserTest() {

        WebDriver driver = null;

        try {

            System.out.println("=== Browser Commands Test ===");

            // 1. Browser Setup
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // 2. Navigation
            driver.get("https://www.ebay.com");
            String currentURL = driver.getCurrentUrl();

            // Assertion
            Assert.assertTrue(currentURL.contains("ebay.com"), "Navigation failed!");

            // 3. Page Information

            String title = driver.getTitle();
            int titleLength = title.length();

            String pageSource = driver.getPageSource();
            int pageSourceLength = pageSource.length();

            System.out.println("Page Title: " + title);
            System.out.println("Title Length: " + titleLength + " characters\n");

            System.out.println("Current URL: " + currentURL + "\n");

            System.out.println("Page Source Length: " + pageSourceLength + " characters");

        }

        finally {

            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed successfully.");
            }

        }

    }
}
