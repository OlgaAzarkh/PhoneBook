package api_tests;

import dto.User;
import io.restassured.response.Response;
import manager.AuthenticationController;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseAPI;

import static utils.RandomUtils.generateEmail;
import static utils.RandomUtils.generateString;

public class RegistrationTests extends AuthenticationController implements BaseAPI {

    @Test
    public void RegistrationPositiveTest_200() {
        User user = new User(generateEmail(10), "Password123!");
        Response response = requestRegLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void RegistrationPositiveTest_getBody() {
        User user = new User(generateEmail(10), "Password123!");
        Response response = requestRegLogin(user, REGISTRATION_URL);
        System.out.println(response.body().print());
    }

    @Test
    public void RegistrationNegativeTestWrongEmail_400() {
        User user = new User(generateString(4), "Password123!");
        Response response = requestRegLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }
}
