package question02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssertionsTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openEbay() {
        driver.get("https://www.ebay.com");
    }

    // 1.assertEquals()
    @Test
    public void testPageTitle() {
        System.out.println("Executing: Using assertEquals() - testPageTitle");

        String actualTitle = driver.getTitle();
        String expectedTitle = "Electronics, Cars, Fashion, Collectibles & More | eBay";

        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match");
    }

    @Test
    public void testURL() {
        System.out.println("Executing: Using assertEquals() - testURL");

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.ebay.com/";

        Assert.assertEquals(actualUrl, expectedUrl, "URL does not match");
    }

    @Test
    public void testSearchBoxAttribute() {
        System.out.println("Executing: Using assertEquals() - testSearchBoxAttribute");

        WebElement searchBox = driver.findElement(By.id("gh-ac"));

        String actualPlaceholder = searchBox.getAttribute("placeholder");
        String expectedPlaceholder = "Search for anything";

        Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "Placeholder text mismatch");
    }

    // 2. assertTrue()
    @Test
    public void testSearchBoxDisplayed() {
        System.out.println("Executing: Using assertTrue() - testSearchBoxDisplayed");
        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        Assert.assertTrue(searchBox.isDisplayed(), "Search box is not visible");
    }

    // assertFalse()
    @Test
    public void testIncorrectElement() {
        System.out.println("Executing: Using assertFalse() - testIncorrectElement");
        boolean isLoginVisible = driver.getCurrentUrl().contains("signin");
        Assert.assertFalse(isLoginVisible, "Unexpectedly on login page");
    }

    // 3. Demonstrate execution stops at first failure
    @Test
    public void testStopOnFailure() {
        System.out.println("Executing: testStopOnFailure");

        String title = driver.getTitle();
        Assert.assertEquals(title, "Wrong Title", "Forcing failure");
        System.out.println("This line will NOT be printed");
        Assert.assertTrue(false, "This will not run");
    }

//Soft Assertions with multiple checks------------------------------------------------------------------------------
    @Test
    public void testSoftAssertions() {
        System.out.println("Executing: testSoftAssertions");

        SoftAssert softAssert = new SoftAssert();

        //faliure
        String title = driver.getTitle();
        softAssert.assertEquals(title, "Wrong Title", "Title mismatch");

        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        softAssert.assertTrue(searchBox.isDisplayed(), "Search box not visible");

        //faliure
        softAssert.assertFalse(title.contains("eBay"), "Title should not contain eBay");

        System.out.println("All soft assertions executed");

        softAssert.assertAll();
    }

    @Test
    public void testSoftAssertionsElements() {
        System.out.println("Executing: testSoftAssertionsElements");

        SoftAssert softAssert = new SoftAssert();

        // Check search box
        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        softAssert.assertTrue(searchBox.isDisplayed(), "Search box missing");

        // Check category dropdown
        WebElement category = driver.findElement(By.id("gh-cat"));
        softAssert.assertTrue(category.isDisplayed(), "Category dropdown missing");

        // Intentional failure
        softAssert.assertEquals(category.getTagName(), "input", "Wrong tag type");

        System.out.println("Multiple element checks completed");

        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
