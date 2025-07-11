package ui_tests;

import dto.Contact;
import dto.ContactsDto;
import dto.User;
import io.restassured.response.Response;
import manager.ApplicationManager;
import manager.ContactController;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.HeaderMenuItemEnum;
import utils.RandomUtils;

import static utils.PropertiesReader.getProperty;

public class AddNewContactsTestsApi extends ApplicationManager {
    HomePage homePage;
    LogInPage logInPage;
    ContactsPage contactsPage;
    AddContactsPage addContactsPage;

    @BeforeMethod
    public void login() {
        User user = new User(getProperty("login.properties","email"),
                getProperty("login.properties","password"));
        homePage = new HomePage(driver);
        logInPage = BasePage.clickButtonsOnHeader(HeaderMenuItemEnum.LOGIN);
        logInPage.typeLoginForm(user);
        logInPage.clickButtonLogIn();
        contactsPage = new ContactsPage(driver);
        addContactsPage = BasePage.clickButtonsOnHeader(HeaderMenuItemEnum.ADD);
    }
    @Test
    public void addNewContactPositiveTest() {
        Contact contact = Contact.builder()
                .name(RandomUtils.generateString(5))
                .lastName(RandomUtils.generateString(10))
                .phone(RandomUtils.generatePhone(10))
                .email(RandomUtils.generateEmail(6))
                .address(RandomUtils.generateString(20))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        addContactsPage.fillContactForm(contact);
        BasePage.pause(10);
        ContactController contactController = new ContactController();
        contactController.login();
        Response response = contactController.getAllUserContacts();
        System.out.println(response.getStatusLine());
        ContactsDto contactsDto = new ContactsDto();
        if(response.getStatusCode() == 200) {
            contactsDto = response.body().as(ContactsDto.class);
        }
        for(Contact contact1: contactsDto.getContacts()) {
            if (contact1.equals(contact)) {
                System.out.println(contact1);
            System.out.println(contact);
            //Assert.assertEquals(contact1, contact);
        }

        }

    }
}
