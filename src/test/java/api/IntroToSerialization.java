package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.CustomResponse;
import entities.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import utilities.CashWiseToken;
import utilities.Config;

public class IntroToSerialization {
    public static void main(String[] args) throws JsonProcessingException {

        String token = CashWiseToken.GetToken();

        RequestBody body = new RequestBody();
        body.setName_tag("ITnew");
        body.setDescription("Data company in Naperville");

        Response response = RestAssured.given().auth().oauth2(token)
                .contentType(ContentType.JSON).body(body).post("https://backend.cashwise.us/api/myaccount/tags");

        System.out.println(response.statusCode());

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        System.out.println(customResponse.getId());





    }
}
