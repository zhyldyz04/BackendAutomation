package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class ReqRres {
    public static void main(String[] args) {

        String url = "https://reqres.in/api/users/7";
        Response response = RestAssured.get(url);
        System.out.println(response.prettyPrint());
        System.out.println(response.statusCode());
        String email = response.jsonPath().get("data.email").toString();
        System.out.println(email);

        String firstName = response.jsonPath().get("data.first_name").toString();
        System.out.println(firstName);

        String lastName = response.jsonPath().getString("data.last_name");
        System.out.println(lastName);

        String supportText = response.jsonPath().get("support.text").toString();
        System.out.println(supportText);




    }
}
