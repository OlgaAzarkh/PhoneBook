package ui_tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;

import static org.testng.Assert.assertTrue;


public class LogInTests extends ApplicationManager {
    private static final String EMAIL = "new11@testperest55.rr";
    private static final String PASSWORD = "Test777@";
    @Test
    public void registerPositiveTest() {
            HomePage homePage = new HomePage(getDriver());
            homePage.clickBtnLoginInHeader();
            LogInPage loginPage = new LogInPage(getDriver());
            loginPage.fillEmailForm(EMAIL, PASSWORD);
            loginPage.clickRegistrationButton();
            assertTrue(loginPage.isNoContactsMessageVisible(), "'No Contacts here!' message should be visible");
        }

        @Test
        public void loginPositiveTest () {
            HomePage homePage = new HomePage(getDriver());
            homePage.clickBtnLoginInHeader();
            LogInPage loginPage = new LogInPage(getDriver());
            loginPage.fillEmailForm(EMAIL, PASSWORD);
            loginPage.clickButtonLogIn();
        }
    }

