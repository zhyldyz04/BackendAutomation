package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CashwiseClientSteps {

    Response response;
    @Given("the user hits the API with endpoint {string}")
    public void the_user_hits_the_api_with_endpoint(String endpoint) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 2);

        String path = Config.getProperty("baseCashwiseUrl");
        response = RestAssured.given().auth().
                oauth2(Config.getProperty("cashwiseToken")).
                params(params).get(path + endpoint);



    }
    @Then("the user validates that status code is {int}")
    public void the_user_validates_that_status_code_is(int statusCode) {
      Assert.assertEquals(response.getStatusCode(), statusCode);
        System.out.println(response.prettyPrint());

    }
    @Then("the user verifies if all clients id are not empty")
    public void the_user_verifies_if_all_clients_id_are_not_empty() {

       response.jsonPath().get("responses.client_id").toString();




    }





}
