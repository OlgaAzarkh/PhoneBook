package pages;

import dto.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LogInPage extends BasePage {

    public LogInPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this
        );
    }

    @FindBy(xpath = "//input[@name='email']")
    WebElement inputEmail;

    @FindBy(xpath = "//input[@name='password']")
    WebElement inputPassword;

    public void fillEmailForm(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
    }

    @FindBy(xpath = "//button[@name='login']")
    WebElement buttonLogin;

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement btnSignOutInHeader;

    public void clickButtonLogIn() {
        buttonLogin.click();
    }

    public void typeLoginForm(User user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    @FindBy(xpath = "//button[@name='registration']")
    WebElement buttonRegistration;

    @FindBy(xpath = "//div[@class='login_login__3EHKB']/div")
    WebElement errorMessageLogin;

    @FindBy(className = "contact-page_message__2qafk")
    WebElement messageNoContacts;

    public void clickRegistrationButton() {
        buttonRegistration.click();
    }

    public void logOut() {
        btnSignOutInHeader.click();
    }

    public void closeAlert() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        alert.accept();
    }

    public String closeAlertAndReturnItsText() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public boolean isErrorMessagePresent(String message) {
        return isTextInElementPresent(errorMessageLogin, message);
    }

    public boolean isNoContactMessagePresent(String message) {
        return isTextInElementPresent(messageNoContacts, message);
    }

    }
