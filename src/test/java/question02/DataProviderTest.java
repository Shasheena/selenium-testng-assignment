package question02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DataProviderTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openEbay() {
        driver.get("https://www.ebay.com");
    }

    //Data Provider with 5 datasets
    @DataProvider(name = "searchTerms")
    public Object[][] getSearchData() {
        return new Object[][]{
                {"laptop", " Computers/Tablets & Networking", true},
                {"shoes", " Clothing, Shoes & Accessories", true},
                {"phone", " Cell Phones & Accessories", true},
                {"book", "Books", true},
                {"", "All Categories", false}
        };
    }

    @Test(dataProvider = "searchTerms")
    public void testSearchFunctionality(String keyword, String category, boolean expectedResult) {

        System.out.println("Executing with: " + keyword + " | " + category);

        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        searchBox.clear();
        searchBox.sendKeys(keyword);

        WebElement categoryDropdown = driver.findElement(By.id("gh-cat"));
        categoryDropdown.sendKeys(category);

        driver.findElement(By.id("gh-search-btn")).click();

        String currentUrl = driver.getCurrentUrl();

        if (expectedResult) {
            Assert.assertTrue(currentUrl.contains(keyword),
                    "Search results not displayed for valid input");
        } else {
            Assert.assertTrue(currentUrl.contains("_nkw="),
                    "Empty search not handled properly");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
