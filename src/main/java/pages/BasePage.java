package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HeaderMenuItemEnum;

import java.time.Duration;

public abstract class BasePage {
     static WebDriver driver;

     Logger logger = LoggerFactory.getLogger(BasePage.class);

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends BasePage> T clickButtonsOnHeader(HeaderMenuItemEnum headerMenuItem) {
        //pause(3);
        //WebElement element = driver.findElement(By.xpath(headerMenuItem.getLocator()));
        //element.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(headerMenuItem.getLocator()))).click();
        switch(headerMenuItem){
            case LOGIN, SIGN_OUT -> { return (T) new LogInPage(driver);
            }
            case HOME -> { return (T) new HomePage(driver);
            }
            case ABOUT -> { return (T) new AboutPage(driver);
            }
            case ADD -> { return (T) new AddContactsPage(driver);
            }
            case CONTACTS -> { return (T) new ContactsPage(driver);
            }
            default -> throw new IllegalArgumentException("Invalid headerMenuItem");
        }
    }

    public boolean isElementPresent(WebElement element) {
       return element.isDisplayed();
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        return element.getText().contains(text);
    }

    public boolean validateUrl(String str) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(str));
    }

    public boolean urlDoesNotContain(String str) {
        pause(5);
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains(str)));
    }

}
