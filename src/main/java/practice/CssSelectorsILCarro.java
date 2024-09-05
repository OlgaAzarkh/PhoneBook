package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CssSelectorsILCarro {
    WebDriver driver =new ChromeDriver();

    @Test
public void CssSelectorsILCarro() {
        driver.navigate().to("https://ilcarro.web.app/search");
        driver.manage().window().maximize();

        WebElement search = driver.findElement(By.cssSelector("a[href='search']"));
        System.out.println(search.getText());
        pause(2);
        search.click();

        WebElement btnletTheCarWork = driver.findElement(By.cssSelector("a[href='let-car-work']"));
        btnletTheCarWork.click();
        pause(2);

        WebElement inputLocation = driver.findElement(By.cssSelector("input[formcontrolname='pickUpPlace']"));
        inputLocation.sendKeys("Israel,Rehovot");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pac-item")));
        List<WebElement> dropdownItems = driver.findElements(By.cssSelector(".pac-item"));
        if (dropdownItems.size() > 1) {
            dropdownItems.get(1).click();
        } else {
            System.out.println("ot enough");
        }
        pause(2);

        WebElement inputManufactory=driver.findElement(By.cssSelector("input[formcontrolname='make']"));
        inputManufactory.sendKeys("Toyota");
        pause(2);

        WebElement inputModel=driver.findElement(By.cssSelector("input[formcontrolname='model']"));
        inputModel.sendKeys("Corolla");
        pause(2);

        WebElement inputYear=driver.findElement(By.cssSelector("input[formcontrolname='year']"));
        inputYear.sendKeys("2024");
        inputYear.sendKeys(Keys.ARROW_DOWN);
        pause(2);


        WebElement dropdown = driver.findElement(By.cssSelector("div.dropdown-toggle"));
        dropdown.click();

        WebElement fuelDropdown = driver.findElement(By.cssSelector("select[formcontrolname='fuel']"));
        Select selectFuel = new Select(fuelDropdown);
        selectFuel.selectByValue("Petrol");




    driver.quit();
    }

    public void pause(int time){
        try {
            Thread.sleep(time*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


