package question01;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class FormInteraction {
    @Test
    public void ebayAdvancedSearchTest() throws InterruptedException {

        WebDriver driver = null;

        try {

            System.out.println("=== eBay Advanced Search Test ===");

            // 1 Browser Setup
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // 1 Advanced Search Navigation
            driver.get("https://www.ebay.com/sch/ebayadvsearch");

            String title = driver.getTitle();
            Assert.assertTrue(title.contains("Advanced"), "Advanced search page not loaded");

            System.out.println("Advanced Search Page Loaded Successfully");

            // 2 Keyword Search Input
            WebElement keywordBox = driver.findElement(By.id("_nkw"));
            keywordBox.sendKeys("laptop");
            String enteredText = keywordBox.getAttribute("value");
            Assert.assertEquals(enteredText, "laptop");

            System.out.println("Keyword entered correctly: " + enteredText);


            //3 Category Selection
            WebElement categoryDropdown = driver.findElement(By.id("s0-1-20-4[0]-7[3]-_sacat"));
            Select categorySelect = new Select(categoryDropdown);
            categorySelect.selectByVisibleText("Computers/Tablets & Networking");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            categorySelect.selectByValue("58058");
            System.out.println("Category selected using selectByVisibleText and selectByValue");


            // 4 Price Range
            WebElement minPrice = driver.findElement(By.name("_udlo"));
            WebElement maxPrice = driver.findElement(By.name("_udhi"));

            minPrice.sendKeys("100");
            maxPrice.sendKeys("500");

            Assert.assertEquals(minPrice.getAttribute("value"), "100");
            Assert.assertEquals(maxPrice.getAttribute("value"), "500");

            System.out.println("Price range set: 100 - 500");


            // 5 Checkbox Interaction
            WebElement titleDescCheckbox = driver.findElement(By.name("LH_TitleDesc"));
            if (!titleDescCheckbox.isSelected()) {
                titleDescCheckbox.click();
            }

            // Verify it's selected
            if (titleDescCheckbox.isSelected()) {
                System.out.println("Title & Description checkbox is selected");
            } else {
                System.out.println("Title & Description checkbox selection failed");
            }

            // Locate the checkbox
            WebElement completeCheckbox = driver.findElement(By.name("LH_Complete"));

            // Select it if not already selected
            if (!completeCheckbox.isSelected()) {
                completeCheckbox.click();
            }

            // Verify it's selected
            if (completeCheckbox.isSelected()) {
                System.out.println("Complete checkbox is selected");
            } else {
                System.out.println("Complete checkbox selection failed");
            }


            // 6 Search Execution
            WebElement searchButton = driver.findElement(By.xpath("/html/body/div[2]/div/main/form/div[2]/button"));
            searchButton.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Locate container and items
            WebElement resultsContainer = driver.findElement(By.id("srp-river-results"));
            List<WebElement> results = resultsContainer.findElements(By.cssSelector("li.s-card"));

            // Print number of results
            System.out.println("Number of Results Found: " + results.size());

            // Assert at least one result exists
            Assert.assertTrue(results.size() > 0, "No results found");

        }

        finally {

            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed successfully.");
            }

        }

    }
}
