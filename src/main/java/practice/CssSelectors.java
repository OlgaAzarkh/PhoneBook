package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CssSelectors {
WebDriver driver=new ChromeDriver();

@Test
public void cssSelectors(){
    driver.navigate().to("https://telranedu.web.app/home");
    driver.manage().window().maximize();

    WebElement elementH3 = driver.findElement(By.cssSelector("h3"));
    System.out.println(elementH3.getText());

    WebElement elementAbout=driver.findElement(By.cssSelector("a[href='/about']"));
    elementAbout.click();

    WebElement btnLogin = driver.findElement(By.cssSelector("a[href='/login']"));
    pause(2);
    btnLogin.click();

    WebElement inputEmail= driver.findElement(By.cssSelector("input[name='email']"));
    pause(2);
    inputEmail.sendKeys("my_email");



pause(2);
    driver.quit();

}


private void pause(int time){
    try {
        Thread.sleep(time*1000L);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}


}
