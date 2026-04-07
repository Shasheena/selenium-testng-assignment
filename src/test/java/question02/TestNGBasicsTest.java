package question02;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TestNGBasicsTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("@BeforeClass: Setting up test environment");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeEachTest() {
        System.out.println("@BeforeMethod: Preparing for test");
        driver.get("https://www.ebay.com");
    }

    @Test
    public void testValidLogin() {
        System.out.println("Executing: testValidLogin");
    }

    @Test
    public void testSearchItem() {
        System.out.println("Executing: testSearchItem");
    }

    @Test
    public void testAddToCart() {
        System.out.println("Executing: testAddToCart");
    }

    @AfterMethod
    public void afterEachTest() {
        driver.navigate().refresh();
        System.out.println("@AfterMethod: Reset after test");
        System.out.println();
    }

    @AfterClass
    public void tearDown() {
        System.out.println("@AfterClass: Closing browser");
        driver.quit();
    }

    //1.2 Login Functionality Testing----------------------------------------------------------------------------------

    // 1. Test Navigation - Navigate to eBay and verify page title
    @Test
    public void testNavigation() {
        System.out.println("Executing: Navigation to eBay.");
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);
        Assert.assertTrue(title.contains("eBay"), "Title does not contain eBay");
    }

    // 2. Test Search Functionality - Verify search box is displayed
    @Test
    public void testSearchBox() {
        System.out.println("Executing: Verification of search box.");
        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        Assert.assertTrue(searchBox.isDisplayed(), "Search box is not displayed");
    }

    // 3. Test Page Elements - Verify category menu and navigation links
    @Test
    public void testPageElements() {
        System.out.println("Executing: Testing page elements.");

        // Category dropdown
        WebElement categoryDropdown = driver.findElement(By.id("gh-cat"));
        Assert.assertTrue(categoryDropdown.isDisplayed(), "Category dropdown is not present.");

        // Navigation links
        WebElement electronicsLink = driver.findElement(By.linkText("Electronics"));
        Assert.assertTrue(electronicsLink.isDisplayed(), "Electronics link not present.");

        WebElement collectiblesLink = driver.findElement(By.linkText("Collectibles and Art"));
        Assert.assertTrue(collectiblesLink.isDisplayed(), "Collectibles and Art link is not present.");

        WebElement dealsLink = driver.findElement(By.linkText("Deals"));
        Assert.assertTrue(dealsLink.isDisplayed(), "Deals link is not present.");
    }
}
