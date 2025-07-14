package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.time.LocalDate;

public class ApplicationManager {
    protected WebDriver driver;

    public Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        logger.info("start testing " + LocalDate.now());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @AfterMethod(enabled = true, alwaysRun = true)
    public void tearDown() {
        logger.info("Stop test");
        if (driver != null)
            driver.quit();
    }
}
