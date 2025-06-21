package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LogInPage;
import utils.RandomUtils;

public class RegistrationTests extends ApplicationManager {
    HomePage homePage;
    LogInPage loginPage;

    @BeforeMethod
    public void goToRegistrationPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginInHeader();
        loginPage = new LogInPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        User user = new User (RandomUtils.generateEmail(4), "Test789#");
        goToRegistrationPage();
        loginPage.typeLoginForm(user);
        loginPage.clickRegistrationButton();
        loginPage.isNoContactMessagePresent("Add new by clicking on Add in NavBar!");
    }

    @Test
    public void registrationNegativeTestWrongPassword() {
        User user = new User (RandomUtils.generateEmail(6), "!!!!!!");
        goToRegistrationPage();
        loginPage.typeLoginForm(user);
        loginPage.clickRegistrationButton();
        Assert.assertTrue(loginPage.closeAlertAndReturnItsText().contains("Password must contain at least one uppercase letter!"));
        Assert.assertTrue(loginPage.isErrorMessagePresent("Registration failed with code 400"));
    }

    @Test
    public void registrationNegativeTestDuplicatedUser() {
        User user = new User (RandomUtils.generateEmail(4), "Test789#");
        goToRegistrationPage();
        loginPage.typeLoginForm(user);
        loginPage.clickRegistrationButton();
        if(loginPage.isNoContactMessagePresent("Add new by clicking on Add in NavBar!")) {
            loginPage.logOut();
            loginPage.typeLoginForm(user);
            loginPage.clickRegistrationButton();
            Assert.assertTrue(loginPage.closeAlertAndReturnItsText().contains("User already exist"));
        } else {
            Assert.fail("Wrong registration with the user " + user.toString());
        }
    }
}
