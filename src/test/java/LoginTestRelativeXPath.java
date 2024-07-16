import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class LoginTestRelativeXPath {
    public static void main(String[] args) {

        try {
            // Set the path to the ChromeDriver executable
            System.setProperty("webdriver.chrome.driver", "D:\\sspto\\Downloads1\\Softwares\\Chrome Driver\\chromedriver-win64\\chromedriver.exe");

            // Launch the Chrome Browser
            WebDriver driver = new ChromeDriver();

            // Open the NxtTrendz login page
            driver.get("https://rahulnxttrendz.ccbp.tech/login");

            // Find and fill in the form fields
            WebElement usernameEl = driver.findElement(By.xpath("//input[@id='username']"));
            usernameEl.sendKeys("rahul");

            WebElement passwordEl = driver.findElement(By.xpath("//input[@id='password']"));
            passwordEl.sendKeys("rahul@2021");

            WebElement buttonEl = driver.findElement(By.xpath("//button[@type='submit']"));
            buttonEl.submit();

            // Define the expected URL of the home page
            String expectedUrl = "https://rahulnxttrendz.ccbp.tech/";

            // Wait for the expected URL to be loaded
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(expectedUrl));

            // Get the current URL after login
            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.equals(expectedUrl)) {

                System.out.println("Navigation to home page was successful!");
            }

            // Find and click on the "Shop Now" button
            WebElement shopNowButtonEl = driver.findElement(By.xpath("//button[@class='shop-now-button']"));
            shopNowButtonEl.click();

            // Define the expected URL of the products page
            String productsPageUrl = "https://rahulnxttrendz.ccbp.tech/products";

            // Get the current URL after login
            currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals(productsPageUrl)) {
                System.out.println("Navigation to products page was successful!");
            }

            //Wait until elements are displayed
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='products-list']")));

            // Verify the display of products on the products page
            List<WebElement> products = driver.findElements(By.xpath("//li[@class='product-item']"));

            if (products.size() > 0) {
                System.out.println("Products are displayed successfully on the products page: " + products.size());

                // using XPath functions
                WebElement productNameEl = driver.findElement(By.xpath("//h1[text()='Minifigures']"));
                String expectedProductTitle = productNameEl.getText();
                System.out.println(expectedProductTitle);
                productNameEl.click();

                String productDetailsUrl = "https://rahulnxttrendz.ccbp.tech/products/1002";
                wait.until(ExpectedConditions.urlToBe(productDetailsUrl));

                currentUrl = driver.getCurrentUrl();
                if (currentUrl.equals(productDetailsUrl)) {
                    System.out.println("Navigation to product details page is successful!");
                }

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-details-success-view']")));
                WebElement productTitleElement = driver.findElement(By.xpath("//h1[text()='Minifigures']"));
                String actualProductTitle = productTitleElement.getText();
                if (expectedProductTitle.equals(actualProductTitle)) {
                    System.out.println("Product Name is - " + actualProductTitle);
                } else {
                    System.out.println("Actual product name is not same as expected product name" + actualProductTitle);
                }

                String expectedDesc = "Collect all mystery mini-figures in the new series 11 and grow your LEGO Minifigure Collection. Each mini-figure comes in a sealed “mystery” bag with its accessories, display plate, and collector’s booklet. Only 1 of 16 individual mini-figures will be available in each “mystery” bag.";

                WebElement productDescEle = driver.findElement(By.xpath("//p[contains(text(), 'mystery mini-figures')]"));
                String actualProductDesc = productDescEle.getText();

                if (expectedDesc.equals(actualProductDesc)) {
                    System.out.println("Description is - " + actualProductDesc);
                } else {
                    System.out.println("Actual description is not same as expected description" + actualProductDesc);
                }
            } else {
                System.out.println("No products found on the products page.");
            }

            driver.quit();
        }catch (Exception e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }
    }
}
