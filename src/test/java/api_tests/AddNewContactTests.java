package api_tests;

import dto.Contact;
import dto.ErrorMessageDto;
import dto.ResponseMessageDto;
import dto.TokenDto;
import io.restassured.response.Response;
import manager.ContactController;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.RandomUtils;

public class AddNewContactTests extends ContactController {
    private SoftAssert softAssert;

    @BeforeMethod
    public void initSoftAssert() {
        softAssert = new SoftAssert();
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
        Response response = addNewContactRequest(contact, tokenDto);
        System.out.println(response.getStatusCode());
        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        if(response.getStatusCode() == 200){
            responseMessageDto = response.body().as(ResponseMessageDto.class);
        }
        System.out.println(responseMessageDto.toString());
        Assert.assertTrue(responseMessageDto.getMessage().contains("Contact was added!"));
    }

    @Test
    public void addNewContactNegativeTestWrongToken() {
        Contact contact = Contact.builder()
                .name(RandomUtils.generateString(5))
                .lastName(RandomUtils.generateString(10))
                .phone(RandomUtils.generatePhone(10))
                .email(RandomUtils.generateEmail(6))
                .address(RandomUtils.generateString(20))
                .description("desc " + RandomUtils.generateString(6))
                .build();
        Response response = addNewContactRequest(contact, TokenDto.builder()
                .token("token")
                .build());
        System.out.println(response.getStatusLine());
        System.out.println("HTTP status code: " + response.statusCode());
        ErrorMessageDto errorMessageDto = response.body().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto.toString());
        softAssert.assertEquals(errorMessageDto.getStatus(), 401);
        softAssert.assertTrue(errorMessageDto.getMessage().toString()
                .contains("JWT strings must contain exactly 2 period characters."));
        softAssert.assertAll();
    }
}
