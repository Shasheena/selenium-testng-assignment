package question02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DependencyTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ebay.com");
    }

    //1. Base Test - Navigation
    @Test
    public void testOpenHomePage() {
        System.out.println("Executing: testOpenHomePage");

        String title = driver.getTitle();
        Assert.assertTrue(title.contains("eBay"), "Homepage not loaded");
    }

    //2. Single dependency
    @Test(dependsOnMethods = "testOpenHomePage")
    public void testNavigateToSignIn() {
        System.out.println("Executing: testNavigateToSignIn");

        driver.findElement(By.linkText("Sign in")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("signin"),
                "Did not navigate to Sign In page");
    }

    @Test(dependsOnMethods = "testNavigateToSignIn")
    public void testLogin() {
        System.out.println("Executing: testLogin");

        // Intentionally failing
        Assert.fail("Forcing login failure to demonstrate dependency behavior");
    }

    // 4. Multiple dependencies
    @Test(dependsOnMethods = {"testNavigateToSignIn", "testLogin"})
    public void testAccessElectronics() {
        System.out.println("Executing: testAccessElectronics");

        driver.findElement(By.linkText("Electronics")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("Electronics"),
                "Electronics not accessible");
    }

    // 5. Another dependent test
    @Test(dependsOnMethods = "testAccessElectronics")
    public void testAddItemToWatchlist() {
        System.out.println("Executing: testAddItemToWatchlist");

        Assert.assertTrue(true, "Dummy test");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        System.out.println("@AfterClass: Closing browser");
        driver.quit();
    }

    // If a dependency test fails, all dependent tests are skipped.
    // This prevents execution of tests that rely on failed functionality.

}
