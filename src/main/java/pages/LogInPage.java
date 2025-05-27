package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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

    public void clickButtonLogIn() {
        buttonLogin.click();
    }

    @FindBy(xpath = "//button[@name='registration']")
    WebElement buttonRegistration;

    public void clickRegistrationButton() {
        buttonRegistration.click();
    }

    @FindBy(xpath = "//h1[text()=' No Contacts here!']")
    WebElement noContacts;

    public boolean isNoContactsMessageVisible() {
        return noContacts.isDisplayed();
    }

    }
