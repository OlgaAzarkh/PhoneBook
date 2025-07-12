package api_tests;

import dto.Contact;
import dto.ResponseMessageDto;
import io.restassured.response.Response;
import manager.ContactController;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RandomUtils;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UpdateContactTests extends ContactController {

    Contact contact;

    @BeforeClass
    public void createContact() {
        contact = Contact.builder()
                .name(RandomUtils.generateString(5))
                .lastName(RandomUtils.generateString(10))
                .phone(RandomUtils.generatePhone(10))
                .email(RandomUtils.generateEmail(6))
                .address("Haifa " + RandomUtils.generateString(6))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        Response response = addNewContactRequest(contact, tokenDto);
        ResponseMessageDto responseMessageDto;
        if (response.getStatusCode() != 200)
            System.out.println("Contact was not created");
        else {
            responseMessageDto = response.body().as(ResponseMessageDto.class);
            contact.setId(responseMessageDto.getMessage().split("ID: ")[1]);
        }
    }

    @Test
    public void updateContactPositiveTest() {
        System.out.println(contact.toString());
        contact.setName("New name");
        Response response = updateContact(contact, tokenDto);
        System.out.println(response.getStatusLine());
        response
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("ResponseMessageDtoSchema.json"))
        ;
        ResponseMessageDto responseMessageDto = response.body().as(ResponseMessageDto.class);
        Assert.assertTrue(responseMessageDto.getMessage().contains("Contact was updated"));
    }
}
