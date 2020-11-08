package TrendyolAutomationCase.CheckImageAndSelectProduct;

import TrendyolAutomationCase.DriverParameters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class CheckImageAndSelectProduct {
    String[] sections;
    public WebDriver driver;
    private String BROWSER = System.getProperty("browser");

    @BeforeTest
    @Parameters({"browser"})
    public void beforeClass() {
        sections = new String[]{"kadin", "erkek", "cocuk", "ev--yasam", "supermarket", "kozmetik", "ayakkabi--canta", "saat--aksesuar", "elektronik"};

        if (BROWSER.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--disable-notifications");
            System.setProperty(DriverParameters.FIREFOX_DRIVER_TYPE, DriverParameters.FIREFOX_DRIVER_PATH);
            driver = new FirefoxDriver(firefoxOptions);
        } else if (BROWSER.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");
            System.setProperty(DriverParameters.CHROME_DRIVER_TYPE, DriverParameters.CHROME_DRIVER_PATH);
            driver = new ChromeDriver(chromeOptions);
        }
        driver.manage().window().maximize();
        driver.get(CaseUrls.LOGIN_URL);
    }

    @Test
    public void MainTest() throws Exception {
        driver.findElement(By.id(Elements.ID_EMAIL)).sendKeys(Elements.USER_EMAIL);
        driver.findElement(By.id(Elements.ID_PASSWORD)).sendKeys(Elements.USER_PASSWORD);
        driver.findElement(By.xpath(Elements.PATH_SEND_BUTTON)).click();

        for (int i = 0; i < sections.length; i++) {
            try {
                if (driver.findElements(By.id(Elements.ID_X_BUTTON)).size() > 0) {
                    driver.findElement(By.id(Elements.ID_X_BUTTON)).click();
                    driver.findElement(By.xpath("//a[@href='/butik/liste/" + sections[i] + "']")).click();
                    Thread.sleep(500);
                }
                driver.findElement(By.xpath("//a[@href='/butik/liste/" + sections[i] + "']")).click();
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(sections[i] + " page cannot open.");
            }
        }
        driver.get(CaseUrls.IMAGE_CONTROL_URL);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        WebElement imageControl = driver.findElement(By.xpath(Elements.PATH_IMAGE_ELEMENT));
        while (!(Boolean) jse.executeScript("return arguments[0].complete;", imageControl)) {
            System.out.println("=> image broken...");
            Thread.sleep(1000);
        }
        System.out.println("image is loaded... ");

        Thread.sleep(1000);
        driver.get(CaseUrls.URL_RANDOM_PRODUCT);
        Thread.sleep(1000);
        driver.findElement(By.xpath(Elements.XPATH_ADD_BUTTON)).click();
    }

    @AfterTest
    public void afterClass() throws Exception {
        driver.quit();
    }
}
