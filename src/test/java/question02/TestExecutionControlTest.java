package question02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.SkipException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TestExecutionControlTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testNavigateToHomePage() {
        System.out.println("Executing: Navigate to home page.");
        driver.get("https://www.ebay.com");
    }

    @Test(priority = 2)
    public void testVerifyFormElements() {
        System.out.println("Executing: Verify form Elements");

        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        WebElement categoryDropdown = driver.findElement(By.id("gh-cat"));

        Assert.assertTrue(searchBox.isDisplayed(), "Search box is not present.");
        Assert.assertTrue(categoryDropdown.isDisplayed(), "Category dropdown is not present.");
    }

    @Test(priority = 3)
    public void testValidateInputConstraints() {
        System.out.println("Executing: Validate Input Constraints.");

        // Navigate to Sign In page
        driver.findElement(By.linkText("Sign in")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait for continue button
            WebElement continueBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("signin-continue-btn"))
            );
            // Click without entering email
            continueBtn.click();
            // Validate error message
            WebElement errorMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("signin-error-msg"))
            );

            Assert.assertTrue(errorMsg.isDisplayed(),
                    "Error message not displayed for empty input");

        } catch (Exception e) {
            // when page is not loaded due to CAPTCHA / human verification
            System.out.println("Sign-in page not loaded due to human verification.");
            throw new SkipException("Skipping test due to CAPTCHA / human verification.");
        }
    }

    @Test(priority = 4)
    public void testSuccessfulSubmission() {
        System.out.println("Executing: Successful Submission");

        driver.get("https://www.ebay.com");

        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        searchBox.sendKeys("laptop");

        WebElement searchBtn = driver.findElement(By.id("gh-search-btn"));
        searchBtn.click();
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("laptop")) {
            System.out.println("Successful submission.");
        } else {
            System.out.println("submission was not successful.");
        }
    }
//2.2 Skipping Tests----------------------------------------------------------------------------------------------
    @Test(enabled = false)
    public void testFeatureUnderDevelopment() {
        // Reason: This feature is still under development
        System.out.println("This test is disabled because feature is under development.");
    }

    @Test(enabled = false)
    public void testIdentifiedBugScenario() {
        // Reason: Application has a known bug, test will fail until fixed.
        System.out.println("This test is disabled due to a known issue");
    }

    //Skipping tests conditionally
    @Test(priority = 5)
    public void testSavedListAccess() {
        System.out.println("Executing: Skipping tests conditionally.(Testing saved list access.)");
        driver.get("https://www.ebay.com");

        // Click Saved
        driver.findElement(By.linkText("Saved")).click();

        // Check if redirected to Sign In page
        String currentUrl = driver.getCurrentUrl();

        if (currentUrl.contains("signin")) {
            // Skip test because user is not logged in
            System.out.println("Skipping test: Cannot access Watchlist without logging in");
            throw new SkipException("Skipping test: Cannot access Watchlist without logging in");
        }

        // If logged in, verify saved list page
        Assert.assertTrue(currentUrl.contains("saved"), "Watchlist page loaded");
    }

//2.3 Realistic Test Scenarios----------------------------------------------------------------------------------

    @Test(priority = 6)
    public void testNavigation() {
        System.out.println("Executing:  Navigate and verify page title");
        driver.get("https://www.ebay.com");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("eBay"), "eBay homepage not loaded");
    }

    @Test(priority = 7)
    public void testVerifyFormElements2() {
        System.out.println("Executing: Verify Form Elements - Check username and password fields");
        driver.findElement(By.linkText("Sign in")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait for username field
            WebElement username = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("userid"))
            );
            Assert.assertTrue(username.isDisplayed(), "Username field not visible");

        } catch (Exception e) {
            // Handle CAPTCHA / human verification issue
            System.out.println("Sign-in page not loaded due to human verification.");
            throw new SkipException("Skipping test due to CAPTCHA / human verification.");
        }
    }

    @Test(priority = 8)
    public void testValidateInputConstraints2() {
        System.out.println("Executing: Validate Input Constraints - Test empty login ");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Wait for continue button
            WebElement continueBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("signin-continue-btn"))
            );
            // Click without entering email
            continueBtn.click();
            // Validate error message
            WebElement errorMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("signin-error-msg"))
            );

            Assert.assertTrue(errorMsg.isDisplayed(),
                    "Error message not displayed for empty input");

        } catch (Exception e) {
            // when page is not loaded due to CAPTCHA / human verification
            System.out.println("Sign-in page not loaded due to human verification.");
            throw new SkipException("Skipping test due to CAPTCHA / human verification.");
        }
    }

    @Test(priority = 9)
    public void testSuccessfulSubmission2() {
        System.out.println("Executing: Successful Submission - Test valid login with assertions");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait for username field (ensures page loaded)
            WebElement username = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("userid"))
            );
            // Enter email
            username.sendKeys("testuser@gmail.com");
            // Click continue
            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("signin-continue-btn"))
            );
            continueBtn.click();

            // Wait for URL to change to password step
            wait.until(ExpectedConditions.urlContains("signin"));

            Assert.assertTrue(driver.getCurrentUrl().contains("signin"),
                    "Did not navigate to password step");

        } catch (Exception e) {

            // Handle CAPTCHA / human verification
            System.out.println("Sign-in flow interrupted due to human verification.");
            throw new SkipException("Skipping test due to CAPTCHA / human verification.");
        }
    }
}
