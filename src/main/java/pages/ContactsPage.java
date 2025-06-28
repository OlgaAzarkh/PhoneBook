package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class ContactsPage extends BasePage {

    public ContactsPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this
        );
    }

    @FindBy(xpath = "//h1[text()=' No Contacts here!']")
    WebElement noContacts;
    @FindBy(xpath = "//a[@href='/contacts']")
    WebElement btnContactsHeader;
    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']")
    List<WebElement> contactsList;

    public boolean isContactsPresent() {
        return isElementPresent(btnContactsHeader);
    }

    public Integer getContactsListSizeUseFindElement() {
        pause(15);
        List <WebElement> listContactsFindElement = driver.findElements(
                By.xpath("//div[@class='contact-item_card__2SOIM']"));
        return listContactsFindElement.size();
    }

    public boolean validateContactNamePhone(String name, String phone) {
        for (WebElement element : contactsList) {
            if(element.getText().contains(name) && element.getText().contains(phone))
                return true;
        }
        return false;
    }

    public String getPhoneFromList() {
        if(!contactsList.isEmpty()) {
            System.out.println(contactsList.get(0).getText().split("\n")[1]);
            return contactsList.get(0).getText().split("\n")[1];
        }
        System.out.println("contact list is empty");
        return null;
    }
}
