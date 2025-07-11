package ui_tests;

import data_provider.ContactDP;
import dto.Contact;
import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.HeaderMenuItemEnum;
import utils.RandomUtils;
import static utils.PropertiesReader.*;

public class AddNewContactsTest extends ApplicationManager {
    HomePage homePage;
    LogInPage logInPage;
    ContactsPage contactsPage;
    AddContactsPage addContactsPage;
    String existPhone;
    int sizeBeforeAdding;

    @BeforeMethod
    public void login() {
        User user = new User(getProperty("login.properties","email"),
                getProperty("login.properties","password"));
        homePage = new HomePage(driver);
        logInPage = BasePage.clickButtonsOnHeader(HeaderMenuItemEnum.LOGIN);
        logInPage.typeLoginForm(user);
        logInPage.clickButtonLogIn();
        contactsPage = new ContactsPage(driver);
        sizeBeforeAdding = contactsPage.getContactsListSizeUseFindElement();
        existPhone = contactsPage.getPhoneFromList();
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
        int sizeAfterAdd = contactsPage.getContactsListSizeUseFindElement();
        Assert.assertEquals(sizeBeforeAdding + 1, sizeAfterAdd);
    }

    @Test
    public void addNewContactPositiveTestValidatePhone() {
        Contact contact = Contact.builder()
                .name("Name - " + RandomUtils.generateString(5))
                .lastName(RandomUtils.generateString(10))
                .phone(RandomUtils.generatePhone(10))
                .email(RandomUtils.generateEmail(6))
                .address(RandomUtils.generateString(20))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        addContactsPage.fillContactForm(contact);
        Assert.assertTrue(contactsPage.validateContactNamePhone(contact.getName(), contact.getPhone()));
    }

    @Test(invocationCount = 1)
    public void addNewContactPositiveTestUseFindElements() {
        Contact contact = Contact.builder()
                .name(RandomUtils.generateString(5))
                .lastName(RandomUtils.generateString(10))
                .phone(RandomUtils.generatePhone(10))
                .email(RandomUtils.generateEmail(6))
                .address(RandomUtils.generateString(20))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        addContactsPage.fillContactForm(contact);
        int sizeAfterAdd = contactsPage.getContactsListSizeUseFindElement();
        System.out.println(sizeBeforeAdding + " vs " + sizeAfterAdd);
        Assert.assertEquals(sizeBeforeAdding, sizeAfterAdd);
    }

    @Test(dataProvider = "addNewContactDP", dataProviderClass = ContactDP.class)
    public void addNewCarPositiveTestDataProvider(Contact contact) {
        addContactsPage.fillContactForm(contact);
        Assert.assertTrue(contactsPage.validateContactNamePhone(contact.getName(), contact.getPhone()));
    }

    @Test(invocationCount = 1)
    public void addNewContactNegativeTestEmptyName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName(RandomUtils.generateString(10))
                .phone(RandomUtils.generatePhone(10))
                .email(RandomUtils.generateEmail(6))
                .address(RandomUtils.generateString(20))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        addContactsPage.fillContactForm(contact);
        Assert.assertTrue(addContactsPage.validateUrl("add"));
    }

    @Test(invocationCount = 1)
    public void addNewContactNegativeTestEmptyLastName() {
        Contact contact = Contact.builder()
                .name(RandomUtils.generateString(10))
                .lastName("")
                .phone(RandomUtils.generatePhone(10))
                .email(RandomUtils.generateEmail(6))
                .address(RandomUtils.generateString(20))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        addContactsPage.fillContactForm(contact);
        Assert.assertTrue(addContactsPage.urlDoesNotContain("contacts"));
    }

    @Test(invocationCount = 1)
    public void addNewContactNegativeTestEmptyPhone() {
        Contact contact = Contact.builder()
                .name(RandomUtils.generateString(5))
                .lastName(RandomUtils.generateString(5))
                .phone("")
                .email(RandomUtils.generateEmail(6))
                .address(RandomUtils.generateString(20))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        addContactsPage.fillContactForm(contact);
        Assert.assertEquals(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!", addContactsPage.closeAlertAndReturnText());
    }

    @Test(invocationCount = 1)
    public void addNewContactNegativeTestEmptyEmail() {
        Contact contact = Contact.builder()
                .name(RandomUtils.generateString(10))
                .lastName(RandomUtils.generateString(17))
                .phone(RandomUtils.generatePhone(10))
                .email("")
                .address(RandomUtils.generateString(20))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        addContactsPage.fillContactForm(contact);
        Assert.assertTrue(addContactsPage.urlDoesNotContain("contacts"));
    }

    @Test(invocationCount = 1)
    public void addNewContactNegativeTestExistingPhone() {
        Contact contact = Contact.builder()
                .name(RandomUtils.generateString(10))
                .lastName(RandomUtils.generateString(17))
                .phone(existPhone)
                .email(RandomUtils.generateEmail(7))
                .address(RandomUtils.generateString(20))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        addContactsPage.fillContactForm(contact);
        Assert.assertTrue(addContactsPage.urlDoesNotContain("contacts"));
    }
}
