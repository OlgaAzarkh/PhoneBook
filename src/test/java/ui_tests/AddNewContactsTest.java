package ui_tests;

import dto.Contact;
import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.HeaderMenuItemEnum;
import utils.RandomUtils;

public class AddNewContactsTest extends ApplicationManager {
    HomePage homePage;
    LogInPage logInPage;
    ContactsPage contactsPage;
    AddContactsPage addContactsPage;
    int sizeBeforeAdding;

    @BeforeMethod
    public void login() {
        User user = new User("trtr@fjjf.ff", "Test789#");
        homePage = new HomePage(driver);
        logInPage = BasePage.clickButtonsOnHeader(HeaderMenuItemEnum.LOGIN);
        logInPage.typeLoginForm(user);
        logInPage.clickButtonLogIn();
        contactsPage = new ContactsPage(driver);
        sizeBeforeAdding = contactsPage.getContactsListSizeUseFindElement();
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
        Assert.assertEquals(sizeBeforeAdding + 1, sizeAfterAdd);
    }


}
