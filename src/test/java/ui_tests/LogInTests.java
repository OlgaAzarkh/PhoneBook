package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LogInPage;

import java.lang.reflect.Method;

public class LogInTests extends ApplicationManager {
    @Test(groups = "smoke")
    public void loginPositiveTest(Method method) {
        logger.info("Start method " + method.getName());
        User user = new User("trtr@fjjf.ff", "Test789#");
        logger.info("Test data -> " + user.toString());
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginInHeader();
        LogInPage loginPage = new LogInPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickButtonLogIn();
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.isContactsPresent());
    }

    @Test
    public void loginNegativeTestWrongPassword() {
        User user = new User("trtr@fjjf.f", "@@@@@");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginInHeader();
        LogInPage loginPage = new LogInPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickButtonLogIn();
        loginPage.closeAlert();
        Assert.assertTrue(loginPage.isErrorMessagePresent("Login Failed with code 401"));
    }

    @Test
    public void loginNegativeTestWrongEmail() {
        User user = new User("trtr@fjjf.ff", "Test789@");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginInHeader();
        LogInPage loginPage = new LogInPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickButtonLogIn();
        loginPage.closeAlert();
        Assert.assertTrue(loginPage.isErrorMessagePresent("Login Failed with code 401"));
    }
}