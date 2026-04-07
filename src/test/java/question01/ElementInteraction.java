package question01;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.openqa.selenium.support.ui.Select;
import java.util.List;

import org.testng.Assert;

public class ElementInteraction {
    @Test
    public void locatorExamples() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. ID LOCATOR
        driver.get("https://www.ebay.com");
        WebElement searchBox3 = driver.findElement(By.id("gh-ac"));
        searchBox3.sendKeys("laptop");
        System.out.println("ID Locator used: typed 'laptop' into search bar");
        searchBox3.clear();

        /*
         Why ID is preferred:
         - ID is unique in DOM
         - Fastest locator in Selenium
         - Most stable compared to XPath/CSS
        */

        // ----------------------------------------
        // 2. Name Locator
        // ----------------------------------------
        WebElement searchByName = driver.findElement(By.name("_nkw"));
        searchByName.sendKeys("phone");
        System.out.println("Used Name Locator: Entered 'phone' in search box");
        searchByName.clear();

        /*
         When name locator is useful:
         - Common in forms (search, login fields)
         - Good alternative when ID is not available
        */

        // ----------------------------------------
        // 3. Class Name Locator
        // ----------------------------------------
        WebElement searchBtn1 = driver.findElement(By.className("vl-flyout-nav__link-container"));
        System.out.println("Used class name locator: Found electronics link.");

        /*
         Limitations of className:
         - Only single class allowed
         - Many elements share same class has a risk of wrong element
        */

        // ----------------------------------------
        // 4. CSS Selector Locator
        // ----------------------------------------

        // Attribute selector
        WebElement cssByAttribute = driver.findElement(By.cssSelector("input[type='text'][id='gh-ac']"));
        cssByAttribute.sendKeys("camera");
        System.out.println("Used CSS selector (Attribute): Entered 'camera'");
        cssByAttribute.clear();

        /*
         CSS Attribute selector:
         tag[attribute='value']
         Useful when ID is not available
        */

        // Class selector
        WebElement cssByClass = driver.findElement(By.cssSelector(".gh-tb.ui-autocomplete-input"));
        System.out.println("Used CSS selector (Class): Found search textbox");

        /*
         CSS Class selector:
         .classname
         Simple but may match multiple elements
        */

        // ----------------------------------------
        // 5. XPATH LOCATORS
        // ----------------------------------------

        // Relative XPath with attribute
        WebElement xpathByAttribute = driver.findElement(By.xpath("//input[@id='gh-ac']"));
        xpathByAttribute.sendKeys("books");
        System.out.println("XPath (Attribute): Entered 'books'");
        xpathByAttribute.clear();

        // XPath using text()
        WebElement xpathByText = driver.findElement(By.xpath("//a[text()='Electronics']"));
        String Electronics = xpathByText.getText();
        System.out.println("XPath (Text): Found link with text: " + Electronics);

        // XPath using contains()
        WebElement xpathContains = driver.findElement(By.xpath("//input[contains(@class,'gh-tb')]"));
        System.out.println("XPath (Contains): Found element with partial class match");


//Locator Strategy Implementation----------------------------------------------------------------------------------
        try {

            driver.get("https://www.ebay.com");

            System.out.println("=== Text Input Operations ===");

            // 1. First input field - Search Box
            WebElement searchBox1 = driver.findElement(By.id("gh-ac"));
            searchBox1.sendKeys("laptop");
            searchBox1.sendKeys(Keys.ENTER);

            // 2. Second input field - Clear and re-enter
            driver.navigate().back();
            WebElement searchBox2 = driver.findElement(By.id("gh-ac"));
            searchBox2.clear();
            searchBox2.sendKeys("camera");
            searchBox2.sendKeys(Keys.ENTER);

            System.out.println("Text input operations executed successfully.");
            System.out.println();
    //-------------------------------------------------------------------------------------------
            System.out.println("=== Dropdown Interactions ===");
            driver.navigate().back();

            // Locate dropdown and create Select object
            WebElement categoryDropdown1 = driver.findElement(By.id("gh-cat"));
            Select select = new Select(categoryDropdown1);
            // 1. selectByIndex()
            select.selectByIndex(2);
            System.out.println("Selected by index: 2");
            // 2. selectByValue()
            select.selectByValue("58058");
            System.out.println("Selected by value: 58058");
            // 3. selectByVisibleText()
            select.selectByVisibleText("Books");
            System.out.println("Selected by visible text: Books");
            // Get all dropdown options
            List<WebElement> options = select.getOptions();
            System.out.println("All Dropdown Options:");

            for (WebElement option : options) {
                System.out.println(option.getText());
            }

            // Get currently selected option
            WebElement selectedOption = select.getFirstSelectedOption();
            System.out.println("Currently Selected Option:");
            System.out.println(selectedOption.getText());
            System.out.println();

//-----------------------------------------------------------------------------------------------------
            driver.get("https://www.ebay.com/sch/ebayadvsearch");
            System.out.println("=== Checkbox/Radio Button Handling  ===");

            // Locate checkboxes
            WebElement titleDescCheckbox = driver.findElement(By.name("LH_TitleDesc"));
            WebElement completedCheckbox = driver.findElement(By.name("LH_Complete"));
            // Check state before interaction
            System.out.println("Before Interaction:");
            System.out.println("Title & Description selected: " + titleDescCheckbox.isSelected());
            System.out.println("Completed Listings selected: " + completedCheckbox.isSelected());

            // Toggle checkbox state
            if (!titleDescCheckbox.isSelected()) {
                titleDescCheckbox.click();
            }
            if (!completedCheckbox.isSelected()) {
                completedCheckbox.click();
            }

            // Verify AFTER interaction
            System.out.println("After Interaction:");
            System.out.println("Title & Description selected: " + titleDescCheckbox.isSelected());
            System.out.println("Completed Listings selected: " + completedCheckbox.isSelected());
            System.out.println();
//--------------------------------------------------------------------------------------------------------------
            System.out.println("=== Button Clicking ===");

            // 1. Button clicking using XPath locator (Advanced Search)

            WebElement advSearchButton = driver.findElement(By.xpath("//button[text()='Search']"));
            advSearchButton.click();
            System.out.println("Advanced search button clicked using XPath");

            // 2. Button using ID locator (Homepage Search)
            driver.get("https://www.ebay.com");
            driver.findElement(By.id("gh-ac")).sendKeys("laptop");
            WebElement searchButton = driver.findElement(By.id("gh-search-btn"));
            searchButton.click();
            System.out.println("Homepage Search button clicked using id locator.");
            System.out.println();
//------------------------------------------------------------------------------------------------------
            System.out.println("=== Text Extraction and Validation ===");

            // 1. Extract text from "Shop by category"
            WebElement categoryText = driver.findElement(By.xpath("//span[text()='Shop by category']"));
            String categoryLabel = categoryText.getText();
            System.out.println("Category Label: " + categoryLabel);

            WebElement searchBtn = driver.findElement(By.id("gh-search-btn"));
            String searchBtnLabel = searchBtn.getText();
            System.out.println("Search Button Label: " + searchBtnLabel);

            WebElement categoryDropdown = driver.findElement(By.id("gh-cat"));
            String categoryDropdownText = categoryDropdown.getText();
            System.out.println("category Dropdown Label: " + categoryDropdownText);

            // 2. Extract attribute values using getAttribute()

            // Search box attribute
            WebElement searchBox = driver.findElement(By.id("gh-ac"));
            String searchBoxValue = searchBox.getAttribute("value");
            System.out.println("Search Box Value Attribute: " + searchBoxValue);

            // Category dropdown attribute
            WebElement categoryDropdown2 = driver.findElement(By.id("gh-cat"));
            String selectedCategoryValue = categoryDropdown2.getAttribute("value");
            System.out.println("Selected Category Value Attribute: " + selectedCategoryValue);


            // 3. Validation
            Assert.assertEquals(categoryLabel, "Shop by category");
            Assert.assertEquals(searchBtnLabel, "Search");

            Assert.assertEquals(searchBoxValue, "laptop");
            Assert.assertTrue(selectedCategoryValue != null);

            System.out.println("All validations passed successfully.");

        }
        finally {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}
